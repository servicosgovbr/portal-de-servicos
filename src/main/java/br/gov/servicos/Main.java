package br.gov.servicos;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class Main {

    @Configuration
    @Profile("cluster")
    @EnableConfigurationProperties(ElasticsearchProperties.class)
    public static class ElasticsearchConfiguration {

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
            fb.setClusterNodes("localhost:9300");
            fb.afterPropertiesSet();

            return fb.getObject();
        }
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
