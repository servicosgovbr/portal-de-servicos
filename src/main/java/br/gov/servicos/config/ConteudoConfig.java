package br.gov.servicos.config;

import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties("pds.conteudo")
@EnableConfigurationProperties
@FieldDefaults(level = PRIVATE)
public class ConteudoConfig {

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, URL> ouvidorias;

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, URL> sitesOficiais;

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, String> telefones;

    @Autowired
    Slugify slugify;

    @SneakyThrows
    public Optional<String> ouvidoria(String termo) {
        if (ouvidorias.containsKey(termo)) {
            return of(ouvidorias.get(termo).toString());
        }
        return empty();
    }

    @SneakyThrows
    public Optional<String> siteOficial(String termo) {
        if (sitesOficiais.containsKey(termo)) {
            return of(sitesOficiais.get(termo).toString());
        }
        return empty();
    }

    @SneakyThrows
    public Optional<String> telefone(String termo) {
        if (telefones.containsKey(termo)) {
            return of(telefones.get(termo));
        }
        return empty();
    }
}