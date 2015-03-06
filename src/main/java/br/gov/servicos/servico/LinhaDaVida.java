package br.gov.servicos.servico;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@XStreamAlias("linhaDaVida")
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

    public LinhaDaVida(String id, String titulo) {
        this(id, titulo, null);
    }

    public LinhaDaVida(String id, String titulo, Long servicos) {
        this.id = id;
        this.titulo = titulo;
        this.servicos = servicos;
    }
}
