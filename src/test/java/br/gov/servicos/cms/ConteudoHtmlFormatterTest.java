package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

import static br.gov.servicos.fixtures.TestData.CONTEUDO_HTML;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ConteudoHtmlFormatterTest {

    @Mock
    Markdown markdown;

    ConteudoHtml.Formatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new ConteudoHtml.Formatter(markdown, new Slugify());
    }

    @Test
    public void testParseComIdValido() throws Exception {
        given(markdown.toHtml(new ClassPathResource("/conteudo/foo.md"))).willReturn(CONTEUDO_HTML.withId("foo"));
        assertThat(formatter.parse("foo", Locale.getDefault()), is(CONTEUDO_HTML.withId("foo")));
    }

    @Test
    public void testParseComIdMalicioso() throws Exception {
        given(markdown.toHtml(new ClassPathResource("/conteudo/foo.md"))).willReturn(CONTEUDO_HTML.withId("foo"));
        assertThat(formatter.parse("../../../foo", Locale.getDefault()), is(CONTEUDO_HTML.withId("foo")));
    }

    @Test(expected = ConteudoNaoEncontrado.class)
    public void testParseComIdInvalido() throws Exception {
        given(markdown.toHtml(any(Resource.class))).willThrow(new ConteudoNaoEncontrado());
        assertThat(formatter.parse("../../../foo", Locale.getDefault()), is(CONTEUDO_HTML.withId("foo")));
    }
}