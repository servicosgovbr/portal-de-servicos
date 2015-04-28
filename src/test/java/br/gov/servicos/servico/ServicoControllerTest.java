package br.gov.servicos.servico;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ServicoControllerTest {

    @Mock
    ServicoRepository servicos;

    ServicoController controller;

    @Before
    public void setUp() {
        doReturn(SERVICO).when(servicos).findOne("1");

        doReturn(EMPTY_LIST)
                .when(servicos)
                .findAll(any(Sort.class));

        doAnswer(returnsFirstArg())
                .when(servicos)
                .save(any(Servico.class));

        controller = new ServicoController(servicos);
    }

    @Test
    public void deveRedirecionarParaListaDeServicos() {
        assertViewName(controller.todos(null), "servicos");
    }

    @Test
    public void deveRetornarTodosOsServicosEmOrdemAlfabetica() {
        Servico servicoA = new Servico().withTitulo("A");
        Servico servicoB = new Servico().withTitulo("B");

        doReturn(asList(servicoA, servicoB))
                .when(servicos)
                .findAll(new Sort(ASC, "titulo"));

        assertModelAttributeValue(controller.todos(null), "servicos", singletonList(servicoA));
        assertModelAttributeValue(controller.todos('B'), "servicos", singletonList(servicoB));
    }

    @Test
    public void deveRetornarLetrasDisponiveisParaFiltro() {
        List<Servico> servicosNaoOrdenados = asList(
                new Servico().withTitulo("x"),
                new Servico().withTitulo("B2"),
                new Servico().withTitulo("B1"));

        doReturn(servicosNaoOrdenados)
                .when(servicos)
                .findAll(any(Sort.class));

        assertModelAttributeValue(controller.todos(null), "letras", asList('B', 'X'));
    }

    @Test
    public void deveRetornarQualEALetraFiltrada() {
        assertModelAttributeValue(controller.todos(null), "letraAtiva", 'A');
        assertModelAttributeValue(controller.todos('B'), "letraAtiva", 'B');
    }

    @Test
    public void redirecionaParaAPaginaDeServicos() {
        assertViewName(controller.get("1"), "servico");
    }

    @Test
    public void retornaOServicoBuscado() {
        assertModelAttributeValue(controller.get("1"), "servico", SERVICO);
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void retorna404QuandoServicoNaoExiste() {
        controller.get("servico-nao-existente");
    }

}
