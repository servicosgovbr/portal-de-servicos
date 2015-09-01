package br.gov.servicos.v3.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
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

    private final String value;

    SegmentoDaSociedade(String v) {
        value = v;
    }

    public static SegmentoDaSociedade from(String v) {
        return Stream.of(values())
                .filter(c -> c.value.equals(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(v));
    }

    public String value() {
        return value;
    }

}
