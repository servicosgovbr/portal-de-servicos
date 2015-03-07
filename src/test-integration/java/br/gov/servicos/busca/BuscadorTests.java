package br.gov.servicos.busca;

import br.gov.servicos.Main;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class BuscadorTests {

    @Autowired
    ServicoRepository servicos;

    @Autowired
    Buscador buscador;

    @Test
    public void buscaPorTermosComErrosDeDigitacao() {
        servicos.save(new Servico("passaporte", "Passaporte", "Emissão de passaportes", null, null, null, null, null, null, null, null, 0L, 0L));

        List<Servico> busca = buscador.busca(of("passaprote"));
        assertThat(busca, is(not(empty())));

        Servico resultado = busca.iterator().next();
        assertThat(resultado.getId(), is("passaporte"));
    }

}
