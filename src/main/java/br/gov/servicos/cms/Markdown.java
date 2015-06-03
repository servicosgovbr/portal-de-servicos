package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.pegdown.PegDownProcessor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Markdown {

    PegDownProcessor pegdown;

    Markdown() {
        pegdown = new PegDownProcessor();
    }

    @Cacheable("conteudo")
    public ConteudoHtml toHtml(Resource resource) {
        if (!resource.exists()) {
            throw new ConteudoNaoEncontrado(resource.toString());
        }

        try {
            InputStreamReader input = new InputStreamReader(resource.getInputStream(), "UTF-8");

            try (BufferedReader br = new BufferedReader(input)) {
                String titulo = br.readLine();
                String conteudo = titulo + "\n" + br.lines().collect(joining("\n"));

                return new ConteudoHtml()
                        .withTitulo(titulo)
                        .withHtml(pegdown.markdownToHtml(conteudo));
            }
        } catch (IOException e) {
            throw new ConteudoNaoEncontrado(e);
        }
    }

}
