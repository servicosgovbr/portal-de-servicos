package br.gov.servicos.frontend;

import br.gov.servicos.dominio.Orgao;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
public class ServicoControllerTest {

    private static final Servico SERVICO = new Servico(
            "1",
            "Título",
            "Descrição",
            "url://site",
            "Gratuita",
            new Orgao("Nome", "123"),
            new Orgao("Nome", null),
            asList("Área de Interesse"),
            asList("Linhas da Vida"),
            asList("Eventos das Linhas da Vida"),
            0L, 0L
    );

    @Mock
    private ServicoRepository servicos;
    private ServicoController controller;

    @Before
    public void setUp() {
        doReturn(SERVICO).when(servicos).findOne("1");
        
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
        assertModelAttributeValue(controller.get("1"), "servico", SERVICO.withNovoAcesso());
    }

    @Test
    public void incrementaONumeroDeAcessosAoServico() {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        doReturn(SERVICO)
                .when(servicos)
                .save(captor.capture());

        controller.get("1");
        assertThat(captor.getValue().getAcessos(), is(1L));
    }

    @Test
    public void redirecionaUsuarioParaLinkDoServico() {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        doReturn(SERVICO)
                .when(servicos)
                .save(captor.capture());

        String actual = controller.navegar("1").getUrl();

        assertThat(actual, is(SERVICO.getUrl()));
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