package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorParaConteudoDePaginas {

    Markdown markdown;
    ConteudoParser parser;

    @Autowired
    public ImportadorParaConteudoDePaginas(Markdown markdown, ConteudoParser parser) {
        this.markdown = markdown;
        this.parser = parser;
    }

    public Stream<Conteudo> importar() {
        return asList("acessibilidade", "documento-de-arrecadacao-de-receitas-federais-darf", "perguntas-frequentes")
                .stream()
                .map(id -> {
                    String caminho = format("/conteudo/%s.md", id);
                    return new Conteudo()
                            .withId(id)
                            .withTitulo(markdown.toHtml(new ClassPathResource(caminho)).getTitulo())
                            .withTipoConteudo("conteudo")
                            .withConteudo(parser.conteudo(caminho));
                });
    }

}
