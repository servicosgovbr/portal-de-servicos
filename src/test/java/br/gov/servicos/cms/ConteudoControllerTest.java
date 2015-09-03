package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ConteudoControllerTest {

    ConteudoController controller;

    @Before
    public void setUp() {
        controller = new ConteudoController();
    }

    @Test
    public void redirecionaParaAPaginaDeConteudo() throws ConteudoNaoEncontrado {
        assertViewName(controller.conteudo(new ConteudoHtml().withId("pagina")), "conteudo");
    }

}
