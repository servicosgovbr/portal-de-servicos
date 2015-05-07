package br.gov.servicos.config;

import lombok.experimental.FieldDefaults;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_IMPORTADOR;
import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_PERSISTENTE;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus.GREEN;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ElasticSearchHealthIndicator implements HealthIndicator {

    AdminClient client;

    @Autowired
    public ElasticSearchHealthIndicator(Client client) {
        this.client = client.admin();
    }

    @Override
    public Health health() {
        try {
            ClusterHealthRequest request = new ClusterHealthRequest(GDS_IMPORTADOR, GDS_PERSISTENTE);
            ClusterHealthResponse response = client.cluster().health(request).actionGet();

            Health.Builder health = response.getStatus() != GREEN ? Health.down() : Health.up();

            health.withDetail("status", response.getStatus())
                    .withDetail("nodes", response.getNumberOfNodes());

            NodeInfo[] nodeInfos = client.cluster().nodesInfo(new NodesInfoRequest()).actionGet().getNodes();
            for (int i = 0; i < nodeInfos.length; i++) {
                health = health.withDetail("node-" + i, nodeInfos[i].getNode().getName());
            }

            return health.build();

        } catch(Exception e) {
            return Health.down().withException(e).build();
        }
    }
}
