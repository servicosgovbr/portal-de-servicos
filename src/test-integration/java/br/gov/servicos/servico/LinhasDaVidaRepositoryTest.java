package br.gov.servicos.servico;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class LinhasDaVidaRepositoryTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    LinhasDaVidaRepository linhasDaVida;

    @Test
    public void listaLinhasDaVida() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1")
                .withLinhasDaVida(
                        new LinhaDaVida("meu-negocio", "Meu negócio"),
                        new LinhaDaVida("administrar-um-negocio", "Administrar um negócio")));

        servicos.save(SERVICO
                .withId("servico-2")
                .withLinhasDaVida(
                        new LinhaDaVida("meu-negocio", "Meu negócio")));

        servicos.save(SERVICO
                .withId("servico-3")
                .withLinhasDaVida(
                        new LinhaDaVida("aposentar-se", "Aposentar-se")));

        List<LinhaDaVida> linhas = linhasDaVida.findAll();

        assertThat(linhas, is(not(empty())));
        assertThat(linhas.get(0).getId(), is("meu-negocio"));
    }
}