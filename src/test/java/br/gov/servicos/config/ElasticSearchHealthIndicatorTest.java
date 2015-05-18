package br.gov.servicos.config;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.actuate.health.Status;

import static org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus.GREEN;
import static org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus.RED;
import static org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus.YELLOW;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class ElasticSearchHealthIndicatorTest {

    @Mock
    Client client;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    AdminClient admin;

    ElasticSearchHealthIndicator indicator;

    @Before
    public void setUp() throws Exception {
        given(client.admin()).willReturn(admin);
        indicator = new ElasticSearchHealthIndicator(client);
    }

    @Test
    public void deveRetornarUpQuandoClusterEstáSaudável() throws Exception {
        given(admin.cluster().health(anyObject()).actionGet().getStatus()).willReturn(GREEN);

        assertThat(indicator.health().getStatus(), is(Status.UP));
    }

    @Test
    public void deveRetornarDownQuandoClusterTemPoucosNodos() throws Exception {
        given(admin.cluster().health(anyObject()).actionGet().getStatus()).willReturn(YELLOW);

        assertThat(indicator.health().getStatus(), is(Status.DOWN));
        assertThat(indicator.health().getDetails().get("status"), is(YELLOW));
    }

    @Test
    public void deveRetornarDownQuandoClusterNãoTemConexão() throws Exception {
        given(admin.cluster().health(anyObject()).actionGet().getStatus()).willReturn(RED);

        assertThat(indicator.health().getStatus(), is(Status.DOWN));
        assertThat(indicator.health().getDetails().get("status"), is(RED));
    }

    @Test
    public void deveRetornarDownQuandoHáExceçãoConsultandoOCluster() throws Exception {
        given(admin.cluster().health(anyObject()).actionGet().getStatus()).willThrow(new RuntimeException("boom"));

        assertThat(indicator.health().getStatus(), is(Status.DOWN));
        assertThat(indicator.health().getDetails().get("status"), is(nullValue()));
        assertThat(indicator.health().getDetails().get("error"), is("java.lang.RuntimeException: boom"));
    }
}