package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class RobotsTxtControllerTest {

    RobotsTxtController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RobotsTxtController();
    }

    @Test
    public void devePermitirRobosComAFlagLigada() throws Exception {
        assertThat(controller.robotsTxt(new MockHttpServletRequest(), true),
                stringContainsInOrder(asList("Sitemap", "User-agent", "*", "Disallow:")));
    }

    @Test
    public void deveBanirRobosComAFlagDesligada() throws Exception {
        assertThat(controller.robotsTxt(new MockHttpServletRequest(), false),
                stringContainsInOrder(asList("User-agent", "*", "Disallow: /")));
    }
}
