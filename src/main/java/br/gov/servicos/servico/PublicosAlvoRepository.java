package br.gov.servicos.servico;

import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class PublicosAlvoRepository {

    private static final String PUBLICOS_ALVO = "publicosAlvo";
    private static final String TITULO = "publicosAlvo.titulo";

    Slugify slugify;
    ElasticsearchTemplate elasticsearch;

    @Autowired
    PublicosAlvoRepository(ElasticsearchTemplate elasticsearch, Slugify slugify) {
        this.elasticsearch = elasticsearch;
        this.slugify = slugify;
    }

    public List<PublicoAlvo> findAll() {
        return elasticsearch.query(publicosAlvoAgregadosPorTitulo(), extraiPublicosAlvo());
    }

    private NativeSearchQuery publicosAlvoAgregadosPorTitulo() {
        return new NativeSearchQueryBuilder().addAggregation(
                    new TermsBuilder(PUBLICOS_ALVO)
                            .field(TITULO)
                            .size(MAX_VALUE))
                    .build();
    }

    private ResultsExtractor<List<PublicoAlvo>> extraiPublicosAlvo() {
        return response -> ((Terms) response.getAggregations().get(PUBLICOS_ALVO))
                .getBuckets()
                .stream()
                .map(this::bucketToPublicoAlvo)
                .sorted((left, right) -> left.getTitulo().compareTo(right.getTitulo()))
                .collect(toList());
    }

    private PublicoAlvo bucketToPublicoAlvo(Terms.Bucket bucket) {
        return new PublicoAlvo()
                .withId(slugify.slugify(bucket.getKey()))
                .withTitulo(bucket.getKey());
    }

}
