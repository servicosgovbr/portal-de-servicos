package br.gov.servicos.metricas;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static br.gov.servicos.config.GuiaDeServicosIndex.GUIA_DE_SERVICOS;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = GUIA_DE_SERVICOS, type = "feedback")
@XStreamAlias("feedback")
@Wither
public class Feedback {

    @Id
    String id;

    @Field(store = false, type = String, index = not_analyzed)
    String url;

    @Field(store = false, type = String, index = not_analyzed)
    String queryString;

    @Field(store = false, type = Long, index = not_analyzed)
    Long timestamp;

    @Field(store = true, type = String)
    String tentandoFazer;

    @Field(store = true, type = String)
    String aconteceu;

    @Field(store = false, type = String, index = not_analyzed)
    String ticket;

    public Feedback() {
        this(null, null, null, null, null, null, null);
    }
}
