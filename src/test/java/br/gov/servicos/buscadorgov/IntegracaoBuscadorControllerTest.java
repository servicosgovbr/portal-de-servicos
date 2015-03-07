package br.gov.servicos.buscadorgov;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.mock.web.MockHttpServletRequest;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class IntegracaoBuscadorControllerTest {

    @Mock
    ServicoRepository servicos;

    IntegracaoBuscadorController controller;

    @Before
    public void setUp() throws Exception {
        controller = new IntegracaoBuscadorController(servicos);
    }

    @Test
    public void retornaListaDeLinksParaServicos() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("foo");
        request.setServerPort(80);

        given(servicos.findAll())
                .willReturn(new FacetedPageImpl<>(asList(SERVICO)));

        ResultadoListarServicos listaDeServicos = controller.get(request);

        assertThat(listaDeServicos.getListaServicos(),
                is(not(emptyCollectionOf(Servico.class))));
    }

    @Test
    public void retornaDetalheDeServico() throws Exception {
        br.gov.servicos.servico.Servico servico = SERVICO.withId("svc");

        given(servicos.findOne("svc")).willReturn(servico);

        ResultadoDetalharServico r = controller.get("svc");
        assertThat(r.getServico(), is(servico));
    }

}