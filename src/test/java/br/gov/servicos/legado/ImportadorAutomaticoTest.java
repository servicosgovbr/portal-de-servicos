package br.gov.servicos.legado;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorAutomaticoTest {

    @Mock
    private Importador importador;

    private ImportadorAutomatico importadorAutomatico;

    @Before
    public void setUp() throws Exception {
        importadorAutomatico = new ImportadorAutomatico(importador);
    }

    @Test
    public void deveImportarOsServicosLegadosAoCarregarAAplicacao() throws Exception {
        importadorAutomatico.appCarregada();
        verify(importador).importar();
    }
}