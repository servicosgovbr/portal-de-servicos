package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class MarkdownTest {

    private Markdown markdown;

    @Before
    public void setUp() {
        markdown = new Markdown();
    }

    @Test
    public void geraHtmlDeResource() {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")),
                containsString("<h1>Uma Página Qualquer</h1>"));
    }
}