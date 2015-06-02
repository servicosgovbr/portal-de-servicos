package br.gov.servicos.frontend;

import br.gov.servicos.config.DestaquesConfig;
import br.gov.servicos.destaques.ServicosEmDestaque;
import br.gov.servicos.piwik.PiwikClient;
import br.gov.servicos.piwik.PiwikPage;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class IndexControllerTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    DestaquesConfig destaques;

    @Mock
    PiwikClient piwikClient;

    IndexController controller;

    ServicosEmDestaque destaquesManuais;
    ServicosEmDestaque destaquesAutomaticos;

    @Before
    public void setUp() {
        destaquesManuais = new ServicosEmDestaque(servicos, destaques, piwikClient, false);
        destaquesAutomaticos = new ServicosEmDestaque(servicos, destaques, piwikClient, true);

        given(servicos.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(singletonList(SERVICO)));
    }

    @Test
    public void redirecionaParaAPaginaInicial() {
        controller = new IndexController(destaquesManuais);
        assertViewName(controller.index(), "index");
    }

    @Test
    public void retornaServicosASeremExibidos() {
        controller = new IndexController(destaquesManuais);
        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveRetornarOsServicosPaginados() {
        controller = new IndexController(destaquesManuais);
        controller.index();
        verify(servicos).findAll(new PageRequest(0, 10, new Sort(DESC, "titulo")));
    }

    @Test
    public void deveRetornarDestaquesPrimeiro() {
        controller = new IndexController(destaquesManuais);
        given(destaques.getServicos()).willReturn(singletonList("servico-em-destaque"));

        Servico servicoEmDestaque = new Servico().withId("servico-em-destaque");
        given(servicos.findOne("servico-em-destaque")).willReturn(servicoEmDestaque);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoEmDestaque, SERVICO));
    }

    @Test
    public void devePedirUrlsMaisAcessadasParaOPiwik() throws Exception {
        controller = new IndexController(destaquesAutomaticos);
        given(piwikClient.getPageUrls(anyString(), anyString()))
                .willReturn(singletonList(
                        new PiwikPage()
                                .withUrl("/servico/servico-mais-acessado")
                                .withVisitors(3L)
                                .withUniqueVisitors(1L)));

        Servico servicoMaisAcessado = new Servico().withId("servico-mais-acessado");
        given(servicos.findOne("servico-mais-acessado")).willReturn(servicoMaisAcessado);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoMaisAcessado, SERVICO));
    }

    @Test
    public void deveRetornarMaisAcessadosECompletarComDestaquesEOutrosServicos() throws Exception {
        controller = new IndexController(destaquesAutomaticos);
        given(piwikClient.getPageUrls(anyString(), anyString()))
                .willReturn(singletonList(
                        new PiwikPage()
                                .withUrl("/servico/servico-mais-acessado")
                                .withVisitors(3L)
                                .withUniqueVisitors(1L)));
        given(destaques.getServicos()).willReturn(singletonList("servico-em-destaque"));

        Servico servicoMaisAcessado = new Servico().withId("servico-mais-acessado");
        given(servicos.findOne("servico-mais-acessado")).willReturn(servicoMaisAcessado);

        Servico servicoEmDestaque = new Servico().withId("servico-em-destaque");
        given(servicos.findOne("servico-em-destaque")).willReturn(servicoEmDestaque);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoMaisAcessado, servicoEmDestaque, SERVICO));
    }

    @Test
    public void deveFiltrarServicosMaisAcessadosNaoEncontrados() throws Exception {
        controller = new IndexController(destaquesAutomaticos);
        given(piwikClient.getPageUrls(anyString(), anyString()))
                .willReturn(singletonList(
                        new PiwikPage()
                                .withUrl("/servico/servico-nao-existe")
                                .withVisitors(3L)
                                .withUniqueVisitors(1L)));

        given(servicos.findOne("servico-nao-existe")).willReturn(null);

        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveFiltrarFiltrarServicosIguais() {
        controller = new IndexController(destaquesManuais);
        given(destaques.getServicos()).willReturn(singletonList("titulo"));
        given(servicos.findOne("titulo")).willReturn(SERVICO);

        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveFiltrarDestaquesNaoEncontrados() {
        controller = new IndexController(destaquesManuais);
        given(destaques.getServicos()).willReturn(singletonList("destaque-nao-cadastrado"));
        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }
}
