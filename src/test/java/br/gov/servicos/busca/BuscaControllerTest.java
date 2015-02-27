package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.*;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class BuscaControllerTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    BuscaRepository buscas;

    @Mock
    Buscador buscador;

    List<Servico> umServico = asList(SERVICO);
    BuscaController controller;

    @Before
    public void setUp() {
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

}