package br.gov.servicos.importador;

import br.gov.servicos.cms.PaginaTematica;
import br.gov.servicos.cms.PaginaTematicaRepository;
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
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorParaPaginasTematicasTest {

    @Mock
    ConteudoParser parser;

    @Mock
    RepositorioCartasServico cartasServico;

    @Mock
    PaginaTematicaRepository paginaRepository;

    @Test
    public void deveConverterPaginasEmConteudos() throws Exception {
        Resource dir = mock(Resource.class);
        given(cartasServico.get("conteudo/paginas-especiais")).willReturn(dir);
        File dirF = mock(File.class);
        given(dir.getFile()).willReturn(dirF);
        given(dirF.listFiles(any(FilenameFilter.class))).willReturn(new File[]{new File("acessibilidade.md"), new File("mapa-do-site.md")});

        given(parser.conteudoHtml(any(Resource.class))).willReturn("<html><h2>Acessibilidade</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>");
        given(parser.titulo(any(Resource.class))).willReturn("Acessibilidade");
        given(parser.conteudo(any(Resource.class))).willReturn("Parágrafo um. Parágrafo dois.");

        PaginaTematica c = new ImportadorParaPaginasTematicas(parser, paginaRepository).importar(cartasServico).iterator().next();

        assertThat(c.getId(), is("acessibilidade"));
        assertThat(c.getTipoConteudo(), is("conteudo"));
        assertThat(c.getNome(), is("Acessibilidade"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
        assertThat(c.getHtml(), is("<html><h2>Acessibilidade</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>"));
    }

}