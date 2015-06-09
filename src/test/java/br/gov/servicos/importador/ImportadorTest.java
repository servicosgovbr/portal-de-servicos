package br.gov.servicos.importador;

import br.gov.servicos.config.GuiaDeServicosIndex;
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
    GuiaDeServicosIndex guiaDeServicosIndex;

    @Mock
    ImportadorCartasDeServico importadorCartasDeServico;

    @Mock
    ImportadorV1 importadorV1;

    @Mock
    ImportadorConteudo importadorConteudo;

    Importador importador;

    @Before
    public void setUp() throws Exception {
        importador = new Importador(guiaDeServicosIndex, importadorV1, importadorConteudo, importadorCartasDeServico);
    }

    @Test
    public void deveRecriarIndices() throws Exception {
        importador.importar();
        verify(guiaDeServicosIndex).recriar();
    }

    @Test
    public void deveRodarImportadorDeCartasDeServico() {
        importador.importar();
        verify(importadorCartasDeServico).importar();
    }

    @Test
    public void deveRodarImportadorLegado() throws Exception {
        importador.importar();
        verify(importadorV1).importar();
    }

    @Test
    public void deveRodarImportadorConteudo() throws Exception {
        importador.importar();
        verify(importadorConteudo).importar();
    }

}