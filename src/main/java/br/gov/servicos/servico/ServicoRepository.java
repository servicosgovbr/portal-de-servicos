package br.gov.servicos.servico;

import br.gov.servicos.v3.schema.Servico;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {
}
