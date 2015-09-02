package br.gov.servicos.orgao;

import br.gov.servicos.busca.Buscador;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.CONTEUDO_HTML;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
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

    @Mock
    OrgaoRepository orgaos;

    @Mock
    ServicoRepository servicos;

    List<Servico> umServico = singletonList(SERVICO);
    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(buscador, markdown, orgaos, servicos);
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaServicos() {
        given(markdown.toHtml(anyObject())).willReturn(CONTEUDO_HTML);

        doReturn(umServico)
                .when(servicos)
                .findByOrgao(new Orgao().withId("receita-federal"));

        assertCompareListModelAttribute(controller.orgao("receita-federal"), "resultados", umServico);
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaConteudoDescritivo() {
        doReturn(CONTEUDO_HTML)
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/orgaos/secretaria-da-receita-federal-do-brasil-rfb.md"));

        assertModelAttributeValue(controller.orgao("secretaria-da-receita-federal-do-brasil-rfb"), "conteudo", CONTEUDO_HTML.withId("secretaria-da-receita-federal-do-brasil-rfb"));
    }

}
