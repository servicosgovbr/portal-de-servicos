package br.gov.servicos.servico.linhaDaVida;

import com.github.slugify.Slugify;
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
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LinhaDaVidaRepository {

    Slugify slugify;
    ElasticsearchTemplate et;

    @Autowired
    LinhaDaVidaRepository(ElasticsearchTemplate et, Slugify slugify) {
        this.et = et;
        this.slugify = slugify;
    }

    public List<LinhaDaVida> findAll() {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("linhasDaVida")
                                        .field("linhasDaVida.titulo")
                                        .size(MAX_VALUE)
                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("linhasDaVida"))
                        .getBuckets()
                        .stream()
                        .map(bucket -> new LinhaDaVida()
                                .withId(slugify.slugify(bucket.getKey()))
                                .withTitulo(bucket.getKey())
                                .withServicos(bucket.getDocCount()))
                        .sorted((left, right) -> left.getTitulo().compareTo(right.getTitulo()))
                        .collect(Collectors.toList())
        );
    }
}
