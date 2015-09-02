package br.gov.servicos.orgao;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
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

    @Mock
    ConteudoRepository conteudos;

    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(orgaos, servicos, conteudos);
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaServicos() {
        given(servicos.findByOrgao(new Orgao().withId("receita-federal"))).willReturn(singletonList(SERVICO));
        given(conteudos.findOne("receita-federal")).willReturn(TestData.CONTEUDO);

        assertModelAttributeValue(controller.orgao("receita-federal"), "resultados", singletonList(Conteudo.fromServico(SERVICO)));
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaConteudoDescritivo() {
        given(servicos.findByOrgao(new Orgao().withId("secretaria-da-receita-federal-do-brasil-rfb"))).willReturn(singletonList(SERVICO));
        given(conteudos.findOne("secretaria-da-receita-federal-do-brasil-rfb")).willReturn(TestData.CONTEUDO);

        assertModelAttributeValue(controller.orgao("secretaria-da-receita-federal-do-brasil-rfb"),
                "conteudo",
                TestData.CONTEUDO);
    }

}
