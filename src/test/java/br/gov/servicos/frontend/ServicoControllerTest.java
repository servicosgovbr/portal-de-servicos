package br.gov.servicos.frontend;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
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
        assertThat(controller.get(null).getViewName(), is("servico"));
    }

    @Test
    public void buscaRetornaServicoNoModel() throws Exception {
        Servico s1 = new Servico("1", "Como adicionar um novo emprego à sua Carteira de Trabalho",
                "Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Interagi no mé, " +
                        "cursus quis, vehicula ac nisi. Aenean vel dui dui. Nullam leo erat, aliquet quis tempus a, " +
                        "posuere ut mi. Ut scelerisque neque et turpis posuere pulvinar pellentesque nibh ullamcorper. " +
                        "Pharetra in mattis molestie, volutpat elementum justo. Aenean ut ante turpis. Pellentesque " +
                        "laoreet mé vel lectus scelerisque interdum cursus velit auctor. Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Etiam ac mauris lectus, non scelerisque augue. Aenean massa.",
                null);

        given(sr.findOne("1")).willReturn(s1);

        Servico actual = (Servico) controller.get("1").getModel().get("servico");
        assertThat(actual, is(s1));
    }

}