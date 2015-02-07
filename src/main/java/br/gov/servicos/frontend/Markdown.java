package br.gov.servicos.frontend;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.pegdown.PegDownProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Markdown {

    PegDownProcessor pegdown;

    public Markdown() {
        pegdown = new PegDownProcessor();
    }

    public String toHtml(String source) {
        return pegdown.markdownToHtml(source);
    }

    @SneakyThrows
    public String toHtml(ClassPathResource resource) {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))) {
            return toHtml(br.lines().parallel().collect(Collectors.joining("\n")));
        }
    }
}
