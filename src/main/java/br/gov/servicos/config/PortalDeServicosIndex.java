package br.gov.servicos.config;

import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.cms.PaginaTematica;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static br.gov.servicos.foundation.IO.read;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PortalDeServicosIndex {

    public static final String PORTAL_DE_SERVICOS_INDEX = "portal-de-servicos";

    private static final String SETTINGS = "/elasticsearch/settings.json";

    ElasticsearchTemplate es;

    @Autowired
    public PortalDeServicosIndex(ElasticsearchTemplate es) {
        this.es = es;
    }

    private static String settings() {
        return read(new ClassPathResource(SETTINGS));
    }

    @CacheEvict(value = {
            "buscas",
            "html",
            "pagina-estatica",
            "pagina-tematica",
            "areas-de-interesse",
            "servicos-destaque",
            "orgaos",
            "servicos-por-area-de-interesse",
            "servicos-por-orgao",
            "servicos-por-segmento-da-sociedade"
    }, allEntries = true)
    public void recriar() throws IOException {
        recriarIndiceImportador();

        es.putMapping(OrgaoXML.class);
        es.putMapping(ServicoXML.class);
        es.putMapping(PaginaEstatica.class);
        es.putMapping(PaginaTematica.class);
    }

    private void recriarIndiceImportador() throws IOException {
        if (es.indexExists(PORTAL_DE_SERVICOS_INDEX)) {
            es.deleteIndex(PORTAL_DE_SERVICOS_INDEX);
        }
        es.createIndex(PORTAL_DE_SERVICOS_INDEX, settings());
    }

}
