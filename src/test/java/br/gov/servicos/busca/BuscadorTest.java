package br.gov.servicos.busca;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;

import static br.gov.servicos.fixtures.TestData.CONTEUDO_DE_SERVICO;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class BuscadorTest {

    @Mock
    ServicoRepository servicos;

    Buscador buscador;

    @Before
    public void setUp() {
        doReturn(singletonList(SERVICO))
                .when(servicos)
                .search(any(QueryBuilder.class));

        doReturn(new FacetedPageImpl<>(singletonList(SERVICO)))
                .when(servicos)
                .search(any(QueryBuilder.class), any(Pageable.class));

        buscador = new Buscador(servicos);
    }

    @Test
    public void buscaPorParteDoServico() {
        assertThat(buscador.buscaServicosPor("areaDeInteresse", of("qualquer interesse")), hasItem(SERVICO));
    }

    @Test
    public void buscaPorSemelhanca() {
        assertThat(buscador.buscaSemelhante(of("qualquer interesse"), "campos.para", "buscar"), hasItem(CONTEUDO_DE_SERVICO));
    }

}
