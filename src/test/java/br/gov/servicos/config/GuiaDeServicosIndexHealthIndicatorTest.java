package br.gov.servicos.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_IMPORTADOR;
import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_PERSISTENTE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GuiaDeServicosIndexHealthIndicatorTest {

    @Mock
    ElasticsearchTemplate es;

    GuiaDeServicosIndexHealthIndicator indicator;

    @Before
    public void setUp() throws Exception {
        indicator = new GuiaDeServicosIndexHealthIndicator(es);
    }

    @Test
    public void retornaUpParaAmbosIndicesCriados() throws Exception {
        given(es.count(any(SearchQuery.class))).willReturn(42L);

        given(es.indexExists(GDS_IMPORTADOR)).willReturn(true);
        given(es.indexExists(GDS_PERSISTENTE)).willReturn(true);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.UP));
        assertThat(health.getDetails().get(GDS_IMPORTADOR), is("ok (42 docs)"));
        assertThat(health.getDetails().get(GDS_PERSISTENTE), is("ok (42 docs)"));
    }

    @Test
    public void retornaDownParaAmbosIndicesNaoCriados() throws Exception {
        given(es.indexExists(GDS_IMPORTADOR)).willReturn(false);
        given(es.indexExists(GDS_PERSISTENTE)).willReturn(false);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(GDS_IMPORTADOR), is("missing"));
        assertThat(health.getDetails().get(GDS_PERSISTENTE), is("missing"));
    }

    @Test
    public void retornaDownParaExcecoes() throws Exception {
        given(es.indexExists(GDS_IMPORTADOR)).willReturn(false);
        given(es.indexExists(GDS_PERSISTENTE)).willThrow(new RuntimeException("boom"));

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(GDS_PERSISTENTE), is("exception"));
        assertThat(health.getDetails().get(GDS_PERSISTENTE), is("exception"));
        assertThat(health.getDetails().get("error"), is("java.lang.RuntimeException: boom"));
    }

}