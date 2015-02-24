package br.gov.servicos.dominio;

import br.gov.servicos.busca.Busca;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BuscaRepository extends ElasticsearchRepository<Busca, String> {
}
