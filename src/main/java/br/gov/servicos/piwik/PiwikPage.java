package br.gov.servicos.piwik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.net.URI;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    static final Pattern urlServicoPattern = Pattern.compile("((/servico)|(/repositorioServico))/(?<idServico>.*)");

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

    public Optional<String> getPath() {
        try {
            return Optional.of(URI.create(url).getPath());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<String> getIdServico() {
        return getPath()
                .map(urlServicoPattern::matcher)
                .filter(Matcher::find)
                .map(m -> m.group("idServico"));
    }

    public Stream<PiwikPage> flattened() {
        return concat(
                of(this),
                stream(subPages).flatMap(PiwikPage::flattened)
        );
    }
}
