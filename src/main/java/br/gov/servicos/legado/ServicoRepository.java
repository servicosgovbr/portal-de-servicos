package br.gov.servicos.legado;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {
}
