package br.gov.servicos.frontend;

import br.gov.servicos.cms.ConteudoController;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.orgao.OrgaoController;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.ServicoXML;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.ModelAndViewAssert.assertAndReturnModelAttributeOfType;

@RunWith(MockitoJUnitRunner.class)
public class MioloControllerTest {

    @Mock
    ConteudoController conteudos;

    @Mock
    OrgaoController orgaos;

    MioloController miolo;

    @Before
    public void setUp() throws Exception {
        miolo = new MioloController(orgaos, conteudos);
    }

    @Test
    public void mioloParaServicos() throws Exception {
        assertAndReturnModelAttributeOfType(miolo.getHtml(TestData.SERVICO), "servico", ServicoXML.class);
    }

    @Test
    public void mioloParaConteudos() throws Exception {
        Map<String, Object> model = new HashMap<>();
        given(conteudos.paginaTematica(TestData.PAGINA_TEMATICA)).willReturn(new ModelAndView("conteudo", model));

        assertThat(miolo.getHtml(TestData.PAGINA_TEMATICA).getModel(), is(model));
    }

    @Test
    public void mioloParaOrgaos() throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("foo", "bar");

        given(orgaos.orgao(new OrgaoXML().withId("orgao-1"))).willReturn(new ModelAndView("orgao", model));
        assertThat(miolo.getHtml(new OrgaoXML().withId("orgao-1")).getModel(), is(model));
    }
}