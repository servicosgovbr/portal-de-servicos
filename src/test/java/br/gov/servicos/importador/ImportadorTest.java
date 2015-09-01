package br.gov.servicos.importador;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImportadorTest {

    @Mock
    ImportadorV3 importadorV3;

    @Mock
    ImportadorConteudo importadorConteudo;

    Importador importador;

    @Before
    public void setUp() throws Exception {
        importador = new Importador(importadorV3, importadorConteudo);
    }

    @Test
    public void deveRodarImportadorDeCartas() throws Exception {
        importador.importar();
        verify(importadorV3).importar();
    }

    @Test
    public void deveRodarImportadorDeConteudo() throws Exception {
        importador.importar();
        verify(importadorConteudo).importar();
    }

}