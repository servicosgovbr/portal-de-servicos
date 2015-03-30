package br.gov.servicos.frontend;

import br.gov.servicos.config.DestaquesConfig;
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
import static lombok.AccessLevel.PRIVATE;
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

    IndexController controller;

    @Before
    public void setUp() {
        controller = new IndexController(servicos, destaques);

        doReturn(new PageImpl<>(asList(SERVICO)))
                .when(servicos)
                .findAll(any(PageRequest.class));
    }

    @Test
    public void redirecionaParaAPaginaInicial() {
        assertViewName(controller.index(), "index");
    }

    @Test
    public void retornaServicosASeremExibidos() {
        assertModelAttributeValue(controller.index(), "destaques", asList(SERVICO));
    }

    @Test
    public void deveRetornarOsServicosPaginados() {
        controller.index();
        verify(servicos).findAll(new PageRequest(0, 15, new Sort(DESC, "titulo")));
    }

    @Test
    public void deveRetornarDestaquesPrimeiro() {
        doReturn(asList("servico-em-destaque"))
                .when(destaques)
                .getServicos();

        Servico servicoEmDestaque = new Servico().withId("servico-em-destaque");
        doReturn(servicoEmDestaque)
                .when(servicos)
                .findOne("servico-em-destaque");

        assertModelAttributeValue(controller.index(), "destaques", asList(servicoEmDestaque, SERVICO));
    }

    @Test
    public void deveFiltrarFiltrarServicosIguais() {
        doReturn(asList("1")).when(destaques).getServicos();
        doReturn(SERVICO).when(servicos).findOne("1");

        assertModelAttributeValue(controller.index(), "destaques", asList(SERVICO));
    }

    @Test
    public void deveFiltrarDestaquesNaoEncontrados() {
        doReturn(asList("destaque-nao-cadastrado")).when(destaques).getServicos();
        assertModelAttributeValue(controller.index(), "destaques", asList(SERVICO));
    }
}
