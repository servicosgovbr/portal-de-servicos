package br.gov.servicos.orgao;

import br.gov.servicos.v3.schema.OrgaoXML;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.LogstashMarker;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Optional.*;
import static lombok.AccessLevel.PRIVATE;
import static net.logstash.logback.marker.Markers.append;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Siorg {

    public static final Predicate<String> URL_PREDICATE = Pattern.compile("http://estruturaorganizacional\\.dados\\.gov\\.br/(doc|id)/unidade-organizacional/\\d+").asPredicate();

    RestTemplate restTemplate;
    CacheManager cacheManager;
    Slugify slugify;

    @Autowired
    public Siorg(RestTemplate restTemplate, CacheManager cacheManager, Slugify slugify) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
        this.slugify = slugify;
    }

    @Cacheable("orgaos-siorg")
    public Optional<OrgaoXML> obterOrgao(String url) {
        return findUnidade(url)
                .map(u -> new OrgaoXML()
                        .withId(slugify.slugify(url))
                        .withUrl(url)
                        .withNome(format("%s (%s)", u.getNome(), u.getSigla())));
    }

    @Cacheable("unidades-siorg")
    public Optional<Unidade> findUnidade(String url) {
        LogstashMarker marker = append("siorg.url", url);

        Cache cache = cacheManager.getCache("unidadesSiorg");
        Unidade cached = cache.get(url, Unidade.class);
        if (cached != null) {
            log.trace(marker, "Encontrado no cache do Siorg: {}", cached.getNome());
            return of(cached);
        }

        if (URL_PREDICATE.negate().test(url)) {
            log.warn(marker, "URL {} não é válida para o Siorg", url);
            return empty();
        }

        try {
            ResponseEntity<Orgao> entity = restTemplate.getForEntity(url, Orgao.class);
            Orgao body = entity.getBody();

            marker = marker.and(append("siorg.status", entity.getStatusCode()));

            if (body.getServico().getCodigoErro() > 0) {
                log.warn((Marker) marker.and(append("siorg.erro", body.getServico().getCodigoErro())),
                        "Erro ao acessar Siorg: {}", body.getServico().getMensagem());

                return empty();
            }

            log.debug(marker, "Consultado no Siorg com sucesso: {}", body.getUnidade().getNome());
            cache.put(url, body.getUnidade());
            return ofNullable(body.getUnidade());

        } catch (Exception e) {
            log.warn("Erro ao acessar Siorg", e);
            return empty();
        }
    }

    @Data
    @Wither
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Orgao {
        Servico servico;
        Unidade unidade;
    }

    @Data
    @Wither
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Servico {
        long codigoErro;
        String mensagem;
    }

    @Data
    @Wither
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Unidade {
        String codigoUnidade;
        String codigoUnidadePai;
        String codigoOrgaoEntidade;
        String codigoTipoUnidade;
        String nome;
        String sigla;
        String codigoEsfera;
        String codigoPoder;
        String codigoNaturezaJuridica;
        String codigoSubNaturezaJuridica;
    }
}
