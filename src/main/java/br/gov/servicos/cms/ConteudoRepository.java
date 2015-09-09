package br.gov.servicos.cms;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.Collectors.toList;

public interface ConteudoRepository extends ElasticsearchRepository<Conteudo, String> {

    default List<Conteudo> findByTipo(String tipoConteudo) {
        return findAll(new PageRequest(0, MAX_VALUE)).getContent()
                .stream()
                .filter(c -> c.getTipoConteudo().equals(tipoConteudo))
                .collect(toList());
    }
}