package br.gov.servicos.frontend;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

public class RobotsTxtControllerTest {

    @Test
    public void retornaPermissoes() throws Exception {
        assertThat(new RobotsTxtController().robotsTxt(), 
                stringContainsInOrder(asList("User-agent", "*", "Disallow", "/")));
    }
}