package br.gov.servicos.busca;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class BuscadorTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    BuscaRepository buscas;

    Buscador buscador;

    @Before
    public void setUp() {
        doReturn(asList(SERVICO))
                .when(servicos)
                .search(any(QueryBuilder.class));
        
        buscador = new Buscador(servicos, buscas);
    }

    @Test
    public void buscaPorServico() {
        assertThat(buscador.busca(of("um serviço")), hasItem(SERVICO));
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
    public void quandoUmServicoEBuscadoOTermoUtilizadoESalvo() {
        doReturn(null)
                .when(buscas)
                .findOne("um serviço");

        buscador.busca(of("um serviço"));

        verify(buscas).save(new Busca("um serviço", 1, 1));
    }

    @Test
    public void quandoUmServicoEBuscadoOTermoUtilizadoEAtualizado() {
        doReturn(new Busca("um serviço", 1, 1))
                .when(buscas)
                .findOne("um serviço");

        buscador.busca(of("um serviço"));

        verify(buscas).save(new Busca("um serviço", 1, 2));
    }

    @Test
    public void deveRegistrarBuscasSemResultado() throws Exception {
        doReturn(emptyList())
                .when(servicos)
                .search(any(QueryBuilder.class));
        
        buscador.busca(of("serviço não existente"));
        verify(buscas).save(new Busca("serviço não existente", 0, 1));
    }

    @Test
    public void retornaUmaListaVaziaQuandoNaoHouverTermoDeBusca() throws Exception {
        assertThat(buscador.busca(empty()), is(emptyList()));
        
        verifyZeroInteractions(servicos);
        verify(buscas).save(new Busca("[BUSCA VAZIA]", 0, 1));
    }

    @Test
    public void deveConsiderarTermoDeBuscaVazioComoEmpty() throws Exception {
        assertThat(buscador.busca(of("")), is(emptyList()));
        
        verifyZeroInteractions(servicos);
        verify(buscas).save(new Busca("[BUSCA VAZIA]", 0, 1));
    }
}