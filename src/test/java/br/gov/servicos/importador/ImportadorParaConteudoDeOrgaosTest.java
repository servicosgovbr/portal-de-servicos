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
public class ImportadorParaConteudoDeOrgaosTest {

    @Mock
    ConteudoParser parser;

    @Mock
    RepositorioCartasServico cartasServico;

    @Test
    public void deveConverterOrgaosEmConteudos() throws Exception {
        given(parser.conteudo(anyObject())).willReturn("Parágrafo um. Parágrafo dois.");

        Conteudo c = new ImportadorParaConteudoDeOrgaos(parser).importar(cartasServico).findFirst().get();

        assertThat(c.getId(), is("ministerio-da-verdade-mv"));
        assertThat(c.getTipoConteudo(), is("orgao"));
        assertThat(c.getNome(), is("Ministério da Verdade"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
    }
}