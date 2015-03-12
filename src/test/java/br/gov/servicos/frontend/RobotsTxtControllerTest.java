package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class RobotsTxtControllerTest {

    RobotsTxtController robotsTxtController;

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
