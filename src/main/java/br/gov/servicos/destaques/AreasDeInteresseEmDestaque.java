package br.gov.servicos.destaques;

import br.gov.servicos.config.AreasDeInteresseDestaqueConfig;
import br.gov.servicos.v3.schema.AreaDeInteresse;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AreasDeInteresseEmDestaque {
    AreasDeInteresseDestaqueConfig destaques;

    @Autowired
    public AreasDeInteresseEmDestaque(AreasDeInteresseDestaqueConfig destaques) {
        this.destaques = destaques;
    }

    @Cacheable("areas-de-interesse")
    public List<AreaDeInteresse> areasDeInteresse() {
        return destaques.getAreasDeInteresse()
                .stream()
                .map(AreaDeInteresse::findById)
                .collect(toList());
    }
}
