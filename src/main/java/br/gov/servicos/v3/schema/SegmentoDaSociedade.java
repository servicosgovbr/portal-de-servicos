package br.gov.servicos.v3.schema;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Stream;


@XmlType(name = "SegmentoDaSociedade")
@XmlEnum
public enum SegmentoDaSociedade {

    @XmlEnumValue("Cidad\u00e3os")
    CIDADAOS("Cidadãos"),

    @XmlEnumValue("Empresas")
    EMPRESAS("Empresas"),

    @XmlEnumValue("Órgãos e entidades públicas")
    ORGAOS_E_ENTIDADES_PUBLICAS("Órgãos e entidades públicas"),

    @XmlEnumValue("Demais segmentos (ONGs, organizações sociais, etc)")
    DEMAIS_SEGMENTOS("Demais segmentos (ONGs, organizações sociais, etc)");

    private final String id;
    private final String value;

    @SneakyThrows
    SegmentoDaSociedade(String v) {
        id = new Slugify().slugify(v);
        value = v;
    }

    public static SegmentoDaSociedade findById(String v) {
        return Stream.of(values())
                .filter(c -> c.getId().equals(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(v));
    }

    public String getValue() {
        return value;
    }

    @SneakyThrows
    public String getId() {
        return id;
    }

    @Component
    public static class Formatter implements org.springframework.format.Formatter<SegmentoDaSociedade> {

        @Override
        public SegmentoDaSociedade parse(String id, Locale locale) throws ParseException {
            try {
                return SegmentoDaSociedade.findById(id);
            } catch (IllegalArgumentException e) {
                throw new ConteudoNaoEncontrado(id);
            }
        }

        @Override
        public String print(SegmentoDaSociedade segmento, Locale locale) {
            return segmento.getId();
        }
    }

}
