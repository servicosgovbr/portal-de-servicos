package br.gov.servicos.piwik;

import br.gov.servicos.Main;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class PiwikClientTest {

    @Autowired
    PiwikClient piwikClient;

    @Test
    @Ignore("Configure o token em GDS_PIWIK_TOKEN")
    public void deveRetonarUrlsParaODia04042015() throws Exception {
        String date = "04-04-2015";
        String period = "day";
        List<PiwikPage> urls = piwikClient.getPageUrls(period, date);

        assertEquals(urls.size(), 100);
        assertEquals(urls.get(0), new PiwikPage()
                .withLabel("/index")
                .withVisitors(13741L)
                .withUniqueVisitors(8693L));

        assertEquals(urls.get(1), new PiwikPage()
                .withLabel("repositorioServico")
                .withVisitors(3855L));
    }

}