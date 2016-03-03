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

import static br.gov.servicos.config.PortalDeServicosIndex.PORTAL_DE_SERVICOS_INDEX;
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

        given(es.indexExists(PORTAL_DE_SERVICOS_INDEX)).willReturn(true);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.UP));
        assertThat(health.getDetails().get(PORTAL_DE_SERVICOS_INDEX), is("ok (42 docs)"));
    }

    @Test
    public void retornaDownParaAmbosIndicesNaoCriados() throws Exception {
        given(es.indexExists(PORTAL_DE_SERVICOS_INDEX)).willReturn(false);

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(PORTAL_DE_SERVICOS_INDEX), is("missing"));
    }

    @Test
    public void retornaDownParaExcecoes() throws Exception {
        given(es.indexExists(PORTAL_DE_SERVICOS_INDEX)).willThrow(new RuntimeException("boom"));

        Health health = indicator.health();

        assertThat(health.getStatus(), is(Status.DOWN));
        assertThat(health.getDetails().get(PORTAL_DE_SERVICOS_INDEX), is("exception"));
        assertThat(health.getDetails().get("error"), is("java.lang.RuntimeException: boom"));
    }

}