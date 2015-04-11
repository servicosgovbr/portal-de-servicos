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
public class PiwikTest {

    @Autowired
    PiwikClient piwikClient;

    @Test
    @Ignore("Configure o token em GDS_PIWIK_TOKEN")
    public void deveRetonarUrlsParaODia04042015() throws Exception {
        List<PiwikPage> pages = piwikClient.getPageUrls("day", "04-04-2015");

        assertEquals(pages.get(0).getPath(), "/");
        assertEquals(pages
                .stream()
                .filter(p -> p.getPath().equals("/repositorioServico/consulta-situacao-do-requerimento-de-beneficio"))
                .count(), 1);
    }

}
