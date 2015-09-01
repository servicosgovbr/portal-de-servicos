package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import static javax.xml.bind.annotation.XmlAccessType.NONE;
import static lombok.AccessLevel.PRIVATE;


@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@XmlAccessorType(NONE)
@XmlType(name = "TempoTotalEstimado", propOrder = {
        "entre",
        "ate",
        "descricao"
})
public class TempoTotalEstimado {

    @XmlElement
    Entre entre;

    @XmlElement
    Ate ate;

    @XmlElement(required = true)
    String descricao;

}
