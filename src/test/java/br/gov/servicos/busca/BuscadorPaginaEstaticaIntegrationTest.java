package br.gov.servicos.busca;

import br.gov.servicos.Main;
import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.cms.PaginaEstaticaRepository;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.setup.SetupTestesIntegracao;
import br.gov.servicos.v3.schema.ServicoXML;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.*;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class BuscadorPaginaEstaticaIntegrationTest {

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    @Autowired
    ServicoRepository servicos;

    @Autowired
    PaginaEstaticaRepository paginas;

    @Autowired
    BuscadorConteudo buscador;

    @Before
    public void setUp() throws Exception {
        setupTestesIntegracao.setupBaseLimpa();
    }

    @Test
    public void deveInserirUmServicoEUmConteudoERetornarDoisConteudos() throws Exception {
        servicos.save(SERVICO);
        paginas.save(PAGINA_ESTATICA);

        List<PaginaEstatica> paginaEstaticas = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(paginaEstaticas, hasSize(2));
        assertThat(paginaEstaticas, hasItem(PAGINA_ESTATICA));
        assertThat(paginaEstaticas, hasItem(PAGINA_ESTATICA_DE_SERVICO));
    }

    @Test
    public void deveRetornarApenasConteudosQueTenhamAPalavraDescricao() throws Exception {
        servicos.save(SERVICO);
        servicos.save(new ServicoXML().withNome("Um titulo").withDescricao("Texto"));
        paginas.save(new PaginaEstatica().withNome("Titulo de conteudo").withConteudo("Conteudo"));

        List<PaginaEstatica> paginaEstaticas = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(paginaEstaticas, hasSize(1));
        assertThat(paginaEstaticas.get(0).getConteudo(), is("Descrição serviço"));
    }

    @Test
    public void deveAcharSemAcentuacao() throws Exception {
        servicos.save(new ServicoXML().withNome("Um titulo").withDescricao("Descricao"));

        List<PaginaEstatica> paginaEstaticas = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();
        assertThat(paginaEstaticas, hasSize(1));
        assertThat(paginaEstaticas.get(0).getConteudo(), is("Descricao"));
    }

    @Test
    public void buscaSemelhante() throws Exception {
        servicos.save(SERVICO);
        servicos.save(new ServicoXML().withNome("Titulo de servico").withDescricao("Texto"));

        List<PaginaEstatica> paginaEstaticas = buscador.buscaSemelhante(of("ervic"));

        assertThat(paginaEstaticas, hasSize(2));
        assertThat(paginaEstaticas, hasItem(PAGINA_ESTATICA_DE_SERVICO));
        assertThat(paginaEstaticas, hasItem(new PaginaEstatica()
                .withId("titulo-de-servico")
                .withNome("Titulo de servico")
                .withTipoConteudo("servico")
                .withConteudo("Texto")));
    }


    @Test
    public void deveConsiderarTermoDeBuscaVazioComoEmpty() throws Exception {
        Page<PaginaEstatica> resultados = buscador.busca(of(""), 0);
        assertThat(resultados.getContent(), is(emptyList()));
    }

    @Test
    public void retornaUmaListaVaziaQuandoNaoHouverTermoDeBusca() throws Exception {
        Page<PaginaEstatica> resultados = buscador.busca(empty(), 0);
        assertThat(resultados.getContent(), is(emptyList()));
    }

    @Test
    public void deveConterTipoConteudo() throws Exception {
        servicos.save(SERVICO);
        paginas.save(PAGINA_ESTATICA);

        List<PaginaEstatica> paginaEstaticas = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(paginaEstaticas, hasSize(2));
        assertThat(paginaEstaticas, hasItem(PAGINA_ESTATICA_DE_SERVICO));
        assertThat(paginaEstaticas, hasItem(PAGINA_ESTATICA));
    }

    @Test
    public void buscaPorTermosComErrosDeDigitacao() {
        servicos.save(SERVICO
                .withNome("Passaporte")
                .withDescricao("Emissão de passaportes"));

        Iterable<PaginaEstatica> busca = buscador.busca(of("passaprote"), 0);
        PaginaEstatica resultado = busca.iterator().next();

        assertThat(resultado.getId(), is("passaporte"));
    }
}