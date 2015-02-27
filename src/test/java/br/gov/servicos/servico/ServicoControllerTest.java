package br.gov.servicos.servico;

import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
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
        doReturn(TestData.SERVICO).when(servicos).findOne("1");
        
        doAnswer(returnsFirstArg())
                .when(servicos)
                .save(any(Servico.class));

        controller = new ServicoController(servicos);
    }

    @Test
    public void redirecionaParaAPaginaDeServicos() {
        assertViewName(controller.get("1"), "servico");
    }

    @Test
    public void retornaOServicoBuscado() {
        assertModelAttributeValue(controller.get("1"), "servico", TestData.SERVICO.withNovoAcesso());
    }

    @Test
    public void incrementaONumeroDeAcessosAoServico() {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        doReturn(TestData.SERVICO)
                .when(servicos)
                .save(captor.capture());

        controller.get("1");
        assertThat(captor.getValue().getAcessos(), is(1L));
    }

    @Test
    public void redirecionaUsuarioParaLinkDoServico() {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        doReturn(TestData.SERVICO)
                .when(servicos)
                .save(captor.capture());

        String actual = controller.navegar("1").getUrl();

        assertThat(actual, is(TestData.SERVICO.getUrl()));
        assertThat(captor.getValue().getAtivacoes(), is(1L));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void retorna404QuandoServicoNaoExiste() {
        controller.get("servico-nao-existente");
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void retorna404QuandoRedirecionaParaServicoNaoExistente() {
        controller.navegar("servico-nao-existente");
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void retorna404QuandoRedirecionaParaServicoSemURL() {
        doReturn(new Servico())
                .when(servicos)
                .findOne("servico-sem-url");
        
        controller.navegar("servico-sem-url");
    }
}