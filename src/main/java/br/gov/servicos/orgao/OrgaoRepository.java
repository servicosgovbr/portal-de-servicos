package br.gov.servicos.orgao;

import br.gov.servicos.v3.schema.OrgaoXML;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface OrgaoRepository extends ElasticsearchRepository<OrgaoXML, String> {
    Optional<OrgaoXML> findByUrl(String url);
}
