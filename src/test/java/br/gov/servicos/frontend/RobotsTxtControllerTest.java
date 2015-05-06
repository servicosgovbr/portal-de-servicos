package br.gov.servicos.frontend;

import br.gov.servicos.foundation.http.BaseUrl;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class RobotsTxtControllerTest {

    RobotsTxtController controller;

    @Mock
    BaseUrl baseUrl;

    @Before
    public void setUp() throws Exception {
        controller = new RobotsTxtController();

        given(baseUrl.and("/sitemap.xml")).willReturn("http://servicos.gov.br/sitemap.xml");
    }

    @Test
    public void devePermitirRobosComAFlagLigada() throws Exception {
        assertThat(controller.robotsTxt(baseUrl, true),
                stringContainsInOrder(asList("Sitemap", "http://servicos.gov.br/sitemap.xml", "User-agent", "*", "Disallow:")));
    }

    @Test
    public void deveBanirRobosComAFlagDesligada() throws Exception {
        assertThat(controller.robotsTxt(baseUrl, false),
                stringContainsInOrder(asList("User-agent", "*", "Disallow: /")));
    }
}
