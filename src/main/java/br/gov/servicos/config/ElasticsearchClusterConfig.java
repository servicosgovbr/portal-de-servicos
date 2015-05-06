package br.gov.servicos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "br.gov.servicos")
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchClusterConfig {

}
