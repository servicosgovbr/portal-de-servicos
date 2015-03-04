package br.gov.servicos.cms;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeneroTest {

    Genero genero = new Genero();

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmDe() throws Exception {
        assertThat(genero.de("secretaria-do-secretariado"), is("da"));
        assertThat(genero.de("ministerio-do-ministeriado"), is("do"));
    }

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmPer() throws Exception {
        assertThat(genero.per("secretaria-do-secretariado"), is("pela"));
        assertThat(genero.per("ministerio-do-ministeriado"), is("pelo"));
    }
}