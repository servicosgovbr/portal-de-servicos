package br.gov.servicos;

import br.gov.servicos.config.GuiaDeServicosIndex;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ElasticSearchTest {
    @Autowired
    GuiaDeServicosIndex esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriar();
    }

}
