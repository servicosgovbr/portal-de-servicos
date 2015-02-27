package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.pegdown.PegDownProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class Markdown {

    PegDownProcessor pegdown;

    Markdown() {
        pegdown = new PegDownProcessor();
    }

    public String toHtml(ClassPathResource resource) {
        if (!resource.exists()) {
            throw new ConteudoNaoEncontrado();
        }

        try {
            InputStreamReader input = new InputStreamReader(resource.getInputStream(), "UTF-8");

            try (BufferedReader br = new BufferedReader(input)) {
                String conteudo = br.lines().collect(joining("\n"));
                return pegdown.markdownToHtml(conteudo);
            }
        } catch (IOException e) {
            throw new ConteudoNaoEncontrado(e);
        }
    }

}
