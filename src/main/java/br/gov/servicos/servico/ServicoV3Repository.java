package br.gov.servicos.servico;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoV3Repository extends ElasticsearchRepository<br.gov.servicos.v3.schema.Servico, String> {
}
