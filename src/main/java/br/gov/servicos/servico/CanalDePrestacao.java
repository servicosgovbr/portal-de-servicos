package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.elasticsearch.annotations.Field;

import static com.google.common.base.Strings.isNullOrEmpty;
import static lombok.AccessLevel.PUBLIC;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Boolean;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PUBLIC)
@Wither
public class CanalDePrestacao {

    @Field(type = String, index = not_analyzed)
    String tipo;

    @Field(type = String)
    String titulo;

    @Field(type = String, index = not_analyzed)
    String referencia;

    @Field(type = Boolean, index = not_analyzed)
    Boolean preferencial;

    public CanalDePrestacao() {
        this(null, null, null, null);
    }

    public boolean isEmpty() {
        return isNullOrEmpty(referencia);
    }
}
