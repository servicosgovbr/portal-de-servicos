package br.gov.servicos.orgao;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import static br.gov.servicos.fixtures.TestData.CONTEUDO_HTML;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class OrgaoControllerTest {

    @Mock
    Markdown markdown;

    @Mock
    OrgaoRepository orgaos;

    @Mock
    ServicoRepository servicos;

    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(markdown, orgaos, servicos);
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaServicos() {
        given(markdown.toHtml(anyObject())).willReturn(CONTEUDO_HTML);
        given(servicos.findByOrgao(new Orgao().withId("receita-federal"))).willReturn(singletonList(SERVICO));

        assertModelAttributeValue(controller.orgao("receita-federal"), "resultados", singletonList(Conteudo.fromServico(SERVICO)));
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaConteudoDescritivo() {
        given(servicos.findByOrgao(new Orgao().withId("secretaria-da-receita-federal-do-brasil-rfb"))).willReturn(singletonList(SERVICO));
        given(markdown.toHtml(new ClassPathResource("conteudo/orgaos/secretaria-da-receita-federal-do-brasil-rfb.md"))).willReturn(CONTEUDO_HTML);

        assertModelAttributeValue(controller.orgao("secretaria-da-receita-federal-do-brasil-rfb"), "conteudo", CONTEUDO_HTML.withId("secretaria-da-receita-federal-do-brasil-rfb"));
    }

}
