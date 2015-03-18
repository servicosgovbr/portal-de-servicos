package br.gov.servicos.servico.publicoAlvo;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
public class PublicoAlvo {

    @Id
    @Field(type = String, index = not_analyzed)
    String id;

    @Field(type = String, index = not_analyzed)
    String titulo;

    public PublicoAlvo() {
        this(null, null);
    }

}
