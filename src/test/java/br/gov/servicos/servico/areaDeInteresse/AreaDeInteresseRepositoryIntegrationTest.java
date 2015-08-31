package br.gov.servicos.servico.areaDeInteresse;

import br.gov.servicos.Main;
import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.AreaDeInteresse;
import br.gov.servicos.v3.schema.AreasDeInteresse;
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
import static java.util.Collections.singletonList;
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
    AreaDeInteresseRepository repositorio;

    @Autowired
    PortalDeServicosIndex esConfig;

    @Before
    public void setup() throws IOException {
        esConfig.recriar();
    }

    @Test
    public void listaAreasDeInteresseEmOrdemAlfabetica() throws Exception {
        servicos.save(SERVICO
                .withNome("Servico 1")
                .withAreasDeInteresse(new AreasDeInteresse().withItem(asList(
                        AreaDeInteresse.fromValue("Habitação"),
                        AreaDeInteresse.fromValue("Educação à distância")
                ))));

        servicos.save(SERVICO
                .withNome("Servico 2")
                .withAreasDeInteresse(new AreasDeInteresse().withItem(singletonList(
                        AreaDeInteresse.fromValue("Abastecimento")
                ))));

        servicos.save(SERVICO
                .withNome("Servico 3")
                .withAreasDeInteresse(new AreasDeInteresse().withItem(singletonList(
                        AreaDeInteresse.fromValue("Agropecuária")
                ))));

        List<AreaDeInteresse> areas = repositorio.findAll();

        assertThat(areas, is(not(empty())));
        assertThat(areas.get(0).value(), is("Abastecimento"));

        assertThat(areas.get(2).value(), is("Educacao à Distância"));
    }

}