package br.gov.servicos.config;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class ConteudoConfigTest {

    @Autowired
    ConteudoConfig config;

    @Test
    public void mapeiaLinhasDaVida() throws Exception {
        assertThat(config.linhaDaVida("Abrir um negócio").getTitulo(), is("Administrar um negócio"));
        assertThat(config.linhaDaVida("Trabalhando").getTitulo(), is("Trabalhar"));
    }

    @Test
    public void mapeiaOrgaos() throws Exception {
        assertThat(config.orgao("Ministério do Turismo").getNome(), is("Ministério do Turismo"));
        assertThat(config.orgao("Ministério do Turismo - MTur").getNome(), is("Ministério do Turismo"));
        assertThat(config.orgao("Ministério do Turismo- MTur").getNome(), is("Ministério do Turismo"));
    }
}