package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.cms.PaginaEstaticaRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ImportadorConteudoTest {

    ImportadorConteudo importadorConteudo;

    @Mock
    ConteudoRepository conteudoRepository;

    @Mock
    ImportadorParaConteudoDeOrgaos orgaos;

    @Mock
    ImportadorParaPaginasTematicas paginas;

    @Mock
    ImportadorParaPaginasEstaticas estatica;

    @Mock
    RepositorioCartasServico cartasServico;

    @Mock
    PaginaEstaticaRepository paginaEstaticaRepository;

    @Before
    public void setUp() throws Exception {
        importadorConteudo = new ImportadorConteudo(conteudoRepository, paginaEstaticaRepository, orgaos, paginas, estatica);
    }

    @Test
    public void deveImportarTodosOsConteudos() throws Exception {
        Conteudo conteudo = new Conteudo()
                .withId("ministerio-da-verdade")
                .withTipoConteudo("orgao")
                .withNome("Ministério da Verdade")
                .withConteudo("Parágrafo um. Parágrafo dois.");

        given(orgaos.importar(cartasServico)).willReturn(Stream.of(conteudo));

        importadorConteudo.importar(cartasServico);

        verify(conteudoRepository).save(singletonList(conteudo));
    }

}