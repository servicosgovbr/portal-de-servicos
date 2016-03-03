package br.gov.servicos.config;

import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class SlugifyConfig {

    @Bean
    public Slugify slugify() throws IOException {
        return new Slugify();
    }

    @SneakyThrows
    public static String slugify(String str) {
        Slugify s = new Slugify();
        s.setCustomReplacements(Collections.singletonMap("_", "-"));
        return s.slugify(str);
    }

}
