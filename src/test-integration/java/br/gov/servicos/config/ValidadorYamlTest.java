package br.gov.servicos.config;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;

public class ValidadorYamlTest {

    @Test
    public void deveSerVálido() throws Exception {
        new org.yaml.snakeyaml.Yaml().parse(
                new InputStreamReader(
                        new ClassPathResource("file:src/main/resources/application.yaml").getInputStream()));
    }
}
