package br.gov.servicos.servico.areaDeInteresse;

import br.gov.servicos.v3.schema.AreaDeInteresse;
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
import java.util.Optional;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AreaDeInteresseRepository {

    Slugify slugify;
    ElasticsearchTemplate et;

    @Autowired
    AreaDeInteresseRepository(ElasticsearchTemplate et, Slugify slugify) {
        this.et = et;
        this.slugify = slugify;
    }

    @Cacheable("areasDeInteresse")
    public List<AreaDeInteresse> findAll() {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("areasDeInteresse")
                                        .field("areasDeInteresse")
                                        .size(MAX_VALUE)
                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("areasDeInteresse"))
                        .getBuckets()
                        .stream()
                        .map(b -> AreaDeInteresse.valueOf(b.getKey()))
                        .sorted()
                        .collect(toList()));
    }

    public Optional<AreaDeInteresse> get(String id) {
        return findAll()
                .stream()
                .filter(a -> a.getValue().equals(id))
                .findFirst();
    }
}
