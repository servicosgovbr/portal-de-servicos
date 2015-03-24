package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Wither
public class Orgao {

    @Id
    @Field(type = String, index = not_analyzed)
    String id;

    @Field(type = String)
    String nome;

    @Field(type = String)
    String telefone;

    public Orgao() {
        this(null, null, null);
    }
}
