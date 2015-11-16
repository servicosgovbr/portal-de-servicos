package br.gov.servicos.config;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Configuration
@ConfigurationProperties("pds.destaques")
@FieldDefaults(level = PRIVATE)
public class AreasDeInteresseDestaqueConfig {
    List<String> areasDeInteresse;
}
