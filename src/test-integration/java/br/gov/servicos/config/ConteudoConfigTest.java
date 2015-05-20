package br.gov.servicos.config;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static br.gov.servicos.fixtures.TestData.ORGAOS;
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
        assertThat(config.linhaDaVida("Abrir um negócio").getTitulo(), is("Empreendedorismo e negócios"));
        assertThat(config.linhaDaVida("Trabalhando").getTitulo(), is("Profissão e trabalho"));
    }

    @Test
    public void mapeiaOrgaos() throws Exception {
        assertThat(config.orgao("Ministério do Turismo").getNome(), is("Ministério do Turismo (MTur)"));
        assertThat(config.orgao("Ministério do Turismo - MTur").getNome(), is("Ministério do Turismo (MTur)"));
        assertThat(config.orgao("Ministério do Turismo- MTur").getNome(), is("Ministério do Turismo (MTur)"));
    }

    @Test
    public void mapeiaLinksParaOuvidoriasDeOrgaos() throws Exception {
        assertThat(config.ouvidoria(ORGAOS.get(0).getId()).get(),
                is("http://www.ouvidorias.gov.br/cidadao/lista-de-ouvidorias/adm_direta/arquivo-nacional-an"));

        assertThat(config.ouvidoria(ORGAOS.get(1).getId()).get(),
                is("http://www.ouvidorias.gov.br/cidadao/lista-de-ouvidorias/bancos/banco-central-do-brasil-bacen"));
    }

    @Test
    public void mapeiaLinksParaSitesOficiaisDeOrgaos() throws Exception {
        assertThat(config.siteOficial(ORGAOS.get(0).getId()).get(),
                is("http://www.arquivonacional.gov.br/"));

        assertThat(config.siteOficial(ORGAOS.get(1).getId()).get(),
                is("http://www.bcb.gov.br/"));
    }
}