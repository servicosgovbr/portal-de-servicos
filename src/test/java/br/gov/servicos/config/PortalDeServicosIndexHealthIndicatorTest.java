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

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static br.gov.servicos.config.PortalDeServicosIndex.PERSISTENTE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class PortalDeServicosIndexHealthIndicatorTest {

    @Mock
    ElasticsearchTemplate es;

    PortalDeServicosIndexHealthIndicator indicator;

    @Before
    public void setUp() throws Exception {
        indicator = new PortalDeServicosIndexHealthIndicator(es);
    }

    @Test
    public void retornaUpParaAmbosIndicesCriados() throws Exception {
        given(es.count(any(SearchQuery.class))).willReturn(42L);

        given(es.indexExists(IMPORTADOR)).willReturn(true);
        given(es.indexExists(PERSISTENTE)).willReturn(true);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.UP));
        assertThat(health.getDetails().get(IMPORTADOR), is("ok (42 docs)"));
        assertThat(health.getDetails().get(PERSISTENTE), is("ok (42 docs)"));
    }

    @Test
    public void retornaDownParaAmbosIndicesNaoCriados() throws Exception {
        given(es.indexExists(IMPORTADOR)).willReturn(false);
        given(es.indexExists(PERSISTENTE)).willReturn(false);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(IMPORTADOR), is("missing"));
        assertThat(health.getDetails().get(PERSISTENTE), is("missing"));
    }

    @Test
    public void retornaDownParaExcecoes() throws Exception {
        given(es.indexExists(IMPORTADOR)).willReturn(false);
        given(es.indexExists(PERSISTENTE)).willThrow(new RuntimeException("boom"));

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(PERSISTENTE), is("exception"));
        assertThat(health.getDetails().get(PERSISTENTE), is("exception"));
        assertThat(health.getDetails().get("error"), is("java.lang.RuntimeException: boom"));
    }

}