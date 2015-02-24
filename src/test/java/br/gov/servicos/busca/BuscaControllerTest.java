package br.gov.servicos.busca;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.ModelAndViewAssert.*;

@RunWith(MockitoJUnitRunner.class)
public class BuscaControllerTest {

    @Mock
    private ServicoRepository servicos;
    
    @Mock
    private BuscaRepository buscas;

    private BuscaController controller;

    @Before
    public void setUp() throws Exception {
        doReturn(emptyList())
                .when(servicos)
                .search(any(QueryBuilder.class));

        controller = new BuscaController(servicos, buscas);
    }

    @Test
    public void buscaRedirecionaParaPaginaDeResultados() throws Exception {
        assertViewName(controller.busca(null), "resultados-busca");
    }

    @Test
    public void buscaRetornaTermoBuscado() throws Exception {
        assertModelAttributeValue(controller.busca("trabalho"), "termo", "trabalho");
    }

    @Test
    public void buscaRetornaResultadosNoModel() throws Exception {
        Servico servico = new Servico(
                "1",
                "Como adicionar um novo emprego à sua Carteira de Trabalho",
                "Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Interagi no mé, " +
                        "cursus quis, vehicula ac nisi. Aenean vel dui dui. Nullam leo erat, aliquet quis tempus a, " +
                        "posuere ut mi. Ut scelerisque neque et turpis posuere pulvinar pellentesque nibh ullamcorper. " +
                        "Pharetra in mattis molestie, volutpat elementum justo. Aenean ut ante turpis. Pellentesque " +
                        "laoreet mé vel lectus scelerisque interdum cursus velit auctor. Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Etiam ac mauris lectus, non scelerisque augue. Aenean massa.",
                null, null, null, null, null, null, null, 0L, 0L);


        doReturn(asList(servico))
                .when(servicos)
                .search(any(QueryBuilder.class));

        assertCompareListModelAttribute(controller.busca("emprego"), "resultados", asList(servico));
    }

    @Test
    public void aoBuscarUmServicoAOTermoBuscadoEIndexada() throws Exception {
        List<Servico> resultados = asList(new Servico(), new Servico());

        doReturn(resultados)
                .when(servicos)
                .search(any(QueryBuilder.class));

        doReturn(null)
                .when(buscas)
                .findOne("trabalho");

        assertModelAttributeValue(controller.busca("trabalho"), "termo", "trabalho");
        verify(buscas).save(new Busca("trabalho", 2L, 1L));
    }

    @Test
    public void aoBuscarUmServicoOTermoBuscadoEAtulalizado() throws Exception {
        List<Servico> resultados = asList(new Servico(), new Servico());

        doReturn(resultados)
                .when(servicos)
                .search(any(QueryBuilder.class));

        doReturn(new Busca("trabalho", 2L, 1L))
                .when(buscas)
                .findOne("trabalho");

        assertModelAttributeValue(controller.busca("trabalho"), "termo", "trabalho");
        verify(buscas).save(new Busca("trabalho", 2L, 2L));
    }

}