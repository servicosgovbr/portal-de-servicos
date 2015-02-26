package br.gov.servicos.servico;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
public class AreaDeInteresse {

    @Id
    @Field(type = String, index = not_analyzed)
    String id;

    @Field(type = String)
    String titulo;

    public AreaDeInteresse() {
        this(null, null);
    }

    public AreaDeInteresse(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
