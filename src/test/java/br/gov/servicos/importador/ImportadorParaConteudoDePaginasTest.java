package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
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
    ConteudoParser parser;

    @Mock
    RepositorioCartasServico cartasServico;

    @Test
    public void deveConverterPaginasEmConteudos() throws Exception {
        given(parser.conteudoHtml(anyObject())).willReturn("<html><h2>Acessibilidade</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>");
        given(parser.titulo(anyObject())).willReturn("Acessibilidade");
        given(parser.conteudo(anyObject())).willReturn("Parágrafo um. Parágrafo dois.");

        Conteudo c = new ImportadorParaConteudoDePaginas(parser).importar(cartasServico).findFirst().get();

        assertThat(c.getId(), is("acessibilidade"));
        assertThat(c.getTipoConteudo(), is("conteudo"));
        assertThat(c.getNome(), is("Acessibilidade"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
        assertThat(c.getHtml(), is("<html><h2>Acessibilidade</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>"));
    }

}