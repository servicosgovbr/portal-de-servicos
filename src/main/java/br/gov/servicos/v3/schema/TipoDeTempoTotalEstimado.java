package br.gov.servicos.v3.schema;

import lombok.Getter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


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
    DIAS_CORRIDOS("dias-corridos", "dias corridos"),

    @XmlEnumValue("dias-uteis")
    DIAS_UTEIS("dias-uteis", "dias úteis"),

    @XmlEnumValue("meses")
    MESES("meses");

    @Getter
    String id;

    @Getter
    String nome;

    TipoDeTempoTotalEstimado(String idOuNome) {
        this.id = idOuNome;
        this.nome = idOuNome;
    }

    TipoDeTempoTotalEstimado(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
