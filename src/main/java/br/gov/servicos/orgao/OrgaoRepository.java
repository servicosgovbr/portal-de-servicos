package br.gov.servicos.orgao;

import br.gov.servicos.v3.schema.OrgaoXML;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrgaoRepository extends ElasticsearchRepository<OrgaoXML, String> {
}
