package br.gov.servicos.busca;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;

@Value
@Document(indexName = "guia-de-servicos", type = "busca")
public class Busca {

    @Id
    String termo;

    @Field(index = not_analyzed, type = Integer)
    Integer resultados;

    @Field(index = not_analyzed, type = Integer)
    Integer ativacoes;
    
    Busca() {
        this(null, null, null);
    }

    public Busca(String termo, Integer resultados, Integer ativacoes) {
        this.termo = termo;
        this.resultados = resultados;
        this.ativacoes = ativacoes;
    }

    public Busca withNovaAtivacao() {
        return new Busca(termo, resultados, ativacoes == null ? 1 : ativacoes + 1);
    }

}
