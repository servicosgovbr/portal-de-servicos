package br.gov.servicos.v3.schema;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;

import static java.util.Locale.getDefault;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AreaDeInteresseFormatterTest {

    AreaDeInteresse.Formatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new AreaDeInteresse.Formatter();
    }

    @Test
    public void testParseComIdValido() throws Exception {
        assertThat(formatter.parse("fomento-ao-trabalho", getDefault()), is(AreaDeInteresse.VCGE2_FOMENTO_AO_TRABALHO));
        assertThat(formatter.parse("urbanismo", getDefault()), is(AreaDeInteresse.VCGE2_URBANISMO));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void testParseComIdInvalido() throws Exception {
        formatter.parse(null, getDefault());
    }
}