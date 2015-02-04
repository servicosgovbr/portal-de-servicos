package br.gov.servicos.frontend;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.MockitoAnnotations.initMocks;

@SuppressWarnings("unchecked")
public class BuscaControllerTest {

    @Mock
    ServicoRepository sr;

    BuscaController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new BuscaController(sr);
    }

    @Test
    public void buscaRetornaView() throws Exception {
        assertThat(controller.busca(null).getViewName(), is("resultados-busca"));
    }

    @Test
    public void buscaRetornaTermoNoModel() throws Exception {
        assertThat(controller.busca("trabalho").getModel().get("termo"), is("trabalho"));
    }
    
    @Test
    public void buscaRetornaResultadosNoModel() throws Exception {
        Servico s1 = new Servico("Como adicionar um novo emprego à sua Carteira de Trabalho",
                "Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Interagi no mé, " +
                        "cursus quis, vehicula ac nisi. Aenean vel dui dui. Nullam leo erat, aliquet quis tempus a, " +
                        "posuere ut mi. Ut scelerisque neque et turpis posuere pulvinar pellentesque nibh ullamcorper. " +
                        "Pharetra in mattis molestie, volutpat elementum justo. Aenean ut ante turpis. Pellentesque " +
                        "laoreet mé vel lectus scelerisque interdum cursus velit auctor. Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Etiam ac mauris lectus, non scelerisque augue. Aenean massa.");

        given(sr.search((QueryBuilder) anyObject())).willReturn(Arrays.asList(s1));

        List<Servico> actual = (List<Servico>) controller.busca("emprego").getModel().get("resultados");
        assertThat(actual, hasItem(s1));
    }
}