package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.servico.Orgao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorParaConteudoDeOrgaosTest {

    @Mock
    OrgaoRepository repository;

    @Mock
    ConteudoParser parser;

    @Test
    public void deveConverterOrgaosEmConteudos() throws Exception {
        given(repository.findAll()).willReturn(singletonList(new Orgao().withId("ministerio-da-verdade-mv").withNome("Ministério da Verdade").withTelefone("166")));
        given(parser.conteudo("/conteudo/orgaos/ministerio-da-verdade-mv.md")).willReturn("Parágrafo um. Parágrafo dois.");

        Conteudo c = new ImportadorParaConteudoDeOrgaos(repository, parser).importar().findFirst().get();

        assertThat(c.getId(), is("ministerio-da-verdade-mv"));
        assertThat(c.getTipoConteudo(), is("orgao"));
        assertThat(c.getTitulo(), is("Ministério da Verdade"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
    }
}