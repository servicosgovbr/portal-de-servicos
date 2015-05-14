package br.gov.servicos.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStreamReader;

public class ValidadorYamlTest {

    private Yaml yaml;

    @Before
    public void setUp() throws Exception {
        yaml = new Yaml();
    }

    @Test
    public void deveSerVálido() throws Exception {
        ClassPathResource resource = new ClassPathResource("file:src/main/resources/application.yaml");
        yaml.parse(new InputStreamReader(resource.getInputStream()));
    }
}
