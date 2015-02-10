package br.gov.servicos.dominio;

import lombok.Value;

import static org.elasticsearch.common.Strings.isEmpty;

@Value
public class Orgao {

    String nome;
    String telefone;

    public Orgao() {
        this(null, null);
    }

    public Orgao(String nome, String telefone) {
        this.nome = isEmpty(nome) ? null : nome;
        this.telefone = isEmpty(telefone) ? null : telefone;
    }
}
