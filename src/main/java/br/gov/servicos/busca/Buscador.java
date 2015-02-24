package br.gov.servicos.busca;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.collect.Lists.newLinkedList;
import static org.elasticsearch.index.query.QueryBuilders.queryString;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class Buscador {

    private static final List<Servico> SEM_RESULTADOS = Collections.<Servico>emptyList();

    ServicoRepository servicos;
    BuscaRepository buscas;

    List<Servico> busca(Optional<String> termoBuscado) {
        return termoBuscado
                .map(termo -> executaBusca(termo, queryString(termo)))
                .orElse(SEM_RESULTADOS);
    }

    List<Servico> buscaPor(String area, Optional<String> termoBuscado) {
        return termoBuscado
                .map(termo -> executaBusca(termo, termQuery(area, termo)))
                .orElse(SEM_RESULTADOS);
    }

    private List<Servico> executaBusca(String termoBuscado, QueryBuilder query) {
        List<Servico> resultados = newLinkedList(servicos.search(query));
        registraBuscaEfetuada(termoBuscado, resultados);

        return resultados;
    }

    private void registraBuscaEfetuada(String termoBuscado, List<Servico> resultados) {
        Busca busca = ofNullable(buscas.findOne(termoBuscado))
                .map(Busca::withNovaAtivacao)
                .orElseGet(() -> new Busca(termoBuscado, resultados.size(), 1));

        buscas.save(busca);
    }

}
