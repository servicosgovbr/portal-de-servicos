package br.gov.servicos.cms;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ConteudoRepository extends ElasticsearchRepository<Conteudo, String> {

    default List<Conteudo> findByTipoConteudo(String tipoConteudo) {
        return findAll(new PageRequest(0, Integer.MAX_VALUE)).getContent()
                .stream()
                .filter(c -> c.getTipoConteudo().equals(tipoConteudo))
                .collect(toList());
    }
}