package br.gov.servicos;

import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.importador.ImportadorConteudo;
import br.gov.servicos.legado.ImportadorLegado;
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
    ImportadorLegado importadorLegado;

    @Mock
    ImportadorConteudo importadorConteudo;

    Importador importador;

    @Before
    public void setUp() throws Exception {
        importador = new Importador(guiaDeServicosIndex, importadorLegado, importadorConteudo);
    }

    @Test
    public void deveRecriarIndices() throws Exception {
        importador.importar();
        verify(guiaDeServicosIndex).recriar();
    }

    @Test
    public void deveRodarImportadorLegado() throws Exception {
        importador.importar();
        verify(importadorLegado).importar();
    }

    @Test
    public void deveRodarImportadorConteudo() throws Exception {
        importador.importar();
        verify(importadorConteudo).importar();
    }

}