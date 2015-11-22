package br.gov.servicos.cms;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaginaTematicaRepository extends ElasticsearchRepository<PaginaTematica, String> {
}