package br.gov.servicos.dominio;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.MAX_VALUE;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LinhasDaVidaRepository {

    ElasticsearchTemplate et;

    @Autowired
    public LinhasDaVidaRepository(ElasticsearchTemplate et) {
        this.et = et;
    }

    public List<LinhaDaVida> findAll() {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("linhasDaVida")
                                        .field("linhasDaVida")
                                        .size(MAX_VALUE)
                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("linhasDaVida"))
                        .getBuckets()
                        .stream()
                        .map((bucket) -> new LinhaDaVida(bucket.getKey(), bucket.getDocCount()))
                        .collect(Collectors.toList())
        );
    }
}
