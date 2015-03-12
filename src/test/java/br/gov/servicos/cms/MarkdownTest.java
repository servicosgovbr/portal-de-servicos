package br.gov.servicos.cms;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class MarkdownTest {

    Markdown markdown;

    @Before
    public void setUp() {
        markdown = new Markdown();
    }

    @Test
    public void geraHtmlDeResource() {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")).getHtml(),
                containsString("<h1>Uma Página Qualquer</h1>"));
    }

    @Test
    public void extraiTituloDaPaginaEmMarkdown() {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")).getTitulo(),
                containsString("Uma Página Qualquer"));
    }
}
