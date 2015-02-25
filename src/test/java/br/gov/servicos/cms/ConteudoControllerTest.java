package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
public class ConteudoControllerTest {

    @Mock
    private Markdown markdown;
    private ConteudoController controller;

    @Before
    public void setUp() {
        doReturn("<h1>Conteúdo</h1>")
                .when(markdown)
                .toHtml(any(ClassPathResource.class));

        controller = new ConteudoController(markdown);
    }

    @Test
    public void redirecionaParaAPaginaDeConteudo() throws ConteudoNaoEncontrado {
        assertViewName(controller.conteudo("pagina"), "conteudo");
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void retorna404QuandoArquivoNaoExiste() throws ConteudoNaoEncontrado {
        controller.conteudo("arquivo-nao-existente");
    }

    @Test
    public void compilaOMarkdownDoConteudo() throws ConteudoNaoEncontrado {
        assertModelAttributeValue(controller.conteudo("pagina"), "conteudo", "<h1>Conteúdo</h1>");
    }

}