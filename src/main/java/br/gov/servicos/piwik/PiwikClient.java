package br.gov.servicos.piwik;

import br.gov.servicos.config.PiwikConfig;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PiwikClient {

    RestTemplate restTemplate;

    PiwikConfig config;

    @Autowired
    public PiwikClient(RestTemplate restTemplate, PiwikConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<PiwikPage> getPageUrls(String period, String date) {
        if (!config.isEnabled()) {
            return emptyList();
        }

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
        return fromHttpUrl(config.getUrl())
                .queryParam("module", "API")
                .queryParam("format", "json")
                .queryParam("idSite", config.getSite())
                .queryParam("token_auth", config.getToken())
                .queryParam("method", "Actions.getPageUrls")
                .queryParam("period", period)
                .queryParam("date", date)
                .queryParam("expanded", 1)
                .build()
                .toUri();
    }

}
