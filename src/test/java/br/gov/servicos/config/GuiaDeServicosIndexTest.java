package br.gov.servicos.config;

import br.gov.servicos.metricas.Feedback;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_IMPORTADOR;
import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_PERSISTENTE;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class GuiaDeServicosIndexTest {

    GuiaDeServicosIndex esConfig;

    @Mock
    ElasticsearchTemplate es;

    @Before
    public void setUp() {
        esConfig = new GuiaDeServicosIndex(es);
    }

    @Test
    public void deveCriarIndiceGDSImportadorQuandoNaoExiste() throws Exception {
        esConfig.recriar();

        verify(es, never()).deleteIndex(eq(GDS_IMPORTADOR));
        verify(es).createIndex(eq(GDS_IMPORTADOR), anyString());
    }

    @Test
    public void deveDeletarECriarIndiceGDSImportadorQuandoExistir() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(GDS_IMPORTADOR);

        esConfig.recriar();

        verify(es).deleteIndex(eq(GDS_IMPORTADOR));
        verify(es).createIndex(eq(GDS_IMPORTADOR), anyString());
    }

    @Test
    public void deveAdicionarMapeamentos() throws Exception {
        esConfig.recriar();
        verify(es).putMapping(Servico.class);
        verify(es).putMapping(Feedback.class);
    }

    @Test
    public void deveCriarIndiceGDSPersistenteQuandoNaoExiste() throws Exception {
        esConfig.recriar();
        verify(es).createIndex(eq(GDS_PERSISTENTE), anyString());
    }

    @Test
    public void naoDeveCriarIndiceGDSPersistenteSeExistir() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(GDS_PERSISTENTE);

        esConfig.recriar();

        verify(es, never()).createIndex(eq(GDS_PERSISTENTE), anyString());
    }
}