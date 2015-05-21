package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class MarkdownTest {

    Markdown markdown;

    @Mock
    ClassPathResource resource;

    @Before
    public void setUp() {
        markdown = new Markdown();
    }

    @Test
    public void deveGerarHtmlDeResource() {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")).getHtml(),
                containsString("<h1>Uma Página Qualquer</h1>"));
    }

    @Test
    public void deveExtrairTituloDaPaginaEmMarkdown() {
        assertThat(markdown.toHtml(new ClassPathResource("/conteudo/pagina.md")).getTitulo(),
                containsString("Uma Página Qualquer"));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void deveJogarExcecaoQuandoConteudoNaoExiste() {
        markdown.toHtml(new ClassPathResource("/conteudo/404.md"));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void deveJogarExcecaoQuandoConteudoNaoPodeSerAcessado() throws Exception {
        given(resource.getInputStream()).willThrow(new IOException("mock"));
        markdown.toHtml(resource);
    }
}
