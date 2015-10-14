package br.gov.servicos.servico;

import br.gov.servicos.Main;
import br.gov.servicos.setup.SetupTestesIntegracao;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
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
import static br.gov.servicos.v3.schema.AreaDeInteresse.*;
import static br.gov.servicos.v3.schema.SegmentoDaSociedade.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class ServicoRepositoryIntegrationTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    @Before
    public void setup() throws IOException {
        setupTestesIntegracao.setupBaseLimpa();
    }

    @Test
    public void listaServicosPorOrgao() throws Exception {
        Orgao orgao = new Orgao()
                .withId("orgao-1")
                .withUrl("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/1")
                .withNome("Órgão 1");

        servicos.save(SERVICO.withId("servico-1").withOrgao(orgao));
        servicos.save(SERVICO.withId("servico-2").withOrgao(new Orgao().withId("orgao-2")));
        servicos.save(SERVICO.withId("servico-3").withOrgao(orgao));

        List<Servico> resultados = servicos.findByOrgao(orgao);

        assertThat(resultados, is(not(empty())));
        assertThat(resultados.size(), is(2));
        assertThat(resultados, everyItem(hasProperty("orgao", is(orgao))));
    }

    @Test
    public void listaServicosPorAreaDeInteresse() throws Exception {
        servicos.save(SERVICO.withId("servico-1").withNome("Serviço 1").withAreasDeInteresse(asList(VCGE2_TURISMO, VCGE2_COMUNICACOES)));
        servicos.save(SERVICO.withId("servico-2").withNome("Serviço 2").withAreasDeInteresse(asList(VCGE2_TURISMO, VCGE2_BIODIVERSIDADE)));
        servicos.save(SERVICO.withId("servico-3").withNome("Serviço 3").withAreasDeInteresse(asList(VCGE2_TURISMO, VCGE2_TRANSPORTES)));
        servicos.save(SERVICO.withId("servico-4").withNome("Serviço 4").withAreasDeInteresse(singletonList(VCGE2_VIGILANCIA_SANITARIA)));

        assertThat(servicos.findByAreaDeInteresse(VCGE2_TURISMO), contains(
                hasProperty("id", is("servico-1")),
                hasProperty("id", is("servico-2")),
                hasProperty("id", is("servico-3"))
        ));

        assertThat(servicos.findByAreaDeInteresse(VCGE2_BIODIVERSIDADE), contains(
                hasProperty("id", is("servico-2"))
        ));

        assertThat(servicos.findByAreaDeInteresse(VCGE2_TRANSPORTES), contains(
                hasProperty("id", is("servico-3"))
        ));

        assertThat(servicos.findByAreaDeInteresse(VCGE2_CULTURA), is(empty()));
    }

    @Test
    public void listaServicosPorSegmentoDaSociedade() throws Exception {
        servicos.save(SERVICO.withId("servico-1").withNome("Serviço 1").withSegmentosDaSociedade(asList(CIDADAOS, EMPRESAS)));
        servicos.save(SERVICO.withId("servico-2").withNome("Serviço 2").withSegmentosDaSociedade(singletonList(EMPRESAS)));
        servicos.save(SERVICO.withId("servico-3").withNome("Serviço 3").withSegmentosDaSociedade(asList(CIDADAOS, ORGAOS_E_ENTIDADES_PUBLICAS)));
        servicos.save(SERVICO.withId("servico-4").withNome("Serviço 4").withSegmentosDaSociedade(singletonList(CIDADAOS)));

        assertThat(servicos.findBySegmentoDaSociedade(CIDADAOS), contains(
                hasProperty("id", is("servico-1")),
                hasProperty("id", is("servico-3")),
                hasProperty("id", is("servico-4"))
        ));

        assertThat(servicos.findBySegmentoDaSociedade(EMPRESAS), contains(
                hasProperty("id", is("servico-1")),
                hasProperty("id", is("servico-2"))
        ));

        assertThat(servicos.findBySegmentoDaSociedade(DEMAIS_SEGMENTOS), is(empty()));
    }
}
