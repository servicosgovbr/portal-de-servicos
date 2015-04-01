package br.gov.servicos.busca;

import br.gov.servicos.ElasticSearchTest;
import br.gov.servicos.Main;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class BuscadorTests extends ElasticSearchTest {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    Buscador buscador;

    @Test
    public void buscaPorTermosComErrosDeDigitacao() {
        servicos.save(SERVICO
                .withId("passaporte")
                .withTitulo("Passaporte")
                .withDescricao("Emissão de passaportes"));

        Iterable<Servico> busca = buscador.busca(of("passaprote"), 0);
        Servico resultado = busca.iterator().next();
        assertThat(resultado.getId(), is("passaporte"));
    }

}
