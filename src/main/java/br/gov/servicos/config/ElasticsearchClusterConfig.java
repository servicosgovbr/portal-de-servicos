package br.gov.servicos.config;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static br.gov.servicos.Profiles.CLUSTER;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.gov.servicos")
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchClusterConfig {

    @Autowired
    private ElasticsearchProperties properties;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
        return new ElasticsearchTemplate(client);
    }

    @Bean
    @Primary
    @Profile(CLUSTER)
    public Client clusteredClient() throws Exception {
        TransportClientFactoryBean fb = new TransportClientFactoryBean();
        fb.setClusterName(this.properties.getClusterName());
        fb.setClusterNodes(this.properties.getClusterNodes());
        fb.afterPropertiesSet();

        return fb.getObject();
    }

    @Bean
    @Primary
    @Profile("!" + CLUSTER)
    public Client embeddedClient() throws Exception {
        return nodeBuilder().local(true).node().client();
    }
}
