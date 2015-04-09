package br.gov.servicos.piwik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.net.URI;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

/**
 * Retorno do endpoint Actions.getPageUrls:
 * http://developer.piwik.org/api-reference/reporting-api
 */
@Wither
@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PiwikPage {

    @JsonProperty("url")
    String url;

    @JsonProperty("nb_visits")
    Long visitors;

    @JsonProperty("nb_uniq_visitors")
    Long uniqueVisitors;

    @JsonProperty("subtable")
    PiwikPage[] subPages;

    public PiwikPage() {
        this("", 0L, 0L, new PiwikPage[0]);
    }

    public String getPath() {
        return URI.create(url).getPath();
    }

    public Stream<PiwikPage> flattened() {
        return concat(
                of(this),
                stream(subPages).flatMap(PiwikPage::flattened)
        );
    }

    public boolean isServicoUrl() {
        return getPath().startsWith("/servico/");
    }
}
