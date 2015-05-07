package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class RobotsTxtControllerTest {

    RobotsTxtController controller;

    MockHttpServletRequest request = new MockHttpServletRequest();

    @Before
    public void setUp() throws Exception {
        controller = new RobotsTxtController();

        request.setScheme("http");
        request.setServerName("servicos.gov.br");
        request.setRequestURI("/robots.txt");
    }

    @Test
    public void devePermitirRobosComAFlagLigada() throws Exception {
        assertThat(controller.robotsTxt(request, true),
                stringContainsInOrder(asList("Sitemap", "http://servicos.gov.br/sitemap.xml", "User-agent", "*", "Disallow:")));
    }

    @Test
    public void deveBanirRobosComAFlagDesligada() throws Exception {
        assertThat(controller.robotsTxt(request, false),
                stringContainsInOrder(asList("User-agent", "*", "Disallow: /")));
    }
}
