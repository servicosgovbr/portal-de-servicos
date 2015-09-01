
package br.gov.servicos.v3.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SegmentoDaSociedade.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SegmentoDaSociedade">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Cidadãos"/>
 *     &lt;enumeration value="Empresas"/>
 *     &lt;enumeration value="Órgãos e entidades públicas"/>
 *     &lt;enumeration value="Demais segmentos (ONGs, organizações sociais, etc)"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
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
    DEMAIS_SEGMENTOS_ON_GS_ORGANIZAÇÕES_SOCIAIS_ETC("Demais segmentos (ONGs, organiza\u00e7\u00f5es sociais, etc)");
    private final String value;

    SegmentoDaSociedade(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SegmentoDaSociedade fromValue(String v) {
        for (SegmentoDaSociedade c: SegmentoDaSociedade.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
