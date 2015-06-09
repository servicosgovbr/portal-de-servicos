package br.gov.servicos.importador;

import br.gov.servicos.foundation.git.ComandosGit;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class ImportadorCartasDeServicoTest {

    private static final String URL_REPOSITORIO = "https://repositorio-de-cartas";
    private static final String CAMINHO_LOCAL = "/opt/cartas";

    @Mock
    ComandosGit git;

    @Test
    public void deveClonarRepositorioDeCartasDeServico() {
        new ImportadorCartasDeServico(git, URL_REPOSITORIO, CAMINHO_LOCAL, true).importar();
        verify(git).clonaOuAtualizaRepositorio(URL_REPOSITORIO, CAMINHO_LOCAL);
    }

    @Test
    public void naoDeveImportarCartasSeFlagEstiverDesligada() {
        new ImportadorCartasDeServico(git, URL_REPOSITORIO, CAMINHO_LOCAL, false).importar();
        verifyZeroInteractions(git);
    }

}