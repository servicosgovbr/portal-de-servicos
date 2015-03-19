package br.gov.servicos.orgao;

import br.gov.servicos.busca.Buscador;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.ModelAndViewAssert;

import java.util.Arrays;
import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class OrgaoControllerTest {

    @Mock
    Buscador buscador;

    @Mock
    Markdown markdown;

    @Mock
    OrgaoRepository orgaos;

    List<Servico> umServico = Arrays.asList(SERVICO);
    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(buscador, markdown, orgaos);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaServicos() {
        Mockito.doReturn(umServico)
                .when(buscador)
                .buscaSemelhante(of("receita-federal"), "prestador.id", "responsavel.id");

        ModelAndViewAssert.assertCompareListModelAttribute(controller.orgao("receita-federal"), "resultados", umServico);
    }

    @Test
    public void exibicaoDeLinhaDaVidaRetornaConteudoDescritivo() {
        Mockito.doReturn(TestData.CONTEUDO)
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/orgaos/secretaria-da-receita-federal-do-brasil-rfb.md"));

        ModelAndViewAssert.assertModelAttributeValue(controller.orgao("secretaria-da-receita-federal-do-brasil-rfb"), "conteudo", TestData.CONTEUDO);
    }

}
