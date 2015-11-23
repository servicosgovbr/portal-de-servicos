package br.gov.servicos.orgao;

import br.gov.servicos.Main;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.setup.SetupTestesIntegracao;
import br.gov.servicos.v3.schema.OrgaoXML;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Iterator;
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
    OrgaoRepositoryUtil orgaosRepositoryUtil;

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    @Before
    public void setup() throws IOException {
        setupTestesIntegracao.setupBaseLimpa();
    }

    @Test
    public void listaOrgaosEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1")
                .withOrgao(orgaos.save(new OrgaoXML()
                        .withId("orgao-1")
                        .withUrl("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/1")
                        .withNome("Órgão 1"))));

        servicos.save(SERVICO
                .withId("servico-3")
                .withOrgao(orgaos.save(new OrgaoXML()
                        .withId("orgao-3")
                        .withUrl("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/3")
                        .withNome("Órgão 3"))));

        servicos.save(SERVICO
                .withId("servico-2")
                .withOrgao(orgaos.save(new OrgaoXML()
                        .withId("orgao-2")
                        .withUrl("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/2")
                        .withNome("Órgão 2"))));

        List<OrgaoXML> resultados = Lists.newArrayList(orgaosRepositoryUtil.findAll());

        assertThat(resultados, is(not(empty())));
        assertThat(resultados.size(), is(3));

        Iterator<OrgaoXML> it = resultados.iterator();
        assertThat(it.next().getId(), is("orgao-1"));
        assertThat(it.next().getId(), is("orgao-2"));
        assertThat(it.next().getId(), is("orgao-3"));

        it = resultados.iterator();
        assertThat(it.next().getUrl(), is("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/1"));
        assertThat(it.next().getUrl(), is("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/2"));
        assertThat(it.next().getUrl(), is("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/3"));

        it = resultados.iterator();
        assertThat(it.next().getNome(), is("Órgão 1"));
        assertThat(it.next().getNome(), is("Órgão 2"));
        assertThat(it.next().getNome(), is("Órgão 3"));
    }
}
