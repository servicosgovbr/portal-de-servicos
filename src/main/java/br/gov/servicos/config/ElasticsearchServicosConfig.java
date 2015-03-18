package br.gov.servicos.config;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.metricas.Feedback;
import br.gov.servicos.servico.Servico;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static br.gov.servicos.config.ElasticSearchIndexes.GUIA_DE_SERVICOS;
import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ElasticsearchServicosConfig {

    static final String SETTINGS = "/elasticsearch/settings.json";

    ElasticsearchTemplate es;

    @Autowired
    public ElasticsearchServicosConfig(ElasticsearchTemplate es) {
        this.es = es;
    }

    private static String settings() throws IOException {
        ClassPathResource resource = new ClassPathResource(SETTINGS);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), defaultCharset());

        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(joining("\n"));
        }
    }

    public void recriarIndices() throws IOException {
        if (es.indexExists(GUIA_DE_SERVICOS)) {
            es.deleteIndex(GUIA_DE_SERVICOS);
        }
        es.createIndex(GUIA_DE_SERVICOS, settings());
        es.putMapping(Busca.class);
        es.putMapping(Servico.class);
        es.putMapping(Feedback.class);
    }

}
