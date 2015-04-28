package br.gov.servicos.cms;

import lombok.experimental.FieldDefaults;
import org.junit.Test;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class GeneroTest {

    Genero genero = new Genero();

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmDe() throws Exception {
        assertThat(genero.de("secretaria-do-secretariado"), is("da"));
        assertThat(genero.de("ministerio-do-ministeriado"), is("do"));
    }

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmLo() throws Exception {
        assertThat(genero.lo("secretaria-do-secretariado"), is("la"));
        assertThat(genero.lo("ministerio-do-ministeriado"), is("lo"));
    }

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmLos() throws Exception {
        assertThat(genero.los("secretaria-do-secretariado"), is("las"));
        assertThat(genero.los("ministerio-do-ministeriado"), is("los"));
    }

    @Test
    public void escolheGeneroDeAcordoComPalavraInicialEmPer() throws Exception {
        assertThat(genero.per("secretaria-do-secretariado"), is("pela"));
        assertThat(genero.per("ministerio-do-ministeriado"), is("pelo"));
    }
}
