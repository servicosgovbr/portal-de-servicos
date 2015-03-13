package br.gov.servicos.metricas;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import static java.lang.String.*;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = "guia-de-servicos", type = "feedback")
@XStreamAlias("feedback")
@Wither
public class Feedback {

    @Id
    String id;

    private String url;
    private String queryString;
    private Long timestamp;
    private String tentandoFazer;
    private String aconteceu;

    public Feedback() {
        this(null, null, null, null, null, null);
    }
}
