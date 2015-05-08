package br.gov.servicos.cms;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ConteudoRepository extends ElasticsearchRepository<Conteudo, String> {
}
