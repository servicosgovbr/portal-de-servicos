package br.gov.servicos.config;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static br.gov.servicos.Profiles.NOT;
import static br.gov.servicos.Profiles.STANDALONE;

@Configuration
@Profile(NOT + STANDALONE)
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchClusterConfig {

    @Autowired
    private ElasticsearchProperties properties;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(esClient());
    }

    @Bean
    public Client esClient() throws Exception {
        TransportClientFactoryBean fb = new TransportClientFactoryBean();
        fb.setClusterName(this.properties.getClusterName());
        fb.setClusterNodes(this.properties.getClusterNodes());
        fb.afterPropertiesSet();

        return fb.getObject();
    }
}
