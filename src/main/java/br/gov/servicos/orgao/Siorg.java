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

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Siorg {

    private static final String BASE_URL = "http://estruturaorganizacional.dados.gov.br/id/unidade-organizacional/";

    RestTemplate restTemplate;

    Slugify slugify;

    OrgaoUtils orgaoUtils;

    @Autowired
    public Siorg(RestTemplate restTemplate, Slugify slugify, OrgaoUtils orgaoUtils) {
        this.restTemplate = restTemplate;
        this.slugify = slugify;
        this.orgaoUtils = orgaoUtils;
    }

    @Cacheable("orgaos-siorg")
    public OrgaoXML obterOrgao(String unsafeUrl) {
        Unidade u = findUnidade(unsafeUrl);
        return new OrgaoXML()
                .withId(slugify.slugify(u.codigoUnidade))
                .withUrl(u.codigoUnidade)
                .withNome(String.format("%s (%s)", u.getNome(), u.getSigla()));
    }

    @Cacheable("unidades-siorg")
    public Unidade findUnidade(String unsafeId) {
        long orgaoId = orgaoUtils.obterId(unsafeId);

        String url = BASE_URL + orgaoId;

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
