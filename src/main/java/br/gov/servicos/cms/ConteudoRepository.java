package br.gov.servicos.cms;

import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ConteudoRepository extends ElasticsearchRepository<Conteudo, String> {

    default List<Conteudo> findByTipo(String tipoConteudo) {
        return search(new NativeSearchQueryBuilder()
                .withFilter(new TermFilterBuilder("tipoConteudo", tipoConteudo))
                .build())
                .getContent();
    }
}