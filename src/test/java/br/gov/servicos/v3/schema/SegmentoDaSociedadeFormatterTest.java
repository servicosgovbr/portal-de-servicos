package br.gov.servicos.v3.schema;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;

import static java.util.Locale.getDefault;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SegmentoDaSociedadeFormatterTest {

    SegmentoDaSociedade.Formatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new SegmentoDaSociedade.Formatter();
    }

    @Test
    public void testParseComIdValido() throws Exception {
        assertThat(formatter.parse("cidadaos", getDefault()), is(SegmentoDaSociedade.CIDADÃOS));
        assertThat(formatter.parse("empresas", getDefault()), is(SegmentoDaSociedade.EMPRESAS));
        assertThat(formatter.parse("demais-segmentos-ongs-organizacoes-sociais-etc", getDefault()), is(SegmentoDaSociedade.DEMAIS_SEGMENTOS_ONGS_ORGANIZAÇÕES_SOCIAIS_ETC));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void testParseComIdInvalido() throws Exception {
        formatter.parse(null, getDefault());
    }

}