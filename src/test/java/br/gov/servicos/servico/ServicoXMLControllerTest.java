package br.gov.servicos.servico;

import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static br.gov.servicos.cms.PaginaEstatica.fromServico;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ServicoXMLControllerTest {

    @Mock
    ServicoRepository servicos;

    ServicoController controller;

    @Before
    public void setUp() {
        given(servicos.findOne("1")).willReturn(SERVICO);
        given(servicos.findAll(any(PageRequest.class))).willReturn(new PageImpl<>(emptyList()));
        given(servicos.save(any(ServicoXML.class))).will(returnsFirstArg());

        controller = new ServicoController(servicos);
    }

    @Test
    public void deveRedirecionarParaListaDeServicos() {
        assertViewName(controller.todos(null), "servicos");
    }

    @Test
    public void deveRedirecionarLinksLegadosParaServico() {
        assertThat(controller.getLegado("abc").getUrl(), is("/servico/abc"));
    }

    @Test
    public void deveRetornarTodosOsServicosEmOrdemAlfabetica() {
        ServicoXML servicoA = new ServicoXML().withNome("A");
        ServicoXML servicoB = new ServicoXML().withNome("B");

        given(servicos.findAll(any(PageRequest.class))).willReturn(new PageImpl<>(asList(servicoA, servicoB)));

        assertModelAttributeValue(controller.todos(null), "servicos", asList(fromServico(servicoA), fromServico(servicoB)));
        assertModelAttributeValue(controller.todos('B'), "servicos", singletonList(fromServico(servicoB)));
    }

    @Test
    public void deveRetornarLetrasDisponiveisParaFiltro() {
        given(servicos.findAll(any(PageRequest.class))).willReturn(new PageImpl<>(asList(
                new ServicoXML().withNome("x"),
                new ServicoXML().withNome("B2"),
                new ServicoXML().withNome("B1"))));

        assertModelAttributeValue(controller.todos(null), "letras", asList('B', 'X'));
    }

    @Test
    public void deveRetornarQualEALetraFiltrada() {
        assertModelAttributeValue(controller.todos('B'), "letraAtiva", 'B');
    }

    @Test
    public void redirecionaParaAPaginaDeServicos() {
        assertViewName(controller.get(new ServicoXML().withId("1")), "servico");
    }

    @Test
    public void retornaOServicoBuscado() {
        assertModelAttributeValue(controller.get(SERVICO), "servico", SERVICO);
    }

}
