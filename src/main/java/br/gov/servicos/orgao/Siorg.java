package br.gov.servicos.orgao;

import br.gov.servicos.v3.schema.OrgaoXML;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Siorg {

    public static final Predicate<String> URL_PREDICATE = Pattern.compile("http://estruturaorganizacional\\.dados\\.gov\\.br/(doc|id)/unidade-organizacional/\\d+").asPredicate();

    RestTemplate restTemplate;
    Slugify slugify;

    @Autowired
    public Siorg(RestTemplate restTemplate, Slugify slugify) {
        this.restTemplate = restTemplate;
        this.slugify = slugify;
    }

    @Cacheable("orgaos-siorg")
    public OrgaoXML obterOrgao(String url) {
        Unidade u = findUnidade(url);
        return new OrgaoXML()
                .withId(slugify.slugify(u.codigoUnidade))
                .withUrl(u.codigoUnidade)
                .withNome(String.format("%s (%s)", u.getNome(), u.getSigla()));
    }

    @Cacheable("unidades-siorg")
    public Unidade findUnidade(String url) {
        if (URL_PREDICATE.negate().test(url)) {
            String msg = String.format("URL %s não é válida para o Siorg", url);
            throw new IllegalArgumentException(msg);
        }

        ResponseEntity<Orgao> entity = restTemplate.getForEntity(url, Orgao.class);
        Orgao body = entity.getBody();

        if (body.getServico().getCodigoErro() > 0) {
            String msg = String.format("Erro ao acessar Siorg: %s", body.getServico().getMensagem());
            throw new RuntimeException(msg);
        }

        return body.getUnidade();
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
