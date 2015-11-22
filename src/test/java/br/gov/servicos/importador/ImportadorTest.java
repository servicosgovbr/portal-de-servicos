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
    ImportadorServicos importadorServicos;

    @Mock
    ImportadorParaPaginasEstaticas importadorParaPaginasEstaticas;

    @Mock
    ImportadorParaPaginasTematicas importadorParaPaginasTematicas;

    @Mock
    ImportadorParaPaginasDeOrgao importadorParaPaginasDeOrgao;

    Importador importador;

    @Mock
    RepositorioCartasServico cartasServico;

    @Before
    public void setUp() throws Exception {
        importador = new Importador(
                cartasServico,
                importadorServicos,
                importadorParaPaginasEstaticas,
                importadorParaPaginasTematicas,
                importadorParaPaginasDeOrgao);
    }

    @Test
    public void deveRodarImportadorDeCartas() throws Exception {
        importador.importar();
        verify(importadorServicos).importar(cartasServico);
    }

    @Test
    public void deveRodarImportadorDeConteudo() throws Exception {
        importador.importar();
        verify(importadorParaPaginasEstaticas).importar(cartasServico);
    }

}