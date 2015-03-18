package br.gov.servicos.servico;

import br.gov.servicos.ElasticSearchTest;
import br.gov.servicos.Main;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@FieldDefaults(level = PRIVATE)
public class LinhasDaVidaRepositoryTest extends ElasticSearchTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    LinhasDaVidaRepository linhasDaVida;

    @Test
    public void listaLinhasDaVidaEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1").withLinhasDaVida(asList(
                        new LinhaDaVida().withId("meu-negocio").withTitulo("Meu negócio"),
                        new LinhaDaVida().withId("indo-para-outro-pais").withTitulo("Indo para outro país"))));

        servicos.save(SERVICO
                .withId("servico-2").withLinhasDaVida(asList(
                        new LinhaDaVida().withId("meu-negocio").withTitulo("Meu negócio"))));

        servicos.save(SERVICO
                .withId("servico-3").withLinhasDaVida(asList(
                        new LinhaDaVida().withId("aposentar-se").withTitulo("Aposentar-se"))));

        List<LinhaDaVida> linhas = linhasDaVida.findAll();

        assertThat(linhas, is(not(empty())));
        assertThat(linhas.get(0).getId(), is("aposentar-se"));
        assertThat(linhas.get(0).getServicos(), is(1L));

        assertThat(linhas.get(2).getId(), is("meu-negocio"));
        assertThat(linhas.get(2).getServicos(), is(2L));
    }
}
