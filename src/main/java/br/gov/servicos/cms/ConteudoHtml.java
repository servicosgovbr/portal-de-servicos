package br.gov.servicos.cms;

import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
public class ConteudoHtml {

    String id;
    String nome;
    String html;

    public ConteudoHtml() {
        this(null, null, null);
    }

    @Component
    public static class Formatter implements org.springframework.format.Formatter<ConteudoHtml> {

        Markdown markdown;
        Slugify slugify;

        @Autowired
        public Formatter(Markdown markdown, Slugify slugify) {
            this.markdown = markdown;
            this.slugify = slugify;
        }

        @Override
        public ConteudoHtml parse(String id, Locale locale) throws ParseException {
            String safe = this.slugify.slugify(id);
            return markdown.toHtml(new ClassPathResource(format("/conteudo/%s.md", safe))).withId(safe);
        }

        @Override
        public String print(ConteudoHtml html, Locale locale) {
            return html.getId();
        }
    }
}
