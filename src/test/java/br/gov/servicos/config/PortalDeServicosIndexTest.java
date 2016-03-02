package br.gov.servicos.config;

import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static br.gov.servicos.config.PortalDeServicosIndex.PERSISTENTE;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class PortalDeServicosIndexTest {

    PortalDeServicosIndex esConfig;

    @Mock
    ElasticsearchTemplate es;

    @Before
    public void setUp() {
        esConfig = new PortalDeServicosIndex(es);
    }

    @Test
    public void deveCriarIndicePDSImportadorQuandoNaoExiste() throws Exception {
        esConfig.recriar();

        verify(es, never()).deleteIndex(eq(IMPORTADOR));
        verify(es).createIndex(eq(IMPORTADOR), anyString());
    }

    @Test
    public void deveDeletarECriarIndicePDSImportadorQuandoExistir() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(IMPORTADOR);

        esConfig.recriar();

        verify(es).deleteIndex(eq(IMPORTADOR));
        verify(es).createIndex(eq(IMPORTADOR), anyString());
    }

    @Test
    public void deveAdicionarMapeamentos() throws Exception {
        esConfig.recriar();
        verify(es).putMapping(ServicoXML.class);
    }

    @Test
    public void deveCriarIndicePDSPersistenteQuandoNaoExiste() throws Exception {
        esConfig.recriar();
        verify(es).createIndex(eq(PERSISTENTE), anyString());
    }

    @Test
    public void naoDeveCriarIndicePDSPersistenteSeExistir() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(PERSISTENTE);

        esConfig.recriar();

        verify(es, never()).createIndex(eq(PERSISTENTE), anyString());
    }
}