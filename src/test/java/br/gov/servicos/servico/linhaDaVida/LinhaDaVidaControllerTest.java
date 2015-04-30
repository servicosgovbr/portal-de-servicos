package br.gov.servicos.servico.linhaDaVida;

import br.gov.servicos.busca.Buscador;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.CONTEUDO;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
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

    List<Servico> umServico = singletonList(SERVICO);
    LinhaDaVidaController controller;

    @Before
    public void setUp() {
        controller = new LinhaDaVidaController(buscador, markdown);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaServicos() {
        doReturn(umServico)
                .when(buscador)
                .buscaPor("linhasDaVida.id", of("Aposentar-se"));

        assertCompareListModelAttribute(controller.linhaDaVida("Aposentar-se"), "resultados", umServico);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaConteudoDescritivo() {
        doReturn(CONTEUDO)
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/linhas-da-vida/aposentar-se.md"));

        assertModelAttributeValue(controller.linhaDaVida("aposentar-se"), "conteudo", CONTEUDO);
    }

}
