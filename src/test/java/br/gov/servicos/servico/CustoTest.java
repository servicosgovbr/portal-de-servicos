package br.gov.servicos.servico;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustoTest {

    @Test
    public void deveSerVazioQuandoNaoTemDescricaoOuValor() throws Exception {
        assertThat(new Custo().isEmpty(), is(true));
        assertThat(new Custo().withDescricao("").isEmpty(), is(true));
        assertThat(new Custo().withDescricao("Blah").isEmpty(), is(true));
        assertThat(new Custo().withValor("").isEmpty(), is(true));
        assertThat(new Custo().withValor("Blah").isEmpty(), is(true));

        assertThat(new Custo().withDescricao("Blah").withValor("Blah").isEmpty(), is(false));
    }
}