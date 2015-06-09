package br.gov.servicos.config;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static br.gov.servicos.config.PortalDeServicosIndex.PERSISTENTE;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PortalDeServicosIndexHealthIndicator implements HealthIndicator {

    ElasticsearchTemplate es;

    @Autowired
    public PortalDeServicosIndexHealthIndicator(ElasticsearchTemplate es) {
        this.es = es;
    }

    @Override
    public Health health() {
        Health.Builder health = Health.unknown();

        health = indice(health, IMPORTADOR);
        health = indice(health, PERSISTENTE);

        return health.build();
    }

    private Health.Builder indice(Health.Builder health, String indice) {
        Health.Builder result = health;
        try {

            if (es.indexExists(indice)) {
                long count = es.count(new NativeSearchQueryBuilder().withIndices(indice).build());
                result = health.up().withDetail(indice, format("ok (%d docs)", count));
            } else {
                result = health.down().withDetail(indice, "missing");
            }
            return result;

        } catch (Exception e) {
            return result.down().withDetail(indice, "exception").withException(e);
        }
    }
}
