package br.gov.servicos.servico;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EtapaTest {

    @Test
    public void deveSerVazioQuandoNaoTemTituloOuDescricao() throws Exception {
        assertThat(new Etapa().isEmpty(), is(true));
        assertThat(new Etapa().withTitulo("").isEmpty(), is(true));
        assertThat(new Etapa().withDescricao("").isEmpty(), is(true));

        assertThat(new Etapa().withTitulo("Blah").isEmpty(), is(false));
        assertThat(new Etapa().withDescricao("Blah").isEmpty(), is(false));
        assertThat(new Etapa().withTitulo("Blah").withDescricao("Blah").isEmpty(), is(false));
    }
}