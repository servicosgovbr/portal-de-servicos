package br.gov.servicos;

import br.gov.servicos.config.ElasticsearchServicosConfig;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ElasticSearchTest {
    @Autowired
    ElasticsearchServicosConfig esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriarIndices();
    }

}
