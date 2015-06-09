package br.gov.servicos.metricas;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static br.gov.servicos.config.PortalDeServicosIndex.PERSISTENTE;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Boolean;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = PERSISTENTE, type = "opiniao")
@Wither
public class Opiniao {

    @Id
    String id;

    @Field(store = false, type = String, index = not_analyzed)
    String url;

    @Field(store = false, type = String, index = not_analyzed)
    String queryString;

    @Field(store = false, type = Long, index = not_analyzed)
    Long timestamp;

    @Field(store = false, type = String, index = not_analyzed)
    String ticket;

    @Field(store = true, type = Boolean)
    Boolean conteudoEncontrado;

    @Field(store = true, type = String)
    String mensagem;

    public Opiniao() {
        this(null, null, null, null, null, null, null);
    }
}
