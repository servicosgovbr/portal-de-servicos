package br.gov.servicos.foundation.http;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BaseUrlTest {

    private BaseUrl baseUrl;
    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();

        baseUrl = new BaseUrl(request);
    }

    @Test
    public void deveAdicionarUrlBaseAString() throws Exception {
        request.setScheme("http");
        request.setServerName("servicos.gov.br");
        request.setRequestURI("/servico/foo");

        assertThat(baseUrl.and("/servico/bar"), is("http://servicos.gov.br/servico/bar"));
    }

    @Test
    public void deveNormalizarBarrasIniciais() throws Exception {
        request.setScheme("http");
        request.setServerName("servicos.gov.br");
        request.setRequestURI("/servico/foo");

        assertThat(baseUrl.and("servico/bar"), is("http://servicos.gov.br/servico/bar"));
        assertThat(baseUrl.and(""), is("http://servicos.gov.br/"));
        assertThat(baseUrl.and("/"), is("http://servicos.gov.br/"));
    }

    @Test
    public void deveAdicionarUrlBaseAStringQuandoHttps() throws Exception {
        request.setScheme("https");
        request.setServerName("servicos.gov.br");
        request.setServerPort(443);
        request.setRequestURI("/servico/foo");

        assertThat(baseUrl.and("/servico/bar"), is("https://servicos.gov.br/servico/bar"));
    }
}