package br.gov.servicos.v3.schema;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.orgao.Siorg;
import br.gov.servicos.servico.ServicoRepository;
import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static br.gov.servicos.fixtures.TestData.PAGINA_ORGAO;
import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Locale.getDefault;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ServicoXMLFormatterTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    OrgaoRepository orgaoRepository;

    @Mock
    Siorg siorg;

    ServicoXML.Formatter formatter;

    @Before
    public void setUp() throws Exception {
        given(orgaoRepository.findOne(any()))
                .willReturn(PAGINA_ORGAO);
        formatter = new ServicoXML.Formatter(new Slugify(), siorg, servicos, orgaoRepository);
    }

    @Test
    public void testParseComIdValido() throws Exception {
        given(servicos.findOne("foo")).willReturn(SERVICO.withId("foo"));
        ServicoXML servico = formatter.parse("foo", getDefault());
        assertThat(servico, is(SERVICO.withId("foo")));
    }

    @Test
    public void testParseComIdMaligno() throws Exception {
        given(servicos.findOne("foo-drop-table")).willReturn(SERVICO.withId("foo"));
        ServicoXML servico = formatter.parse("foo'; -- DROP TABLE *", getDefault());
        assertThat(servico, is(SERVICO.withId("foo")));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void testParseComIdInvalido() throws Exception {
        given(servicos.findOne("foo")).willReturn(null);
        formatter.parse("foo", getDefault());
    }

    @Test
    public void testPrint() throws Exception {
        assertThat(formatter.print(SERVICO.withId(null), getDefault()), is(nullValue()));
        assertThat(formatter.print(SERVICO.withId("foo"), getDefault()), is("foo"));
    }
}