package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.unit.Fuzziness.TWO;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Component
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Buscador {

    private static final LinkedList<Servico> SEM_RESULTADOS = new LinkedList<>();

    ServicoRepository servicos;
    BuscaRepository buscas;

    @Autowired
    Buscador(ServicoRepository servicos, BuscaRepository buscas) {
        this.servicos = servicos;
        this.buscas = buscas;
    }

    List<Servico> busca(Optional<String> termoBuscado) {
        log.debug("Executando busca simples por '{}'", termoBuscado.orElse(""));
        return executaQuery(termoBuscado, (q) -> disMaxQuery()
                .add(queryString(q).boost(5f))
                .add(boolQuery()
                        .should(fuzzy(q, "titulo", 1.0f))
                        .should(fuzzy(q, "descricao", 0.9f))));
    }

    private FuzzyQueryBuilder fuzzy(String q, String field, float boost) {
        return fuzzyQuery(field, q)
                .boost(boost)
                .fuzziness(TWO)
                .prefixLength(0)
                .transpositions(true);
    }

    List<Servico> buscaPor(String campo, Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, termo -> termQuery(campo, termo));
    }

    public List<Servico> buscaSemelhante(Optional<String> termoBuscado, String... campos) {
        return executaQuery(termoBuscado, termo -> fuzzyLikeThisQuery(campos).likeText(termo));
    }

    private List<Servico> executaQuery(Optional<String> termoBuscado, Function<String, QueryBuilder> criaQuery) {
        Optional<String> termo = termoBuscado.filter(t -> !t.isEmpty());

        List<Servico> resultados = termo
                .map(criaQuery)
                .map(servicos::search)
                .map(Lists::newLinkedList)
                .orElse(SEM_RESULTADOS);

        registraBuscaEfetuada(termo, resultados);

        return unmodifiableList(resultados);
    }

    private void registraBuscaEfetuada(Optional<String> termo, List<Servico> resultados) {
        Busca busca = termo
                .map(buscas::findOne)
                .map(Busca::withNovaAtivacao)
                .orElseGet(() -> new Busca()
                        .withTermo(termo.orElse("[BUSCA VAZIA]"))
                        .withResultados(resultados.size())
                        .withAtivacoes(1));

        buscas.save(busca);
    }

}
