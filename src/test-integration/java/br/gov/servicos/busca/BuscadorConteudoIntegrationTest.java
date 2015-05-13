package br.gov.servicos.busca;

import br.gov.servicos.Main;
import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.CONTEUDO;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Optional.of;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class BuscadorConteudoIntegrationTest {

    @Autowired
    GuiaDeServicosIndex guiaDeServicosIndex;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    ConteudoRepository conteudoRepository;

    @Autowired
    BuscadorConteudo   buscadorConteudo;

    @Before
    public void setUp() throws Exception {
        guiaDeServicosIndex.recriar();
    }

    @Test
    public void deveInserirUmServicoEUmConteudoERetornarDoisConteudos() throws Exception {
        servicoRepository.save(SERVICO);
        conteudoRepository.save(CONTEUDO);

        List<Conteudo> conteudos = ((FacetedPageImpl) buscadorConteudo.busca(of("Descrição"), 0)).getContent();

        assertThat(conteudos, hasSize(2));
        assertThat(conteudos.get(0).getConteudo(), is("Descrição conteúdo"));
        assertThat(conteudos.get(1).getConteudo(), is("Descrição serviço"));
    }

    @Test
    public void deveRetornarApenasConteudosQueTenhamAPalavraDescricao() throws Exception {
        servicoRepository.save(SERVICO);
        servicoRepository.save(new Servico().withTitulo("Um titulo").withDescricao("Texto"));
        conteudoRepository.save(new Conteudo().withTitulo("Titulo de conteudo").withConteudo("Conteudo"));

        List<Conteudo> conteudos = ((FacetedPageImpl) buscadorConteudo.busca(of("Descrição"), 0)).getContent();

        assertThat(conteudos, hasSize(1));
        assertThat(conteudos.get(0).getConteudo(), is("Descrição serviço"));
    }

    @Test
    public void deveAcharSemAcentuacao() throws Exception {
        servicoRepository.save(new Servico().withTitulo("Um titulo").withDescricao("Descricao"));

        List<Conteudo> conteudos = ((FacetedPageImpl) buscadorConteudo.busca(of("Descrição"), 0)).getContent();
        assertThat(conteudos, hasSize(1));
        assertThat(conteudos.get(0).getConteudo(), is("Descricao"));
    }

    @Test
    public void buscaSemelhante() throws Exception {
        servicoRepository.save(SERVICO);
        servicoRepository.save(new Servico().withTitulo("Titulo de servico").withDescricao("Texto"));

        List<Conteudo> conteudos = buscadorConteudo.buscaSemelhante(of("ervic"));

        assertThat(conteudos, hasSize(2));
        assertThat(conteudos, hasItem(new Conteudo().withId("titulo").withTitulo(SERVICO.getTitulo()).withConteudo(SERVICO.getDescricao())));
        assertThat(conteudos, hasItem(new Conteudo().withId("titulo-de-servico").withTitulo("Titulo de servico").withConteudo("Texto")));
    }

}