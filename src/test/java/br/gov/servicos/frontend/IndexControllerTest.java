package br.gov.servicos.frontend;

import br.gov.servicos.config.DestaquesConfig;
import br.gov.servicos.destaques.ServicosEmDestaque;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.piwik.PiwikClient;
import br.gov.servicos.piwik.PiwikPage;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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

    @Mock
    OrgaoRepository orgaos;

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
        controller = comDestaquesManuais();
        assertViewName(controller.index(), "index");
    }

    @Test
    public void retornaServicosASeremExibidos() {
        controller = comDestaquesManuais();
        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveRetornarOsServicosPaginados() {
        controller = comDestaquesManuais();
        controller.index();
        verify(servicos).findAll(new PageRequest(0, 10, new Sort(DESC, "nome")));
    }

    @Test
    public void deveRetornarDestaquesPrimeiro() {
        controller = comDestaquesManuais();
        given(destaques.getServicos()).willReturn(singletonList("servico-em-destaque"));

        Servico servicoEmDestaque = new Servico().withNome("servico-em-destaque");
        given(servicos.findOne("servico-em-destaque")).willReturn(servicoEmDestaque);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoEmDestaque, SERVICO));
    }

    @Test
    public void devePedirUrlsMaisAcessadasParaOPiwik() throws Exception {
        controller = comDestaquesAutomaticos();
        given(piwikClient.getPageUrls(anyString(), anyString()))
                .willReturn(singletonList(
                        new PiwikPage()
                                .withUrl("/servico/servico-mais-acessado")
                                .withVisitors(3L)
                                .withUniqueVisitors(1L)));

        Servico servicoMaisAcessado = new Servico().withNome("Servico Mais Acessado");
        given(servicos.findOne("servico-mais-acessado")).willReturn(servicoMaisAcessado);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoMaisAcessado, SERVICO));
    }

    @Test
    public void deveRetornarMaisAcessadosECompletarComDestaquesEOutrosServicos() throws Exception {
        controller = comDestaquesAutomaticos();
        given(piwikClient.getPageUrls(anyString(), anyString()))
                .willReturn(singletonList(
                        new PiwikPage()
                                .withUrl("/servico/servico-mais-acessado")
                                .withVisitors(3L)
                                .withUniqueVisitors(1L)));
        given(destaques.getServicos()).willReturn(singletonList("servico-em-destaque"));

        Servico servicoMaisAcessado = new Servico().withNome("servico-mais-acessado");
        given(servicos.findOne("servico-mais-acessado")).willReturn(servicoMaisAcessado);

        Servico servicoEmDestaque = new Servico().withNome("servico-em-destaque");
        given(servicos.findOne("servico-em-destaque")).willReturn(servicoEmDestaque);

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoMaisAcessado, servicoEmDestaque, SERVICO));
    }

    @Test
    public void deveFiltrarServicosMaisAcessadosNaoEncontrados() throws Exception {
        controller = comDestaquesAutomaticos();
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
        controller = comDestaquesManuais();
        given(destaques.getServicos()).willReturn(singletonList("exemplo-de-servico"));
        given(servicos.findOne("exemplo-de-servico")).willReturn(SERVICO);

        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveFiltrarDestaquesNaoEncontrados() {
        controller = comDestaquesManuais();
        given(destaques.getServicos()).willReturn(singletonList("destaque-nao-cadastrado"));
        assertModelAttributeValue(controller.index(), "destaques", singletonList(SERVICO));
    }

    @Test
    public void deveRedirecionarParaOrgao() throws IOException {
        controller = comDestaquesManuais();
        String urlOrgao = "http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/1934";
        given(orgaos.findByUrl(urlOrgao)).willReturn(of(new Orgao().withId("secretaria-secretarial-do-secretariado-sss")));

        ModelAndView view = controller.redirectParaOrgao(urlOrgao);

        assertThat(((RedirectView) view.getView()).getUrl(), is("/orgaos/secretaria-secretarial-do-secretariado-sss"));
    }

    @Test
    public void deveRedirecionarParaIndexQuandoHaProblemasComParametroOrgao() throws IOException {
        controller = comDestaquesManuais();
        String urlOrgao = "http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/1934";
        given(orgaos.findByUrl(urlOrgao)).willReturn(empty());

        ModelAndView view = controller.redirectParaOrgao(urlOrgao);

        assertThat(view.getViewName(), is("index"));
    }

    private IndexController comDestaquesAutomaticos() {
        return new IndexController(destaquesAutomaticos, orgaos);
    }

    private IndexController comDestaquesManuais() {
        return new IndexController(destaquesManuais, orgaos);
    }
}
