package br.gov.servicos.setup;

import br.gov.servicos.config.PortalDeServicosIndex;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Component
public class SetupPortalElasticSearch {


    private ElasticsearchTemplate es;
    private PortalDeServicosIndex portalIndex;

    @Autowired
    public SetupPortalElasticSearch(ElasticsearchTemplate es, PortalDeServicosIndex portalIndex) {
        this.es = es;
        this.portalIndex = portalIndex;
    }

    @SneakyThrows
    public void setup() {
        es.deleteIndex(PortalDeServicosIndex.PORTAL_DE_SERVICOS_INDEX);

        portalIndex.recriar();
    }

}
