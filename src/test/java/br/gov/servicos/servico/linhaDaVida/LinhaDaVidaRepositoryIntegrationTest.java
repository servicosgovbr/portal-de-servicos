package br.gov.servicos.servico.linhaDaVida;

import br.gov.servicos.Main;
import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
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
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@FieldDefaults(level = PRIVATE)
public class LinhaDaVidaRepositoryIntegrationTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    LinhaDaVidaRepository linhasDaVida;

    @Autowired
    PortalDeServicosIndex esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriar();
    }

    @Test
    public void listaLinhasDaVidaEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1").withEventosDaLinhaDaVida(asList(
                        new LinhaDaVida().withId("meu-negocio").withTitulo("Meu negócio"),
                        new LinhaDaVida().withId("indo-para-outro-pais").withTitulo("Indo para outro país"))));

        servicos.save(SERVICO
                .withId("servico-2").withEventosDaLinhaDaVida(singletonList(
                        new LinhaDaVida().withId("meu-negocio").withTitulo("Meu negócio"))));

        servicos.save(SERVICO
                .withId("servico-3").withEventosDaLinhaDaVida(singletonList(
                        new LinhaDaVida().withId("aposentadoria").withTitulo("Aposentadoria"))));

        List<LinhaDaVida> linhas = linhasDaVida.findAll();

        assertThat(linhas, is(not(empty())));
        assertThat(linhas.get(0).getId(), is("aposentadoria"));
        assertThat(linhas.get(0).getServicos(), is(1L));

        assertThat(linhas.get(2).getId(), is("meu-negocio"));
        assertThat(linhas.get(2).getServicos(), is(2L));
    }


}
