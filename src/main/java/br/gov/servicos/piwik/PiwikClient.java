package br.gov.servicos.piwik;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Cacheable("piwik")
public class PiwikClient {

    RestTemplate restTemplate;
    String authToken;
    String url;
    int site;

    @Autowired
    public PiwikClient(RestTemplate restTemplate, String url, String authToken, int site) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.authToken = authToken;
        this.site = site;
    }

    public List<PiwikPage> getPageUrls(String period, String date) {
        return pageUrlsRequest(period, date)
                .stream()
                .flatMap(PiwikPage::flattened)
                .collect(toList());
    }

    private List<PiwikPage> pageUrlsRequest(String period, String date) {
        URI uri = buildURI(period, date);
        log.debug("Fazendo requisição fora do cache ao Piwik: {}", uri);
        ResponseEntity<PiwikPage[]> entity = restTemplate.getForEntity(uri, PiwikPage[].class);
        log.debug("Resposta fora do cache do Piwik: {}", entity.getStatusCode());
        return asList(entity.getBody());
    }

    private URI buildURI(String period, String date) {
        return fromHttpUrl(url)
                .queryParam("module", "API")
                .queryParam("format", "json")
                .queryParam("idSite", site)
                .queryParam("token_auth", authToken)
                .queryParam("method", "Actions.getPageUrls")
                .queryParam("period", period)
                .queryParam("date", date)
                .queryParam("expanded", 1)
                .build()
                .toUri();
    }

}
