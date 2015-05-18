package br.gov.servicos;

import br.gov.servicos.importador.Importador;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImportadorIntegrationTest {
    @Autowired
    Importador importador;

    @Test
    public void deveRodarImportador() throws Exception {
        importador.importar();
    }
}