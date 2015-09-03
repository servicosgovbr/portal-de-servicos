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
    CIDADÃOS("Cidad\u00e3os"),

    @XmlEnumValue("Empresas")
    EMPRESAS("Empresas"),

    @XmlEnumValue("\u00d3rg\u00e3os e entidades p\u00fablicas")
    ÓRGÃOS_E_ENTIDADES_PÚBLICAS("\u00d3rg\u00e3os e entidades p\u00fablicas"),

    @XmlEnumValue("Demais segmentos (ONGs, organiza\u00e7\u00f5es sociais, etc)")
    DEMAIS_SEGMENTOS_ONGS_ORGANIZAÇÕES_SOCIAIS_ETC("Demais segmentos (ONGs, organiza\u00e7\u00f5es sociais, etc)");

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
