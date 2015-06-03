package br.gov.servicos.piwik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Wither
@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PiwikPage {

    static final Pattern URL_SERVICO = Pattern.compile("((/servico)|(/repositorioServico))/(?<idServico>.*)");

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
            log.warn("Não foi possível criar URI a partir de {}", url, e);
            return Optional.empty();
        }
    }

    public Optional<String> getIdServico() {
        return getPath()
                .map(URL_SERVICO::matcher)
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
