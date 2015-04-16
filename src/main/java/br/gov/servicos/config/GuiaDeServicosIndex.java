package br.gov.servicos.config;

import br.gov.servicos.metricas.Feedback;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class GuiaDeServicosIndex {

    public static final String GDS_IMPORTADOR = "gds-importador";
    public static final String GDS_PERSISTENTE = "gds-persistente";

    private static final String SETTINGS = "/elasticsearch/settings.json";

    ElasticsearchTemplate es;

    @Autowired
    public GuiaDeServicosIndex(ElasticsearchTemplate es) {
        this.es = es;
    }

    private static String settings() throws IOException {
        ClassPathResource resource = new ClassPathResource(SETTINGS);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), defaultCharset());

        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(joining("\n"));
        }
    }

    public void recriar() throws IOException {
        recriarIndiceImportador();
        criarIndicePersistenteSeNaoExistir();

        es.putMapping(Servico.class);
        es.putMapping(Feedback.class);
    }

    private void criarIndicePersistenteSeNaoExistir() throws IOException {
        if (!es.indexExists(GDS_PERSISTENTE))
            es.createIndex(GDS_PERSISTENTE, settings());
    }

    private void recriarIndiceImportador() throws IOException {
        if (es.indexExists(GDS_IMPORTADOR)) {
            es.deleteIndex(GDS_IMPORTADOR);
        }
        es.createIndex(GDS_IMPORTADOR, settings());
    }

}
