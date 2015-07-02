package br.gov.servicos.config;

import br.gov.servicos.importador.MapaVcge20;
import com.github.slugify.Slugify;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Vcge20Config {

    @Bean
    @SneakyThrows
    public MapaVcge20 getMapaVcge20() {
        return new MapaVcge20(new ClassPathResource("areas-de-interesse.csv"), new Slugify());
    }
}
