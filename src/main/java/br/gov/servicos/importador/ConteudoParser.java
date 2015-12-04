package br.gov.servicos.importador;

import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;
import static org.jsoup.Jsoup.parse;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Cacheable("conteudo-parser")
public class ConteudoParser {
    Markdown markdown;

    @Autowired
    public ConteudoParser(Markdown markdown) {
        this.markdown = markdown;
    }

    public String conteudo(Resource resource) {
        return parseResource(resource).select("p, ul, ol").text();
    }

    public String conteudo(String raw) {
        return parse(markdown.render(raw)).select("p, ul, ol").text();
    }

    public String textoPuro(String textoComMarkdown) {
        return parse(markdown.render(textoComMarkdown)).select("p, ul, ol").text();
    }

    public String conteudoHtml(Resource resource) {
        return parseResource(resource).toString();
    }

    public String conteudoHtml(String raw) {
        return parse(markdown.render(raw)).toString();
    }

    public String titulo(Resource resource) {
        return parseResource(resource).select("h2").text();
    }

    public String titulo(String raw) {
        return parse(markdown.toHtml(raw)).select("h2").text();
    }

    public String link(String source) {
        if (source == null) {
            return null;
        }
        if (source.startsWith("http://") || source.startsWith("https://")) {
            return source;
        }
        return parse(markdown.render(source)).select("a").attr("href");
    }

    private Document parseResource(Resource resource) {
        return parse(markdown.toHtml(resource));
    }
}
