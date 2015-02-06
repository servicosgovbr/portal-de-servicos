package br.gov.servicos.frontend;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServicoControllerTest {

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
        given(sr.findOne("1")).willReturn(new Servico("1", null, null, null, null, null, 0L, 0L));
        assertThat(controller.get("1").getViewName(), is("servico"));
    }

    @Test
    public void buscaRetornaServicoNoModel() throws Exception {
        Servico s1 = new Servico(
                "1",
                "Como adicionar um novo emprego à sua Carteira de Trabalho", "Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Interagi no mé, " +
                "cursus quis, vehicula ac nisi. Aenean vel dui dui. Nullam leo erat, aliquet quis tempus a, " +
                "posuere ut mi. Ut scelerisque neque et turpis posuere pulvinar pellentesque nibh ullamcorper. " +
                "Pharetra in mattis molestie, volutpat elementum justo. Aenean ut ante turpis. Pellentesque " +
                "laoreet mé vel lectus scelerisque interdum cursus velit auctor. Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Etiam ac mauris lectus, non scelerisque augue. Aenean massa.",
                null,
                null,
                null,
                0L,
                0L);

        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        given(sr.findOne("1")).willReturn(s1);
        given(sr.save(captor.capture())).willReturn(s1);

        Servico actual = (Servico) controller.get("1").getModel().get("servico");
        assertThat(actual, is(s1));
        assertThat(captor.getValue().getAcessos(), is(1L));
    }

    @Test
    public void redirecionaUsuarioParaLinkDoServico() throws Exception {
        Servico s1 = new Servico(
                "1",
                "Como adicionar um novo emprego à sua Carteira de Trabalho", "desc",
                "URL://blah",
                null,
                null,
                0L,
                0L
        );

        ArgumentCaptor<Servico> captor = ArgumentCaptor.forClass(Servico.class);

        given(sr.findOne("1")).willReturn(s1);
        given(sr.save(captor.capture())).willReturn(s1);

        String actual = controller.navegar("1").getUrl();
        assertThat(actual, is("URL://blah"));
        assertThat(captor.getValue().getAtivacoes(), is(1L));
    }

}