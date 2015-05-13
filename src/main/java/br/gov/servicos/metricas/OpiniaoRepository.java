package br.gov.servicos.metricas;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface OpiniaoRepository extends ElasticsearchRepository<Opiniao, String> {
}
