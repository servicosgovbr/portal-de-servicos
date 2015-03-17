package br.gov.servicos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("gds.conteudo")
@EnableConfigurationProperties
@Setter
@Getter
public class ConteudoConfig {
    Map<String, String> termos;
    public String mapeiaTermo(String termo) {
        return termos.getOrDefault(termo, termo);
    }
}

