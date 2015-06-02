package br.gov.servicos.piwik;

import br.gov.servicos.config.PiwikConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class PiwikClientTest {

    static final String PERIOD = "day";
    static final String DATE = "04-04-2015";
    static final int SITE = 2;
    static final String AUTH_TOKEN = "authToken";
    static final String PIWIK_URL = "http://piwik";

    @Mock(answer = RETURNS_SMART_NULLS)
    RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void deveRetornarListaVaziaQuandoDesabilitado() throws Exception {
        PiwikClient piwikClient = new PiwikClient(restTemplate, new PiwikConfig()
                .withEnabled(false)
                .withUrl(PIWIK_URL)
                .withToken(AUTH_TOKEN)
                .withSite(SITE)
        );

        assertThat(piwikClient.getPageUrls(PERIOD, DATE), is(empty()));

        verify(restTemplate, never()).getForEntity(any(URI.class), anyObject());
    }

    @Test
    public void deveFazerChamadaAoPiwikQuandoHabilitado() throws Exception {
        when(restTemplate.getForEntity(
                        new URI(PIWIK_URL + "?" +
                                "module=API" +
                                "&" +
                                "format=json" +
                                "&" +
                                "idSite=" + SITE +
                                "&" +
                                "token_auth=" + AUTH_TOKEN +
                                "&" +
                                "method=Actions.getPageUrls" +
                                "&" +
                                "period=" + PERIOD +
                                "&" +
                                "date=" + DATE +
                                "&" +
                                "expanded=1"),
                        PiwikPage[].class)
        ).thenReturn(
                new ResponseEntity<>(
                        new PiwikPage[]{
                                new PiwikPage("http://site.com:80/page-url", 100L, 10L, new PiwikPage[0])
                        }, OK));

        PiwikClient piwikClient = new PiwikClient(restTemplate, new PiwikConfig()
                .withEnabled(true)
                .withUrl(PIWIK_URL)
                .withToken(AUTH_TOKEN)
                .withSite(SITE));

        List<PiwikPage> urls = piwikClient.getPageUrls(PERIOD, DATE);
        assertThat(urls.get(0).getPath(), is(Optional.of("/page-url")));
    }
}