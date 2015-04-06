package br.gov.servicos.piwik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * Retorno do endpoint Actions.getPageUrls:
 * http://developer.piwik.org/api-reference/reporting-api
 */
@Wither
@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PiwikPage2 {

    @JsonProperty("label")
    String label;

    @JsonProperty("nb_visits")
    Long visitors;

    @JsonProperty("nb_uniq_visitors")
    Long uniqueVisitors;

    public PiwikPage2() {
        this("", 0L, 0L);
    }
}
