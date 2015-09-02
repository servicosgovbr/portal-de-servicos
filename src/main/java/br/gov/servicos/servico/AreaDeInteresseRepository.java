package br.gov.servicos.servico;

import br.gov.servicos.busca.BuscadorFacetado;
import br.gov.servicos.v3.schema.AreaDeInteresse;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
class AreaDeInteresseRepository {

    BuscadorFacetado buscador;

    @Autowired
    AreaDeInteresseRepository(BuscadorFacetado buscador) {
        this.buscador = buscador;
    }

    public List<AreaDeInteresse> findAll() {
        return buscador.servicosPor("areasDeInteresse")
                .map(AreaDeInteresse::valueOf)
                .sorted((l, r) -> l.getId().compareTo(r.getId()))
                .collect(toList());
    }

}
