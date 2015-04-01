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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class BuscadorTest {

    @Mock
    ServicoRepository servicos;

    Buscador buscador;

    @Before
    public void setUp() {
        doReturn(asList(SERVICO))
                .when(servicos)
                .search(any(QueryBuilder.class));

        doReturn(new FacetedPageImpl<>(asList(SERVICO)))
                .when(servicos)
                .search(any(QueryBuilder.class), any(Pageable.class));

        buscador = new Buscador(servicos);
    }

    @Test
    public void buscaPorServico() {
        assertThat(buscador.busca(of("um serviço"), 0), hasItem(SERVICO));
    }

    @Test
    public void deveEfetuarBuscaPaginada() {
        buscador.busca(of("um serviço"), 10);
        verify(servicos).search(any(QueryBuilder.class), eq(new PageRequest(10, 20)));
    }

    @Test
    public void buscaPorParteDoServico() {
        assertThat(buscador.buscaPor("areaDeInteresse", of("qualquer interesse")), hasItem(SERVICO));
    }

    @Test
    public void buscaPorSemelhanca() {
        assertThat(buscador.buscaSemelhante(of("qualquer interesse"), "campos.para", "buscar"), hasItem(SERVICO));
    }

    @Test
    public void retornaUmaListaVaziaQuandoNaoHouverTermoDeBusca() throws Exception {
        Page<Servico> resultados = buscador.busca(empty(), 0);
        assertThat(resultados.getContent(), is(emptyList()));
        verifyZeroInteractions(servicos);
    }

    @Test
    public void deveConsiderarTermoDeBuscaVazioComoEmpty() throws Exception {
        Page<Servico> resultados = buscador.busca(of(""), 0);
        assertThat(resultados.getContent(), is(emptyList()));
        verifyZeroInteractions(servicos);
    }
}
