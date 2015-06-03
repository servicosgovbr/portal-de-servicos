package br.gov.servicos.importador;

import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;
import static org.jsoup.Jsoup.parse;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ConteudoParser {
    Markdown markdown;

    @Autowired
    public ConteudoParser(Markdown markdown) {
        this.markdown = markdown;
    }

    public String conteudo(String caminho) {
        Resource resource = new ClassPathResource(caminho);
        log.debug("Conteúdo {} encontrado em: {}", caminho, resource);
        return parse(markdown.toHtml(resource).getHtml()).select("p").text();
    }

}
