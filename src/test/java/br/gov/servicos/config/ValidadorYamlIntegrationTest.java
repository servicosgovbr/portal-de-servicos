package br.gov.servicos.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStreamReader;

import static java.nio.charset.Charset.defaultCharset;

public class ValidadorYamlIntegrationTest {

    private Yaml yaml;

    @Before
    public void setUp() throws Exception {
        yaml = new Yaml();
    }

    @Test
    public void deveSerVálido() throws Exception {
        Resource resource = new FileSystemResource("src/main/resources/application.yaml");
        yaml.parse(new InputStreamReader(resource.getInputStream(), defaultCharset()));
    }
}
