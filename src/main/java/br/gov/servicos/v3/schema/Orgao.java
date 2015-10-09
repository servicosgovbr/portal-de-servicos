package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.xml.bind.annotation.*;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;


@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Orgao")
public class Orgao {

    @XmlAttribute(name = "id", required = true)
    @Field(type = String, index = not_analyzed, store = true)
    String id;

    @XmlTransient
    @Field(type = String, index = not_analyzed, store = true)
    String url;

    @XmlTransient
    @Field(type = String, index = analyzed, store = true)
    String nome;

    @XmlElement(name = "contato")
    @Field(type = String, index = analyzed, store = true)
    String contato;

}
