package br.gov.servicos.importador;

import br.gov.servicos.foundation.git.ComandosGit;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class ImportadorCartasDeServicoTest {

    private static final String URL_REPOSITORIO = "https://repositorio-de-cartas";
    private static final String CAMINHO_LOCAL = "/opt/cartas";
    private static final String ULTIMO_COMMIT = "sha1243546546";

    @Mock
    ComandosGit git;

    @Before
    public void setUp() throws Exception {
        given(git.clonaOuAtualizaRepositorio(URL_REPOSITORIO, CAMINHO_LOCAL)).willReturn(ULTIMO_COMMIT);
    }

    @Test
    public void deveClonarRepositorioDeCartasDeServico() {
        new ImportadorCartasDeServico(git, URL_REPOSITORIO, CAMINHO_LOCAL, true).importar();
        verify(git).clonaOuAtualizaRepositorio(URL_REPOSITORIO, CAMINHO_LOCAL);
    }

    @Test
    public void deveRetornarOHashDoUltimoCommitNoRepositorioDeCartas() {
        String ultimoCommit = new ImportadorCartasDeServico(git, URL_REPOSITORIO, CAMINHO_LOCAL, true).importar();
        assertThat(ultimoCommit, is(ULTIMO_COMMIT));
    }

    @Test
    public void naoDeveImportarCartasSeFlagEstiverDesligada() {
        new ImportadorCartasDeServico(git, URL_REPOSITORIO, CAMINHO_LOCAL, false).importar();
        verifyZeroInteractions(git);
    }

}