package br.gov.servicos.legado;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface ServicoRepository extends ElasticsearchRepository<Servico, String> {
}
