package br.gov.servicos.frontend;

import br.gov.servicos.dominio.Orgao;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServicoControllerTest {

    static final Servico SERVICO = new Servico(
            "1",
            "Título",
            "Descrição",
            "url://site",
            "Gratuita",
            new Orgao("Nome", "123"),
            new Orgao("Nome", null),
            0L, 0L
    );

    @Mock
    ServicoRepository sr;

    ServicoController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new ServicoController(sr);
    }

    @Test
    public void buscaRetornaView() throws Exception {
        given(sr.findOne("1")).willReturn(new Servico("1", null, null, null, null, null, null, 0L, 0L));
        assertThat(controller.get("1").getViewName(), is("servico"));
    }

    @Test
    public void buscaRetornaServicoNoModel() throws Exception {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        given(sr.findOne("1")).willReturn(SERVICO);
        given(sr.save(captor.capture())).willReturn(SERVICO);

        Servico actual = (Servico) controller.get("1").getModel().get("servico");
        assertThat(actual, is(SERVICO));
        assertThat(captor.getValue().getAcessos(), is(1L));
    }

    @Test
    public void redirecionaUsuarioParaLinkDoServico() throws Exception {
        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        given(sr.findOne("1")).willReturn(SERVICO);
        given(sr.save(captor.capture())).willReturn(SERVICO);

        String actual = controller.navegar("1").getUrl();
        assertThat(actual, is(SERVICO.getUrl()));
        assertThat(captor.getValue().getAtivacoes(), is(1L));
    }

}