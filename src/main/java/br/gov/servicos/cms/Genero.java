package br.gov.servicos.cms;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Genero {

    private static final List<String> FEMININOS = Arrays.asList(
            "comissao",
            "defensoria",
            "empresa",
            "fundacao",
            "receita",
            "receital",
            "secretaria",
            "administracao"
    );

    public String o(String id) {
        return FEMININOS.stream().anyMatch(id::startsWith) ? "a" : "o";
    }

    public String de(String id) {
        return FEMININOS.stream().anyMatch(id::startsWith) ? "da" : "do";
    }

    public String lo(String id) {
        return FEMININOS.stream().anyMatch(id::startsWith) ? "la" : "lo";
    }

    public String los(String id) {
        return FEMININOS.stream().anyMatch(id::startsWith) ? "las" : "los";
    }

    public String per(String id) {
        return FEMININOS.stream().anyMatch(id::startsWith) ? "pela" : "pelo";
    }
}
