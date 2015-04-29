package br.gov.servicos.config;

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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LinhasDaVidaDeServicosConfig {

    private static final String CSV_LINHAS_DA_VIDA = "linhas-da-vida.csv";

    Slugify slugify;
    Map<String, List<LinhaDaVida>> linhas;

    @Autowired
    public LinhasDaVidaDeServicosConfig(Slugify slugify) throws IOException {
        this.slugify = slugify;
        this.linhas = unmarshallLinhasDaVida();
    }

    public List<LinhaDaVida> linhasDaVida(String tituloServico) {
        String chave = slugify.slugify(tituloServico);
        List<LinhaDaVida> linhasDaVida = linhas.get(chave);

        return Optional.ofNullable(linhasDaVida).orElse(Collections.EMPTY_LIST);
    }

    private Map<String, List<LinhaDaVida>> unmarshallLinhasDaVida() throws IOException {
        try (CSVParser parser = CSVFormat.DEFAULT.withHeader()
                .parse(new InputStreamReader(new ClassPathResource(CSV_LINHAS_DA_VIDA).getInputStream(), "utf-8"))) {
            return parser.getRecords().stream()
                    .collect(toMap(r -> slugify.slugify(r.get("servico")), r -> linhasDaVidaDoCsvRecord(r)));
        }
    }

    private List<LinhaDaVida> linhasDaVidaDoCsvRecord(CSVRecord r) {
        return Stream.of(
                linhaDaVidaParaTitulo(r.get("linha-1")),
                linhaDaVidaParaTitulo(r.get("linha-2")))
                .filter(Objects::nonNull).collect(toList());
    }

    private LinhaDaVida linhaDaVidaParaTitulo(String tituloLinhaDaVida) {
        if (StringUtils.isEmpty(tituloLinhaDaVida))
            return null;

        return new LinhaDaVida()
                .withId(slugify.slugify(tituloLinhaDaVida))
                .withTitulo(tituloLinhaDaVida);
    }

}
