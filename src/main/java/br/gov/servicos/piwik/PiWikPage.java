package br.gov.servicos.piwik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * http://developer.piwik.org/api-reference/reporting-api
 * Retorno do endpoint Actions.getPageUrls
 */
@Wither
@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PiWikPage {

    String label;

    @JsonProperty("nb_visits")
    Long visitors;

    @JsonProperty("nb_uniq_visitors")
    Long uniqueVisitors;

    public PiWikPage() {
        this("", 0L, 0L);
    }
}
