package br.gov.servicos.busca;

import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.collect.Lists.newLinkedList;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class Buscador {

    private static final List<Servico> SEM_RESULTADOS = Collections.<Servico>emptyList();

    ServicoRepository servicos;
    BuscaRepository buscas;

    @Autowired
    Buscador(ServicoRepository servicos, BuscaRepository buscas) {
        this.servicos = servicos;
        this.buscas = buscas;
    }

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
    
    List<Servico> buscaSemelhante(Optional<String> termoBuscado, String... campos) {
        return termoBuscado
                .map(termo -> executaBusca(termo, fuzzyLikeThisQuery(campos).likeText(termo)))
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
