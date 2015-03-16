package br.gov.servicos.servico;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {

    Iterable<Servico> findByTituloStartsWithIgnoreCase(String a);

}
