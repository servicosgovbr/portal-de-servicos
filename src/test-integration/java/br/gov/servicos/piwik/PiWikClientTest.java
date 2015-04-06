package br.gov.servicos.piwik;

import br.gov.servicos.config.PiWikConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PiWikClientTest {

    private PiWikClient piWikClient;

    @Before
    public void setup() {
        piWikClient = new PiWikConfig().piWikClient();
    }

    @Test
    public void deveRetonarUrlsParaODia04042015() throws Exception {
        String date = "04-04-2015";
        String period = "day";
        List<PiWikPage> urls = piWikClient.getPageUrls(period, date);

        assertEquals(urls.size(), 100);
        assertEquals(urls.get(0), new PiWikPage()
                .withLabel("/index")
                .withVisitors(13741L)
                .withUniqueVisitors(8693L));

        assertEquals(urls.get(1), new PiWikPage()
                .withLabel("repositorioServico")
                .withVisitors(3855L));
    }

}