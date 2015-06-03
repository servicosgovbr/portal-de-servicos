package br.gov.servicos.importador;

import br.gov.servicos.Main;
import br.gov.servicos.servico.ServicoRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImportadorIntegrationTest {

    @Autowired
    Importador importador;

    @Autowired
    ServicoRepository servicos;

    @Test
    public void deveRodarImportador() throws Exception {
        importador.importar();

        assertThat(servicos.findOne("passaporte").getDescricao().split("\n").length, is(28));
    }
}