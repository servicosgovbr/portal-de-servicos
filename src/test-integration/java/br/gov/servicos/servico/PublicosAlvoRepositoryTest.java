package br.gov.servicos.servico;

import br.gov.servicos.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class PublicosAlvoRepositoryTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    PublicosAlvoRepository publicosAlvo;

    @Before
    public void setUp() throws Exception {
        servicos.deleteAll();

        servicos.save(SERVICO
                .withId("servico-1").withPublicosAlvo(asList(
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às empresas"),
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos cidadãos"))));

        servicos.save(SERVICO
                .withId("servico-2").withPublicosAlvo(asList(
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às empresas"))));

        servicos.save(SERVICO
                .withId("servico-3").withPublicosAlvo(asList(
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos cidadãos"))));
    }

    @Test
    public void deveAgruparPublicosAlvoPorTitulo() {
        assertThat(publicosAlvo.findAll(),
                equalTo(asList(
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos cidadãos"),
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às empresas"))));
    }
}