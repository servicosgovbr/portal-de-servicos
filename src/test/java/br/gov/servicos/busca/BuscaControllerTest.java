package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.*;

@RunWith(MockitoJUnitRunner.class)
public class BuscaControllerTest {

    @Mock
    private ServicoRepository servicos;

    @Mock
    private BuscaRepository buscas;

    @Mock
    private Buscador buscador;

    private List<Servico> umServico;
    private BuscaController controller;

    @Before
    public void setUp() {
        Servico servico = new Servico("1", "Como adicionar um novo emprego à sua Carteira de Trabalho",
                "Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis.",
                null, null, null, null, null, null, null, 0L, 0L);

        umServico = asList(servico);

        doReturn(emptyList())
                .when(servicos)
                .search(any(QueryBuilder.class));

        controller = new BuscaController(buscador);
    }

    @Test
    public void buscaRedirecionaParaPaginaDeResultados() {
        assertViewName(controller.busca(null), "resultados-busca");
    }

    @Test
    public void buscaRetornaTermoBuscado() {
        assertModelAttributeValue(controller.busca("trabalho"), "termo", "trabalho");
    }

    @Test
    public void buscaRetornaResultadosParaAPagina() {
        doReturn(umServico)
                .when(buscador)
                .busca(of("emprego"));

        assertCompareListModelAttribute(controller.busca("emprego"), "resultados", umServico);
    }

    @Test
    public void buscaPorLinhasDaVida() {
        doReturn(umServico)
                .when(buscador)
                .buscaPor("linhasDaVida", of("Aposentar-se"));

        assertCompareListModelAttribute(controller.linhaDaVida("Aposentar-se"), "resultados", umServico);
    }

    @Test
    public void buscaPorOrgao() {
        doReturn(umServico)
                .when(buscador)
                .buscaSemelhante(of("Planejamento"), "prestador.nome", "responsavel.nome");

        assertCompareListModelAttribute(controller.orgao("Planejamento"), "resultados", umServico);
    }

}