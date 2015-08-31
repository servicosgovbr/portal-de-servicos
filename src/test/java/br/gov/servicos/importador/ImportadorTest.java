package br.gov.servicos.importador;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImportadorTest {

    @Mock
    ImportadorCartasDeServico importadorCartasDeServico;

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
    public void deveRodarImportadorDeCartasDeServico() {
        importador.importar();
        verify(importadorCartasDeServico).importar(any(File.class));
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