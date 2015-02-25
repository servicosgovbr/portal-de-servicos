package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

public class RobotsTxtControllerTest {

    private RobotsTxtController robotsTxtController;

    @Before
    public void setUp() throws Exception {
        robotsTxtController = new RobotsTxtController();
    }

    @Test
    public void retornaPermissoes() throws Exception {
        assertThat(robotsTxtController.robotsTxt(),
                stringContainsInOrder(asList("User-agent", "*", "Disallow", "/")));
    }
}