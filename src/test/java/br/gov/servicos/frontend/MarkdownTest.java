package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MarkdownTest {

    private Markdown markdown;

    @Before
    public void setUp() throws Exception {
        markdown = new Markdown();
    }

    @Test
    public void geraHtmlDeString() throws Exception {
        assertThat(markdown.toHtml("Olá"), is("<p>Olá</p>"));
    }

    @Test
    public void geraHtmlDeResource() throws Exception {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")),
                containsString("<h1>Uma Página Qualquer</h1>"));
    }
}