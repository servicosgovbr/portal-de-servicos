package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FilenameFilter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorParaConteudoDePaginasTest {

    @Mock
    ConteudoParser parser;

    @Mock
    RepositorioCartasServico cartasServico;

    @Test
    public void deveConverterPaginasEmConteudos() throws Exception {
        Resource dir = mock(Resource.class);
        given(cartasServico.get("conteudo/paginas-especiais")).willReturn(dir);
        File dirF = mock(File.class);
        given(dir.getFile()).willReturn(dirF);
        given(dirF.listFiles(any(FilenameFilter.class))).willReturn(new File[]{new File("acessibilidade.md"), new File("mapa-do-site.md")});

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