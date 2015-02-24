package br.gov.servicos.busca;

import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BuscadorTest {

    @Mock
    private ServicoRepository servicos;

    @Mock
    private BuscaRepository buscas;

    private Servico servico;
    private Buscador buscador;

    @Before
    public void setUp() {
        servico = new Servico("1", "Um Serviço", "Uma descrição", null, null, null, null, null, null, null, null, null);

        doReturn(asList(servico))
                .when(servicos)
                .search(any(QueryBuilder.class));
        
        buscador = new Buscador(servicos, buscas);
    }

    @Test
    public void buscaPorServico() {
        assertThat(buscador.busca(of("um serviço")), hasItem(servico));
    }

    @Test
    public void buscaPorParteDoServico() {
        assertThat(buscador.buscaPor("areaDeInteresse", of("qualquer interesse")), hasItem(servico));
    }

    @Test
    public void buscaPorSemelhanca() {
        assertThat(buscador.buscaSemelhante(of("qualquer interesse"), "campos.para", "buscar"), hasItem(servico));
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
}