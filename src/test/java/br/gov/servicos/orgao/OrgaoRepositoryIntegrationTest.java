package br.gov.servicos.orgao;

import br.gov.servicos.Main;
import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
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

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class OrgaoRepositoryIntegrationTest {

    @Autowired
    ServicoRepository servicos;

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
        servicos.save(SERVICO
                .withId("servico-1")
                .withOrgao(new Orgao().withId("orgao-1")));

        servicos.save(SERVICO
                .withId("servico-2")
                .withOrgao(new Orgao().withId("orgao-3")));


        servicos.save(SERVICO
                .withId("servico-3")
                .withOrgao(new Orgao().withId("orgao-2")));

        List<Orgao> resultados = orgaos.findAll();

        assertThat(resultados, is(not(empty())));
        assertThat(resultados.get(0).getId(), is("orgao-1"));
        assertThat(resultados.get(1).getId(), is("orgao-2"));
        assertThat(resultados.get(2).getId(), is("orgao-3"));
    }
}
