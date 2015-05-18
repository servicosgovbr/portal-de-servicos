package br.gov.servicos.config;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_IMPORTADOR;
import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_PERSISTENTE;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GuiaDeServicosIndexHealthIndicator implements HealthIndicator {

    ElasticsearchTemplate es;

    @Autowired
    public GuiaDeServicosIndexHealthIndicator(ElasticsearchTemplate es) {
        this.es = es;
    }

    @Override
    public Health health() {
        Health.Builder health = Health.unknown();

        health = indice(health, GDS_IMPORTADOR);
        health = indice(health, GDS_PERSISTENTE);

        return health.build();
    }

    private Health.Builder indice(Health.Builder health, String indice) {
        try {

            if (es.indexExists(indice)) {
                long count = es.count(new NativeSearchQueryBuilder().withIndices(indice).build());
                health = health.up().withDetail(indice, format("ok (%d docs)", count));
            } else {
                health = health.down().withDetail(indice, "missing");
            }
            return health;
        } catch (Exception e) {
            return health.down().withDetail(indice, "exception").withException(e);
        }
    }
}
