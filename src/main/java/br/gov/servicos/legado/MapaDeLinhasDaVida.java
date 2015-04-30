package br.gov.servicos.legado;

import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import com.github.slugify.Slugify;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;
import static org.springframework.util.StringUtils.isEmpty;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class MapaDeLinhasDaVida {

    private static final String CSV_LINHAS_DA_VIDA = "linhas-da-vida.csv";

    Slugify slugify;
    Map<String, List<LinhaDaVida>> linhas;

    @Autowired
    public MapaDeLinhasDaVida(Slugify slugify) throws IOException {
        this.slugify = slugify;
        this.linhas = unmarshallLinhasDaVida();
    }

    public List<LinhaDaVida> linhasDaVida(String tituloServico) {
        return ofNullable(linhas.get(slugify.slugify(tituloServico)))
                .orElse(emptyList());
    }

    private Map<String, List<LinhaDaVida>> unmarshallLinhasDaVida() throws IOException {
        ClassPathResource resource = new ClassPathResource(CSV_LINHAS_DA_VIDA);

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), "utf-8")) {
            try (CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
                return parser.getRecords()
                        .stream()
                        .collect(toMap(r -> slugify.slugify(r.get("servico")), this::linhasDaVidaDoCsvRecord));
            }
        }
    }

    private List<LinhaDaVida> linhasDaVidaDoCsvRecord(CSVRecord r) {
        return of(linhaDaVida(r.get("linha-1")), linhaDaVida(r.get("linha-2")))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private LinhaDaVida linhaDaVida(String tituloLinhaDaVida) {
        if (isEmpty(tituloLinhaDaVida)) {
            return null;
        }

        return new LinhaDaVida()
                .withId(slugify.slugify(tituloLinhaDaVida))
                .withTitulo(tituloLinhaDaVida);
    }

}
