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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class PiwikIntegrationTest {

    @Autowired
    PiwikClient piwikClient;

    @Test
    @Ignore("Configure o token em GDS_PIWIK_TOKEN")
    public void deveRetonarUrlsParaODia04042015() throws Exception {
        List<PiwikPage> pages = piwikClient.getPageUrls("day", "04-04-2015");

        assertThat(pages.get(0).getPath(), is(Optional.of("/")));

        assertThat(pages.stream()
                .map(PiwikPage::getIdServico)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(s -> s.equals("consulta-situacao-do-requerimento-de-beneficio"))
                .count(), is(1));
    }

}
