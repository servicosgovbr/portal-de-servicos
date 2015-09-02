package br.gov.servicos.orgao;

import br.gov.servicos.Main;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.v3.schema.Orgao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

import static br.gov.servicos.fixtures.TestData.CONTEUDO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class OrgaoRepositoryIntegrationTest {

    @Autowired
    ConteudoRepository conteudos;

    @Autowired
    OrgaoRepository orgaos;

    @Autowired
    PortalDeServicosIndex esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriar();
    }

    @Test
    public void listaOrgaosEmOrdemAlfabetica() throws Exception {
        conteudos.save(CONTEUDO
                .withId("orgao-1")
                .withNome("Órgão 1"));

        conteudos.save(CONTEUDO
                .withId("orgao-2")
                .withNome("Órgão 2"));

        conteudos.save(CONTEUDO
                .withId("orgao-3")
                .withNome("Órgão 3"));

        conteudos.save(CONTEUDO
                .withTipoConteudo("conteudo")
                .withId("conteudo-1")
                .withNome("Conteúdo 1"));

        List<Orgao> resultados = orgaos.findAll();

        assertThat(resultados, is(not(empty())));
        assertThat(resultados.size(), is(3));

        assertThat(resultados.get(0).getId(), is("orgao-1"));
        assertThat(resultados.get(1).getId(), is("orgao-2"));
        assertThat(resultados.get(2).getId(), is("orgao-3"));

        assertThat(resultados.get(0).getNome(), is("Órgão 1"));
        assertThat(resultados.get(1).getNome(), is("Órgão 2"));
        assertThat(resultados.get(2).getNome(), is("Órgão 3"));
    }
}
