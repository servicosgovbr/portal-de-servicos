package br.gov.servicos.orgao;

import br.gov.servicos.cms.Markdown;
import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.OrgaoXML;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    OrgaoRepositoryUtil orgaosUtil;

    @Mock
    ServicoRepository servicos;

    OrgaoController controller;

    @Before
    public void setUp() {
        controller = new OrgaoController(orgaos, orgaosUtil, servicos);
    }

    @Test
    public void exibicaoDeAreaDeInteresseRetornaServicos() {
        given(servicos.findByOrgao(new OrgaoXML().withId("receita-federal"))).willReturn(singletonList(SERVICO));
        given(orgaos.findOne("receita-federal")).willReturn(TestData.PAGINA_ORGAO);

        assertModelAttributeValue(controller.orgao(new OrgaoXML().withId("receita-federal")), "resultados", singletonList(PaginaEstatica.fromServico(SERVICO)));
    }
}
