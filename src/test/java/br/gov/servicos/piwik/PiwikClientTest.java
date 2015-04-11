package br.gov.servicos.piwik;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class PiwikClientTest {

    static final int SITE = 2;
    static final String AUTH_TOKEN = "authToken";
    static final String PIWIK_URL = "http://piwik";
    public static final String PERIOD = "day";
    public static final String DATE = "04-04-2015";

    PiwikClient piwikClient;

    @Mock(answer = RETURNS_SMART_NULLS)
    RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        piwikClient = new PiwikClient(restTemplate, PIWIK_URL, AUTH_TOKEN, SITE);
    }

    @Test
    public void fazChamadaAoPiwik() throws Exception {
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

        List<PiwikPage> urls = piwikClient.getPageUrls(PERIOD, DATE);
        assertThat(urls.get(0).getPath(), is("/page-url"));
    }
}