package br.gov.servicos.legado;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ImportadorAutomaticoTest {

    @Mock
    Importador importador;

    ImportadorAutomatico importadorAutomatico;

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