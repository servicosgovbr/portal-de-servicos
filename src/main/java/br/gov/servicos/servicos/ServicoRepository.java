package br.gov.servicos.servicos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {
}
