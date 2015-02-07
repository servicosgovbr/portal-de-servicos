package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.core.io.ClassPathResource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConteudoControllerTest {

    @Mock
    Markdown markdown;

    ConteudoController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new ConteudoController(markdown);
    }

    @Test(expected = NotFoundException.class)
    public void retorna404QuandoArquivoNaoExiste() throws Exception {
        controller.conteudo("arquivo-nao-existente");
    }

    @Test
    public void retornaView() throws Exception {
        assertThat(controller.conteudo("pagina").getViewName(), is("conteudo"));
    }

    @Test
    public void retornaHtmlNoModel() throws Exception {
        given(markdown.toHtml(any(ClassPathResource.class))).willReturn("<h1>Conteúdo</h1>");
        assertThat(controller.conteudo("pagina").getModel().get("conteudo"), is("<h1>Conteúdo</h1>"));
    }
    
}