package br.gov.servicos.dominio;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {
}
