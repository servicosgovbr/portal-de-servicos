package br.gov.servicos.busca;

import br.gov.servicos.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.facet.request.TermFacetRequestBuilder;
import org.springframework.data.elasticsearch.core.facet.result.Term;
import org.springframework.data.elasticsearch.core.facet.result.TermResult;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

import static java.lang.Integer.MAX_VALUE;

@Repository
public class BuscadorFacetado {

    ServicoRepository servicos;

    @Autowired
    public BuscadorFacetado(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    public Stream<String> servicosPor(String campo) {
        TermResult result = (TermResult) servicos.search(new NativeSearchQueryBuilder()
                .withFacet(new TermFacetRequestBuilder(campo)
                        .size(MAX_VALUE)
                        .fields(campo)
                        .build())
                .build())
                .getFacet(campo);

        return result.getTerms().stream().map(Term::getTerm);
    }
}
