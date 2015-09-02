package br.gov.servicos.busca;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Component
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Buscador {

    private static final FacetedPageImpl<Servico> SEM_RESULTADOS = new FacetedPageImpl<>(emptyList());

    ServicoRepository servicos;

    @Autowired
    Buscador(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    public List<Conteudo> buscaConteudosPor(String campo, Optional<String> termoBuscado) {
        return paraConteudo(buscaServicosPor(campo, termoBuscado));
    }

    public List<Servico> buscaServicosPor(String campo, Optional<String> termoBuscado) {
        return executaQuery(termoBuscado, termo -> termQuery(campo, termo));
    }

    private List<Servico> executaQuery(Optional<String> termoBuscado, Function<String, QueryBuilder> criaQuery) {
        return executaQuery(termoBuscado, 0, MAX_VALUE, criaQuery).getContent();
    }

    private List<Conteudo> paraConteudo(List<Servico> buscados) {
        return buscados.stream()
                .map(Conteudo::fromServico)
                .collect(toList());
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
