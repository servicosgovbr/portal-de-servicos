package br.gov.servicos.frontend;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FrontendTest {

    @Value("${local.server.port}")
    int port;

    @Test
    public void realizaRequisicoesComSucesso() throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(String.format("http://localhost:%d/info", port)).openConnection();
        assertThat(c.getResponseCode(), is(200));
    }
}