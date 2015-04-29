package br.gov.servicos.orgao;

import br.gov.servicos.ElasticSearchTest;
import br.gov.servicos.Main;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class OrgaoRepositoryTest extends ElasticSearchTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    OrgaoRepository orgaos;

    @Test
    public void listaOrgaosEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1")
                .withPrestador(new Orgao().withId("orgao-1").withNome("Ministério da Educação"))
                .withResponsavel(new Orgao().withId("orgao-2").withNome("Instituto Nacional de Estudos e Pesquisas")));

        servicos.save(SERVICO
                .withId("servico-2")
                .withPrestador(new Orgao().withId("orgao-3").withNome("Ministério dos Transportes"))
                .withResponsavel(new Orgao().withId("orgao-3").withNome("Ministério dos Transportes")));

        List<Orgao> resultados = orgaos.findAll();

        assertThat(resultados, is(not(empty())));
        assertThat(resultados.get(0).getId(), is("orgao-1"));
        assertThat(resultados.get(1).getId(), is("orgao-2"));
        assertThat(resultados.get(2).getId(), is("orgao-3"));
    }
}
