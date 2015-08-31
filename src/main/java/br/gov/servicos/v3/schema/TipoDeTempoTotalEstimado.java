
package br.gov.servicos.v3.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoDeTempoTotalEstimado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoDeTempoTotalEstimado"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="minutos"/&gt;
 *     &lt;enumeration value="horas"/&gt;
 *     &lt;enumeration value="dias"/&gt;
 *     &lt;enumeration value="dias-corridos"/&gt;
 *     &lt;enumeration value="dias-uteis"/&gt;
 *     &lt;enumeration value="meses"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
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

    public String value() {
        return value;
    }

    public static TipoDeTempoTotalEstimado fromValue(String v) {
        for (TipoDeTempoTotalEstimado c: TipoDeTempoTotalEstimado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
