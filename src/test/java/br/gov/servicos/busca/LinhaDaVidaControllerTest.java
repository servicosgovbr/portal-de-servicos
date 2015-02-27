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
import static org.springframework.test.web.ModelAndViewAssert.assertCompareListModelAttribute;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class LinhaDaVidaControllerTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    BuscaRepository buscas;

    @Mock
    Buscador buscador;

    List<Servico> umServico;
    LinhaDaVidaController controller;

    @Before
    public void setUp() {
        umServico = asList(SERVICO);

        doReturn(emptyList())
                .when(servicos)
                .search(any(QueryBuilder.class));

        controller = new LinhaDaVidaController(buscador);
    }

    @Test
    public void buscaPorLinhasDaVidaRetornaServicos() {
        doReturn(umServico)
                .when(buscador)
                .buscaPor("linhasDaVida.id", of("Aposentar-se"));

        assertCompareListModelAttribute(controller.linhaDaVida("Aposentar-se"), "resultados", umServico);
    }


}