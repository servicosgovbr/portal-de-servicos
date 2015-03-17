package br.gov.servicos.legado;

import br.gov.servicos.Main;
import br.gov.servicos.servico.LinhaDaVida;
import br.gov.servicos.servico.Servico;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static java.util.Arrays.asList;
import static org.elasticsearch.common.collect.Iterables.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class ImportadorIntegrationTest {
    @Autowired
    Importador importador;

    @Test
    @Ignore
    public void deveMapearCertosTermosDeLinhaDeVidaDoServicoLegado() throws Exception {
        assertThat(importaServico().getLinhasDaVida(),
                equalTo(asList(new LinhaDaVida()
                        .withId("funciona")
                        .withTitulo("Funciona"))));
    }

    private Servico importaServico() throws IOException, JAXBException {
        return get(importador.importar(), 0);
    }
}
