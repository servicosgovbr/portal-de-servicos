package br.gov.servicos.servico.areaDeInteresse;

import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.AreaDeInteresse;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
class AreaDeInteresseRepository {

    ServicoRepository servicos;

    @Autowired
    AreaDeInteresseRepository(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    public List<AreaDeInteresse> findAll() {
        return Stream.of(AreaDeInteresse.values())
                .parallel()
                .filter(area -> !servicos.findByAreaDeInteresse(area).isEmpty())
                .collect(toList());
    }

}
