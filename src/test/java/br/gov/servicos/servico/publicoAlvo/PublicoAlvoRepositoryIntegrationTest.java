package br.gov.servicos.servico.publicoAlvo;

import br.gov.servicos.Main;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class PublicoAlvoRepositoryIntegrationTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    PublicoAlvoRepository publicosAlvo;

    @Before
    public void setUp() throws Exception {
        servicos.deleteAll();

        servicos.save(SERVICO
                .withId("servico-1").withSegmentosDaSociedade(asList(
                        new PublicoAlvo().withId("cidadaos").withTitulo("Cidadãos"),
                        new PublicoAlvo().withId("empresas").withTitulo("Empresas"))));

        servicos.save(SERVICO
                .withId("servico-2").withSegmentosDaSociedade(singletonList(
                        new PublicoAlvo().withId("empresas").withTitulo("Empresas"))));

        servicos.save(SERVICO
                .withId("servico-3").withSegmentosDaSociedade(singletonList(
                        new PublicoAlvo().withId("cidadaos").withTitulo("Cidadãos")
                )));
    }

    @Test
    public void deveAgruparPublicosAlvoPorTitulo() {
        assertThat(publicosAlvo.findAll(),
                equalTo(asList(
                        new PublicoAlvo().withId("cidadaos").withTitulo("Cidadãos"),
                        new PublicoAlvo().withId("empresas").withTitulo("Empresas"))));
    }
}