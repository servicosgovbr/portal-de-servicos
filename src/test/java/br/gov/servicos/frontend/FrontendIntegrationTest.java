package br.gov.servicos.frontend;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@WebIntegrationTest(randomPort = true)
public class FrontendIntegrationTest {

    @Value("${local.server.port}")
    int port;

    @Test
    public void abrePaginaInfoComSucesso() throws Exception {
        realizaRequisicaoComSucesso(String.format("http://localhost:%d/info", port));
    }

    private void realizaRequisicaoComSucesso(String url) throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        assertThat(c.getResponseCode(), is(200));
    }
}
