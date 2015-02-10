package br.gov.servicos.dominio;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BuscaRepository extends ElasticsearchRepository<Busca, String> {
}
