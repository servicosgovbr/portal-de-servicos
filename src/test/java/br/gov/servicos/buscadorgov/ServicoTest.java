package br.gov.servicos.buscadorgov;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class ServicoTest {

    MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.setServerName("servicos");
        request.setServerPort(80);
    }

    @Test
    public void geraLinkParaDetalhesDoServico() throws Exception {
        assertThat(new Servico("abc", request).getLink(), is("http://servicos:80/xml/servico/abc"));
    }

    @Test
    public void naoTemDataDeAtualizacao() throws Exception {
        assertThat(new Servico("abc", request).getDataAtualizacao(), is(nullValue()));
    }

}
