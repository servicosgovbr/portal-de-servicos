package br.gov.servicos.importador;

import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.cms.Markdown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConteudoParserTest {

    @Mock
    Markdown markdown;

    @Test
    public void deveExtrairTextoPuroDeHtml() throws Exception {
        given(markdown.toHtml(anyObject())).willReturn(
                new ConteudoHtml()
                        .withId("foo")
                        .withNome("Foo")
                        .withHtml("<html>" +
                                "<h2>Foo</h2>" +
                                "<p>Parágrafo um.</p>" +
                                "<ul><li>Ponto A.</li><li>Ponto B.</li></ul>" +
                                "<p>Parágrafo dois.</p>" +
                                "</html>")
        );

        String corpo = new ConteudoParser(markdown).conteudo("/conteudo/foo.md");

        assertThat(corpo, is("Parágrafo um. Ponto A. Ponto B. Parágrafo dois."));
    }

    @Test
    public void renderizaPrimeiroLink() throws Exception {
        given(markdown.render(anyString())).willReturn("<a href=\"http://example.com\">hello</a>");
        assertThat(new ConteudoParser(markdown).link("[hello](http://example.com)"), is("http://example.com"));
    }

    @Test
    public void renderizaPrimeiroLinkIgnorandoOutros() throws Exception {
        given(markdown.render(anyString())).willReturn("<a href=\"http://example.com\">hello</a><a href=\"http://examplez.com\">hello</a>");
        assertThat(new ConteudoParser(markdown).link("[hello](http://example.com)"), is("http://example.com"));
    }

    @Test
    public void renderizaLinkEmTextoPuro() throws Exception {
        given(markdown.render(anyString())).willReturn("http://example.com");
        assertThat(new ConteudoParser(markdown).link("http://example.com"), is("http://example.com"));
    }

    @Test
    public void repassaNulls() throws Exception {
        assertThat(new ConteudoParser(markdown).link(null), is(nullValue()));
        verify(markdown, never()).render(anyString());
    }
}