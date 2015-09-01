package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.xml.bind.annotation.*;


@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Orgao")
public class Orgao {

    @XmlAttribute(name = "id", required = true)
    String id;

    @XmlTransient
    String nome;

}
