package br.gov.servicos.servicos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BuscaRepository extends ElasticsearchRepository<Busca, String> {
}
