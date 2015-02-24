package br.gov.servicos.busca;

import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.index.query.QueryBuilders.fuzzyLikeThisQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class Buscador {

    private static final LinkedList<Servico> SEM_RESULTADOS = new LinkedList<>();

    ServicoRepository servicos;
    BuscaRepository buscas;

    @Autowired
    Buscador(ServicoRepository servicos, BuscaRepository buscas) {
        this.servicos = servicos;
        this.buscas = buscas;
    }

    List<Servico> busca(Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, QueryBuilders::queryString);
    }

    List<Servico> buscaPor(String area, Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, termo -> termQuery(area, termo));
    }

    List<Servico> buscaSemelhante(Optional<String> termoBuscado, String... campos) {
        return executaQuery(termoBuscado, termo -> fuzzyLikeThisQuery(campos).likeText(termo));
    }

    private List<Servico> executaQuery(Optional<String> termoBuscado, Function<String, QueryBuilder> criaQuery) {
        LinkedList<Servico> resultados = termoBuscado
                .map(criaQuery)
                .map(servicos::search)
                .map(Lists::newLinkedList)
                .orElse(SEM_RESULTADOS);

        registraBuscaEfetuada(termoBuscado, resultados);

        return resultados;
    }

    private void registraBuscaEfetuada(Optional<String> termoBuscado, LinkedList<Servico> resultados) {
        Busca busca = termoBuscado
                .map(buscas::findOne)
                .map(Busca::withNovaAtivacao)
                .orElseGet(() -> new Busca(termoBuscado.get(), resultados.size(), 1));

        buscas.save(busca);
    }

}
