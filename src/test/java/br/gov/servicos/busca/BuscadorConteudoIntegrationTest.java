package br.gov.servicos.busca;

import br.gov.servicos.Main;
import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.setup.SetupTestesIntegracao;
import br.gov.servicos.v3.schema.Servico;
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
public class BuscadorConteudoIntegrationTest {

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    @Autowired
    ServicoRepository servicos;

    @Autowired
    ConteudoRepository conteudos;

    @Autowired
    BuscadorConteudo buscador;

    @Before
    public void setUp() throws Exception {
        setupTestesIntegracao.setupBaseLimpa();
    }

    @Test
    public void deveInserirUmServicoEUmConteudoERetornarDoisConteudos() throws Exception {
        servicos.save(SERVICO);
        conteudos.save(CONTEUDO);

        List<Conteudo> conteudos = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(conteudos, hasSize(2));
        assertThat(conteudos, hasItem(CONTEUDO));
        assertThat(conteudos, hasItem(CONTEUDO_DE_SERVICO));
    }

    @Test
    public void deveRetornarApenasConteudosQueTenhamAPalavraDescricao() throws Exception {
        servicos.save(SERVICO);
        servicos.save(new Servico().withNome("Um titulo").withDescricao("Texto"));
        conteudos.save(new Conteudo().withNome("Titulo de conteudo").withConteudo("Conteudo"));

        List<Conteudo> conteudos = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(conteudos, hasSize(1));
        assertThat(conteudos.get(0).getConteudo(), is("Descrição serviço"));
    }

    @Test
    public void deveAcharSemAcentuacao() throws Exception {
        servicos.save(new Servico().withNome("Um titulo").withDescricao("Descricao"));

        List<Conteudo> conteudos = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();
        assertThat(conteudos, hasSize(1));
        assertThat(conteudos.get(0).getConteudo(), is("Descricao"));
    }

    @Test
    public void buscaSemelhante() throws Exception {
        servicos.save(SERVICO);
        servicos.save(new Servico().withNome("Titulo de servico").withDescricao("Texto"));

        List<Conteudo> conteudos = buscador.buscaSemelhante(of("ervic"));

        assertThat(conteudos, hasSize(2));
        assertThat(conteudos, hasItem(CONTEUDO_DE_SERVICO));
        assertThat(conteudos, hasItem(new Conteudo()
                .withId("titulo-de-servico")
                .withNome("Titulo de servico")
                .withTipoConteudo("servico")
                .withConteudo("Texto")));
    }


    @Test
    public void deveConsiderarTermoDeBuscaVazioComoEmpty() throws Exception {
        Page<Conteudo> resultados = buscador.busca(of(""), 0);
        assertThat(resultados.getContent(), is(emptyList()));
    }

    @Test
    public void retornaUmaListaVaziaQuandoNaoHouverTermoDeBusca() throws Exception {
        Page<Conteudo> resultados = buscador.busca(empty(), 0);
        assertThat(resultados.getContent(), is(emptyList()));
    }

    @Test
    public void deveConterTipoConteudo() throws Exception {
        servicos.save(SERVICO);
        conteudos.save(CONTEUDO);

        List<Conteudo> conteudos = ((FacetedPageImpl) buscador.busca(of("Descrição"), 0)).getContent();

        assertThat(conteudos, hasSize(2));
        assertThat(conteudos, hasItem(CONTEUDO_DE_SERVICO));
        assertThat(conteudos, hasItem(CONTEUDO));
    }

    @Test
    public void buscaPorTermosComErrosDeDigitacao() {
        servicos.save(SERVICO
                .withNome("Passaporte")
                .withDescricao("Emissão de passaportes"));

        Iterable<Conteudo> busca = buscador.busca(of("passapote"), 0);
        Conteudo resultado = busca.iterator().next();
        assertThat(resultado.getId(), is("passaporte"));
    }
}