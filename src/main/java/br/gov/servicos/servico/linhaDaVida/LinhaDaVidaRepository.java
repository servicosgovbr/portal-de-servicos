package br.gov.servicos.servico.linhaDaVida;

import br.gov.servicos.servico.areaDeInteresse.AreaDeInteresse;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.Collectors.toList;
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

    @Cacheable("linhasDaVida")
    public List<LinhaDaVida> findAll() {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("eventosDaLinhaDaVida")
                                        .field("eventosDaLinhaDaVida.titulo")
                                        .size(MAX_VALUE)
                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("eventosDaLinhaDaVida"))
                        .getBuckets()
                        .stream()
                        .map(bucket -> new LinhaDaVida()
                                .withId(slugify.slugify(bucket.getKey()))
                                .withTitulo(bucket.getKey())
                                .withServicos(bucket.getDocCount()))
                        .sorted((left, right) -> left.getTitulo().compareTo(right.getTitulo()))
                        .collect(toList())
        );
    }

    public List<AreaDeInteresse> findAllAreasDeInteresse() {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("areasDeInteresse")
                                        .field("areasDeInteresse.area")
                                        .size(MAX_VALUE)
                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("areasDeInteresse"))
                        .getBuckets()
                        .stream()
                        .map(bucket -> new AreaDeInteresse()
                                .withId(slugify.slugify(bucket.getKey()))
                                .withArea(bucket.getKey()))
                        .sorted((left, right) -> left.getArea().compareTo(right.getArea()))
                        .collect(toList()));
    }
}
