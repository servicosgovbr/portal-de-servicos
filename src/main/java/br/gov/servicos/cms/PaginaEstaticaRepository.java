package br.gov.servicos.cms;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaginaEstaticaRepository extends ElasticsearchRepository<PaginaEstatica, String> {
}