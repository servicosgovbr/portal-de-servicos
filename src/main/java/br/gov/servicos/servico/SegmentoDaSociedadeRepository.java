package br.gov.servicos.servico;

import br.gov.servicos.busca.BuscadorFacetado;
import br.gov.servicos.v3.schema.SegmentoDaSociedade;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
class SegmentoDaSociedadeRepository {

    BuscadorFacetado buscador;

    @Autowired
    SegmentoDaSociedadeRepository(BuscadorFacetado buscador) {
        this.buscador = buscador;
    }

    public List<SegmentoDaSociedade> findAll() {
        return buscador.servicosPor("segmentosDaSociedade")
                .map(SegmentoDaSociedade::valueOf)
                .sorted((l, r) -> l.getId().compareTo(r.getId()))
                .collect(toList());
    }

}
