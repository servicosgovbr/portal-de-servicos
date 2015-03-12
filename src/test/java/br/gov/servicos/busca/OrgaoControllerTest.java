package br.gov.servicos.busca;

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
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.assertCompareListModelAttribute;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class OrgaoControllerTest {

    @Mock
    Buscador buscador;

    @Mock
    Markdown markdown;

    List<Servico> umServico = asList(SERVICO);
    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(buscador, markdown);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaServicos() {
        doReturn(umServico)
                .when(buscador)
                .buscaSemelhante(of("receita-federal"), "prestador.id", "responsavel.id");

        assertCompareListModelAttribute(controller.orgao("receita-federal"), "resultados", umServico);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaConteudoDescritivo() {
        doReturn(CONTEUDO)
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/orgaos/receita-federal.md"));

        assertModelAttributeValue(controller.orgao("receita-federal"), "conteudo", CONTEUDO);
    }

}
