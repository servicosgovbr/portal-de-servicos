package br.gov.servicos.config;

import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static br.gov.servicos.config.PortalDeServicosIndex.PORTAL_DE_SERVICOS_INDEX;
import static lombok.AccessLevel.PRIVATE;
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

        verify(es, never()).deleteIndex(eq(PORTAL_DE_SERVICOS_INDEX));
        verify(es).createIndex(eq(PORTAL_DE_SERVICOS_INDEX), anyString());
    }

    @Test
    public void deveDeletarECriarIndicePDSImportadorQuandoExistir() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(PORTAL_DE_SERVICOS_INDEX);

        esConfig.recriar();

        verify(es).deleteIndex(eq(PORTAL_DE_SERVICOS_INDEX));
        verify(es).createIndex(eq(PORTAL_DE_SERVICOS_INDEX), anyString());
    }

    @Test
    public void deveAdicionarMapeamentos() throws Exception {
        esConfig.recriar();
        verify(es).putMapping(ServicoXML.class);
    }
}