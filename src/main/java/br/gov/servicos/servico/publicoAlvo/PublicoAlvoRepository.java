package br.gov.servicos.servico.publicoAlvo;

import br.gov.servicos.v3.schema.SegmentoDaSociedade;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class PublicoAlvoRepository {

    private static final String PUBLICOS_ALVO = "segmentosDaSociedade";
    private static final String TITULO = "segmentosDaSociedade.titulo";

    Slugify slugify;
    ElasticsearchTemplate elasticsearch;

    @Autowired
    PublicoAlvoRepository(ElasticsearchTemplate elasticsearch, Slugify slugify) {
        this.elasticsearch = elasticsearch;
        this.slugify = slugify;
    }

    @Cacheable("publicosAlvo")
    public List<SegmentoDaSociedade> findAll() {
        return elasticsearch.query(publicosAlvoAgregadosPorTitulo(), extraiPublicosAlvo());
    }

    private NativeSearchQuery publicosAlvoAgregadosPorTitulo() {
        return new NativeSearchQueryBuilder().addAggregation(
                new TermsBuilder(PUBLICOS_ALVO)
                        .field(TITULO)
                        .size(MAX_VALUE))
                .build();
    }

    private ResultsExtractor<List<SegmentoDaSociedade>> extraiPublicosAlvo() {
        return response -> ((Terms) response.getAggregations().get(PUBLICOS_ALVO))
                .getBuckets()
                .stream()
                .map((bucket) -> SegmentoDaSociedade.fromValue(bucket.getKey()))
                .sorted()
                .collect(toList());
    }

}
