package br.gov.servicos.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties("gds.conteudo")
@EnableConfigurationProperties
@FieldDefaults(level = PRIVATE)
public class ConteudoConfig {

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, String> linhasDaVida;


    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, String> orgaos;

    public String linhaDaVida(String termo) {
        return linhasDaVida.getOrDefault(termo, termo);
    }

    public String orgao(String termo) {
        return orgaos.getOrDefault(termo, termo);
    }
}

