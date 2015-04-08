package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.emptyList;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.unit.Fuzziness.TWO;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Component
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Buscador {

    private static final FacetedPageImpl<Servico> SEM_RESULTADOS = new FacetedPageImpl<>(emptyList());
    private static final int PAGE_SIZE = 20;

    ServicoRepository servicos;

    @Autowired
    Buscador(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    Page<Servico> busca(Optional<String> termoBuscado, Integer paginaAtual) {
        log.debug("Executando busca simples por '{}'", termoBuscado.orElse(""));
        return executaQuery(termoBuscado, paginaAtual, q -> disMaxQuery()
                .add(simpleQueryString(q))
                .add(boolQuery()
                        .should(fuzzy(q, "titulo", 1.0f))
                        .should(fuzzy(q, "descricao", 0.9f))));
    }

    public List<Servico> buscaPor(String campo, Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, termo -> termQuery(campo, termo));
    }

    public List<Servico> buscaSemelhante(Optional<String> termoBuscado, String... campos) {
        return executaQuery(termoBuscado, termo -> fuzzyLikeThisQuery(campos).likeText(termo));
    }

    private FuzzyQueryBuilder fuzzy(String q, String field, float boost) {
        return fuzzyQuery(field, q)
                .boost(boost)
                .fuzziness(TWO)
                .prefixLength(0)
                .transpositions(true);
    }

    private List<Servico> executaQuery(Optional<String> termoBuscado, Function<String, QueryBuilder> criaQuery) {
        return executaQuery(termoBuscado, 0, MAX_VALUE, criaQuery).getContent();
    }

    private Page<Servico> executaQuery(Optional<String> termoBuscado, Integer paginaAtual,
                                       Function<String, QueryBuilder> criaQuery) {

        return executaQuery(termoBuscado, paginaAtual, PAGE_SIZE, criaQuery);
    }

    private Page<Servico> executaQuery(Optional<String> termoBuscado, Integer paginaAtual,
                                       Integer quantidadeDeResultados, Function<String, QueryBuilder> criaQuery) {

        Optional<String> termo = termoBuscado.filter(t -> !t.isEmpty());

        return termo
                .map(criaQuery)
                .map(q -> servicos.search(q, new PageRequest(paginaAtual, quantidadeDeResultados)))
                .orElse(SEM_RESULTADOS);
    }

}
