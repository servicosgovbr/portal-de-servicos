package br.gov.servicos.busca;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;

@Value
@Document(indexName = "guia-de-servicos", type = "busca")
@Wither
@AllArgsConstructor(access = PRIVATE)
public class Busca {

    @Id
    String termo;

    @Field(index = not_analyzed, type = Integer)
    Integer resultados;

    @Field(index = not_analyzed, type = Integer)
    Integer ativacoes;
    
    public Busca() {
        this(null, null, null);
    }

    public Busca withNovaAtivacao() {
        return withAtivacoes(ativacoes == null ? 1 : ativacoes + 1);
    }

}
