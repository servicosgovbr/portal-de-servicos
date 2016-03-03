package br.gov.servicos.config;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import static br.gov.servicos.config.PortalDeServicosIndex.PORTAL_DE_SERVICOS_INDEX;
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

        try {
            if (es.indexExists(PORTAL_DE_SERVICOS_INDEX)) {
                long count = es.count(new NativeSearchQueryBuilder().withIndices(PORTAL_DE_SERVICOS_INDEX).build());
                health = health.up().withDetail(PORTAL_DE_SERVICOS_INDEX, format("ok (%d docs)", count));
            } else {
                health = health.down().withDetail(PORTAL_DE_SERVICOS_INDEX, "missing");
            }

        } catch (Exception e) {
            health = health.down().withDetail(PORTAL_DE_SERVICOS_INDEX, "exception").withException(e);
        }

        return health.build();
    }

}
