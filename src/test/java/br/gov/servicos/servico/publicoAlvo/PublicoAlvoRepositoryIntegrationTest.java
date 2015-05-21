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
                .withId("servico-1").withPublicosAlvo(asList(
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às Empresas"),
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos"))));

        servicos.save(SERVICO
                .withId("servico-2").withPublicosAlvo(singletonList(
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às Empresas"))));

        servicos.save(SERVICO
                .withId("servico-3").withPublicosAlvo(singletonList(
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos"))));
    }

    @Test
    public void deveAgruparPublicosAlvoPorTitulo() {
        assertThat(publicosAlvo.findAll(),
                equalTo(asList(
                        new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos"),
                        new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às Empresas"))));
    }
}