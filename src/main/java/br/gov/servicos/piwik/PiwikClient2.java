package br.gov.servicos.piwik;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PiwikClient2 {
    RestTemplate restTemplate;
    String authToken;
    String url;
    int site;

    @Autowired
    public PiwikClient2(RestTemplate restTemplate, String url, String authToken, int site) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.authToken = authToken;
        this.site = site;
    }

    public List<PiwikPage2> getPageUrls(String period, String date) {
        return Arrays.asList(
                restTemplate.getForEntity(
                        buildCommonParams()
                                .queryParam("method", "Actions.getPageUrls")
                                .queryParam("period", period)
                                .queryParam("date", date)
                                .build()
                                .toUri(),
                        PiwikPage2[].class)
                        .getBody());
    }

    private UriComponentsBuilder buildCommonParams() {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("module", "API")
                .queryParam("format", "json")
                .queryParam("idSite", site)
                .queryParam("token_auth", authToken);
    }
}
