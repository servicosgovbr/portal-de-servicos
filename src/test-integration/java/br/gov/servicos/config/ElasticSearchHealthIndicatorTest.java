package br.gov.servicos.config;

import br.gov.servicos.Main;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class ElasticSearchHealthIndicatorTest {

    @Autowired
    ElasticSearchHealthIndicator healthIndicator;

    @Test @Ignore("teste instável")
    public void indicaStatusDoCluster() throws Exception {
        assertThat(healthIndicator.health().getStatus().getCode(), is("UP"));
    }
}