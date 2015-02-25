package br.gov.servicos.busca;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface BuscaRepository extends ElasticsearchRepository<Busca, String> {
}
