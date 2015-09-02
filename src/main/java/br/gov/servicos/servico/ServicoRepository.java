package br.gov.servicos.servico;

import br.gov.servicos.v3.schema.AreaDeInteresse;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.SegmentoDaSociedade;
import br.gov.servicos.v3.schema.Servico;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.Collectors.toList;

public interface ServicoRepository extends ElasticsearchRepository<Servico, String> {

    @Cacheable("servicos-por-orgao")
    default List<Servico> findByOrgao(Orgao orgao) {
        return findAll(new PageRequest(0, MAX_VALUE)).getContent()
                .stream()
                .filter(s -> s.getOrgao() != null && s.getOrgao().getId().equals(orgao.getId()))
                .collect(toList());
    }

    @Cacheable("servicos-por-area-de-interesse")
    default List<Servico> findByAreaDeInteresse(AreaDeInteresse areaDeInteresse) {
        return findAll(new PageRequest(0, MAX_VALUE)).getContent()
                .stream()
                .filter(s -> s.getAreasDeInteresse().contains(areaDeInteresse))
                .collect(toList());
    }

    @Cacheable("servicos-por-segmento-da-sociedade")
    default List<Servico> findBySegmentoDaSociedade(SegmentoDaSociedade segmentoDaSociedade) {
        return findAll(new PageRequest(0, MAX_VALUE)).getContent()
                .stream()
                .filter(s -> s.getSegmentosDaSociedade().contains(segmentoDaSociedade))
                .collect(toList());
    }
}
