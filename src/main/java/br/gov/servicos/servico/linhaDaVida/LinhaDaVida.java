package br.gov.servicos.servico.linhaDaVida;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static com.google.common.base.Strings.isNullOrEmpty;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Wither
public class LinhaDaVida {

    @Id
    @Field(type = String, index = not_analyzed)
    String id;

    @Field(type = String, index = not_analyzed)
    String titulo;

    Long servicos;

    public LinhaDaVida() {
        this(null, null, null);
    }

    public boolean isEmpty() {
        return isNullOrEmpty(titulo);
    }
}
