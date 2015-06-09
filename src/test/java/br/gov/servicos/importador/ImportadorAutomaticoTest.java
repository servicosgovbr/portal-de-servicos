package br.gov.servicos.importador;

import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ImportadorAutomaticoTest {

    @Mock
    Importador importador;

    @Test
    public void deveImportarOsServicosLegadosAoCarregarAAplicacao() throws Exception {
        ImportadorAutomatico i = new ImportadorAutomatico(importador, true);
        i.appCarregada();
        verify(importador).importar();
    }

    @Test
    public void deveIgnorarOperacaoComAFlagDesligada() throws Exception {
        ImportadorAutomatico i = new ImportadorAutomatico(importador, false);
        i.appCarregada();
        verify(importador, never()).importar();
    }
}