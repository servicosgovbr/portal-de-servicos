package br.gov.servicos.buscadorgov;

import br.gov.servicos.servico.Servico;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResultadoListarServicosTest {

    MockHttpServletRequest request;
    ResultadoListarServicos resultadoListarServicos;

    @Before
    public void setUp() throws Exception {
        Servico s1 = SERVICO.withId("s1-id");
        Servico s2 = SERVICO.withId("s2-id");
        request = new MockHttpServletRequest();
        request.setServerName("foo");
        request.setServerPort(80);
        resultadoListarServicos = new ResultadoListarServicos(Arrays.asList(s1, s2), request);
    }

    @Test
    public void converteListaDeServicosEmListaDeLinksParaServicos() throws Exception {
        List<br.gov.servicos.buscadorgov.Servico> links = resultadoListarServicos.getListaServicos();

        assertThat(links.size(),
                is(2));

        assertThat(links.get(0).getLink(),
                is("http://foo:80/xml/servico/s1-id"));

        assertThat(links.get(1).getLink(),
                is("http://foo:80/xml/servico/s2-id"));
    }
}