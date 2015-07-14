package br.gov.servicos.servico.linhaDaVida;

import br.gov.servicos.busca.Buscador;
import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.CONTEUDO;
import static br.gov.servicos.fixtures.TestData.CONTEUDO_HTML;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.assertCompareListModelAttribute;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class LinhaDaVidaControllerTest {

    @Mock
    Buscador buscador;

    @Mock
    Markdown markdown;

    List<Conteudo> umConteudo = singletonList(CONTEUDO);
    LinhaDaVidaController controller;

    @Before
    public void setUp() {
        controller = new LinhaDaVidaController(buscador, markdown);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaServicos() {
        given(markdown.toHtml(anyObject())).willReturn(CONTEUDO_HTML);

        doReturn(umConteudo)
                .when(buscador)
                .buscaConteudosPor("eventosDaLinhaDaVida.id", of("Aposentadoria"));

        assertCompareListModelAttribute(controller.linhaDaVida("Aposentadoria"), "resultados", umConteudo);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaConteudoDescritivo() {
        doReturn(CONTEUDO_HTML)
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/linhas-da-vida/aposentadoria.md"));

        assertModelAttributeValue(controller.linhaDaVida("aposentadoria"), "conteudo", CONTEUDO_HTML.withId("aposentadoria"));
    }

}
