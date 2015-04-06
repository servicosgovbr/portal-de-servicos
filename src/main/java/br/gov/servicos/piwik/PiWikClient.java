package br.gov.servicos.piwik;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PiWikClient {
    RestTemplate restTemplate;
    String authToken;
    String url;
    String format;
    int idSite;

    @Autowired
    public PiWikClient(RestTemplate restTemplate, String url, String authToken, int idSite, String format) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.authToken = authToken;
        this.idSite = idSite;
        this.format = format;
    }

    public List<PiWikPage> getPageUrls(String period, String date) {
        return Arrays.asList(
                restTemplate.getForEntity(
                    buildCommonParams()
                            .queryParam("method", "Actions.getPageUrls")
                            .queryParam("period", period)
                            .queryParam("date", date)
                            .build()
                            .toUri(),
                    PiWikPage[].class)
                        .getBody());
    }

    private UriComponentsBuilder buildCommonParams() {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("module", "API")
                .queryParam("format", format)
                .queryParam("idSite", idSite)
                .queryParam("token_auth", authToken);
    }
}
