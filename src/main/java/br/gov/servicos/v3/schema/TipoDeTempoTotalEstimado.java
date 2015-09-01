package br.gov.servicos.v3.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.stream.Stream;


@XmlType(name = "TipoDeTempoTotalEstimado")
@XmlEnum
public enum TipoDeTempoTotalEstimado {

    @XmlEnumValue("minutos")
    MINUTOS("minutos"),

    @XmlEnumValue("horas")
    HORAS("horas"),

    @XmlEnumValue("dias")
    DIAS("dias"),

    @XmlEnumValue("dias-corridos")
    DIAS_CORRIDOS("dias-corridos"),

    @XmlEnumValue("dias-uteis")
    DIAS_UTEIS("dias-uteis"),

    @XmlEnumValue("meses")
    MESES("meses");

    private final String value;

    TipoDeTempoTotalEstimado(String v) {
        value = v;
    }

    public static TipoDeTempoTotalEstimado from(String v) {
        return Stream.of(values())
                .filter(c -> c.value.equals(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(v));
    }

    public String value() {
        return value;
    }

}
