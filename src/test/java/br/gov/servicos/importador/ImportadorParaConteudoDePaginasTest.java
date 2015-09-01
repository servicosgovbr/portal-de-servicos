package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.cms.Markdown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorParaConteudoDePaginasTest {

    @Mock
    Markdown markdown;

    @Mock
    ConteudoParser parser;

    @Test
    public void deveConverterPaginasEmConteudos() throws Exception {
        given(markdown.toHtml(anyObject())).willReturn(new ConteudoHtml()
                        .withId("acessibilidade")
                        .withNome("Acessibilidade")
                        .withHtml("<html><h2>Acessibilidade</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>")
        );
        given(parser.conteudo("/conteudo/acessibilidade.md")).willReturn("Parágrafo um. Parágrafo dois.");

        Conteudo c = new ImportadorParaConteudoDePaginas(markdown, parser).importar().findFirst().get();

        assertThat(c.getId(), is("acessibilidade"));
        assertThat(c.getTipoConteudo(), is("conteudo"));
        assertThat(c.getNome(), is("Acessibilidade"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
    }

}