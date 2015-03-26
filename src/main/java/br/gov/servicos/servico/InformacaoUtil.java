package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.elasticsearch.annotations.Field;

import static lombok.AccessLevel.PUBLIC;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PUBLIC)
@Wither
public class InformacaoUtil {

    @Field(type = String)
    String descricao;

    @Field(type = String, index = not_analyzed)
    String tipo;

    @Field(type = String, index = not_analyzed)
    String url;

    public InformacaoUtil() {
        this(null, null, null);
    }
}
