package br.gov.servicos.dominio;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Value
@Document(indexName = "guia-de-servicos", type = "busca")
public class Busca {

    @Id
    String termo;
    Long resultados;
    Long ativacoes;

    private Busca() {
        this(null, null, null);
    }

    public Busca(String termo, Long resultados, Long ativacoes) {
        this.termo = termo;
        this.resultados = resultados;
        this.ativacoes = ativacoes;
    }

    public Busca withNovaAtivacao() {
        return new Busca(termo, resultados, ativacoes == null ? 1 : ativacoes + 1);
    }

}
