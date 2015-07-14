package br.gov.servicos.servico.areaDeInteresse;

import br.gov.servicos.Main;
import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
import junit.framework.TestCase;
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
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@FieldDefaults(level = PRIVATE)
public class AreaDeInteresseRepositoryIntegrationTest extends TestCase {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    AreaDeInteresseRepository areasDaVida;

    @Autowired
    PortalDeServicosIndex esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriar();
    }

    @Test
    public void listaAreasDeInteresseEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withId("servico-1").withAreasDeInteresse(asList(
                        new AreaDeInteresse().withId("habitacao").withArea("Habitação"),
                        new AreaDeInteresse().withId("educacao-a-distancia").withArea("Educação à distância")
                )));

        servicos.save(SERVICO
                .withId("servico-2").withAreasDeInteresse(asList(
                        new AreaDeInteresse().withId("abastecimento").withArea("Abastecimento")
                )));

        servicos.save(SERVICO
                .withId("servico-3").withAreasDeInteresse(asList(
                        new AreaDeInteresse().withId("agropecuaria").withArea("Agropecuária")
                )));

        List<AreaDeInteresse> linhas = areasDaVida.findAll();

        assertThat(linhas, is(not(empty())));
        assertThat(linhas.get(0).getId(), is("abastecimento"));

        assertThat(linhas.get(2).getId(), is("educacao-a-distancia"));
    }

}