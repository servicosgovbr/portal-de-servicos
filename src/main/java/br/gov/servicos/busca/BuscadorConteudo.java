package br.gov.servicos.busca;

import br.gov.servicos.cms.Conteudo;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHitField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.unit.Fuzziness.TWO;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Component
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Cacheable("buscas")
public class BuscadorConteudo {

    private static final FacetedPageImpl<Conteudo> SEM_RESULTADOS = new FacetedPageImpl<>(emptyList());
    private static final int PAGE_SIZE = 20;

    ElasticsearchTemplate et;
    Slugify slugify;

    @Autowired
    BuscadorConteudo(ElasticsearchTemplate et, Slugify slugify) {
        this.et = et;
        this.slugify = slugify;
    }

    public Page<Conteudo> busca(Optional<String> termoBuscado, Integer paginaAtual) {
        log.debug("Executando busca simples por '{}'", termoBuscado.orElse(""));
        return executaQuery(termoBuscado, paginaAtual, q -> disMaxQuery()
                .add(multiMatchQuery(q, "titulo^1.0", "conteudo^0.9", "descricao^0.9")
                        .fuzziness(TWO)
                        .prefixLength(0)));
    }

    public List<Conteudo> buscaSemelhante(Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, termo -> fuzzyLikeThisQuery("nome", "conteudo", "descricao").likeText(termo));
    }

    private List<Conteudo> executaQuery(Optional<String> termoBuscado, Function<String, QueryBuilder> criaQuery) {
        return executaQuery(termoBuscado, 0, MAX_VALUE, criaQuery).getContent();
    }

    private Page<Conteudo> executaQuery(Optional<String> termoBuscado, Integer paginaAtual, Function<String, QueryBuilder> criaQuery) {
        return executaQuery(termoBuscado, paginaAtual, PAGE_SIZE, criaQuery);
    }

    private Page<Conteudo> executaQuery(Optional<String> termoBuscado, Integer paginaAtual,
                                        Integer quantidadeDeResultados, Function<String, QueryBuilder> criaQuery) {
        Optional<String> termo = termoBuscado.filter(t -> !t.isEmpty());
        PageRequest pageable = new PageRequest(paginaAtual, quantidadeDeResultados);

        return termo.map(criaQuery)
                .map(q -> et.query(
                        new NativeSearchQueryBuilder()
                                .withIndices(IMPORTADOR)
                                .withTypes("conteudo", "servico")
                                .withFields("tipoConteudo", "nome", "conteudo", "descricao")
                                .withPageable(pageable)
                                .withQuery(q)
                                .build(),
                        r -> new FacetedPageImpl<>(Stream.of(r.getHits().getHits())
                                .map(h -> {
                                    String nome = (h.field("titulo") == null ? h.field("nome") : h.field("titulo")).value();
                                    return new Conteudo()
                                            .withId(slugify.slugify(nome))
                                            .withTipoConteudo((String) Optional.ofNullable(h.field("tipoConteudo"))
                                                    .filter(Objects::nonNull)
                                                    .map(SearchHitField::value)
                                                    .orElse("servico"))
                                            .withNome(nome)
                                            .withConteudo(Optional.ofNullable(h.field("descricao"))
                                                    .orElse(h.field("conteudo"))
                                                    .value());
                                })
                                .collect(toList()), pageable, r.getHits().totalHits())))
                .orElse(SEM_RESULTADOS);
    }

}