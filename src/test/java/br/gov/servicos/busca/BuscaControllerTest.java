package br.gov.servicos.busca;

import br.gov.servicos.cms.PaginaEstatica;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;

import static br.gov.servicos.fixtures.TestData.PAGINA_ESTATICA_DE_TEMATICA;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class BuscaControllerTest {

    @Mock
    BuscadorConteudo buscador;

    Page<PaginaEstatica> umConteudo = new FacetedPageImpl<>(singletonList(PAGINA_ESTATICA_DE_TEMATICA));
    BuscaController controller;

    @Before
    public void setUp() {
        controller = new BuscaController(buscador);
    }

    @Test
    public void buscaRedirecionaParaPaginaDeResultados() {
        assertViewName(controller.busca(null, 1), "resultados-busca");
    }

    @Test
    public void buscaRetornaTermoBuscado() {
        assertModelAttributeValue(controller.busca("trabalho", 1), "termo", "trabalho");
    }

    @Test
    public void buscaRetornaResultadosParaAPagina() {
        doReturn(umConteudo)
                .when(buscador)
                .busca(of("emprego"), 0);

        assertModelAttributeValue(controller.busca("emprego", 1), "resultados", umConteudo);
    }

    @Test
    public void deveUsarBase0AoBuscarResultadosPaginados() {
        controller.busca("qualquer servico", 20);
        verify(buscador).busca(of("qualquer servico"), 19);
    }

    @Test
    public void retornaSugestoesDeBusca() throws Exception {
        doReturn(singletonList(PAGINA_ESTATICA_DE_TEMATICA.withNome("Seguro-desemprego")))
                .when(buscador)
                .buscaSemelhante(of("empreg"));

        String sugestao = controller.sugestao("empreg");

        assertThat(sugestao, is("[\"empreg\", [\"Seguro-desemprego\"]]"));
    }

    @Test
    public void deveExibirXmlDoOpenSearch() {
        assertThat(controller.openSearch().getViewName(), is("opensearch"));
    }

    @Test
    public void deveRedirecionarEmUrlsDeBuscaLegada() {
        assertThat(controller.search("blah"), is("redirect:/busca?q=blah"));
    }
}
