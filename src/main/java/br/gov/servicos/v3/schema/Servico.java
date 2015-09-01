package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.xml.bind.annotation.*;
import java.util.List;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static javax.xml.bind.annotation.XmlAccessType.NONE;
import static lombok.AccessLevel.PRIVATE;

@Document(indexName = IMPORTADOR, type = "servico")
@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@XmlAccessorType(NONE)
@XmlType(name = "Servico", propOrder = {
        "nome",
        "sigla",
        "nomesPopulares",
        "descricao",
        "gratuito",
        "solicitantes",
        "tempoTotalEstimado",
        "etapas",
        "orgao",
        "segmentosDaSociedade",
        "areasDeInteresse",
        "palavrasChave",
        "legislacoes",
})
public class Servico {

    @Id
    @XmlTransient
    String id;

    @XmlElement(required = true)
    String nome;

    @XmlElement
    String sigla;

    @XmlElementWrapper(name = "nomes-populares")
    @XmlElement(name = "item")
    List<String> nomesPopulares;

    @XmlElement
    String descricao;

    @XmlElement
    String gratuito;

    @XmlElementWrapper(name = "solicitantes")
    @XmlElement(name = "solicitante")
    List<Solicitante> solicitantes;

    @XmlElement(name = "tempo-total-estimado")
    TempoTotalEstimado tempoTotalEstimado;

    @XmlElementWrapper(name = "etapas")
    @XmlElement(name = "etapa")
    List<Etapa> etapas;

    @XmlElement
    Orgao orgao;

    @XmlElementWrapper(name = "segmentos-da-sociedade")
    @XmlElement(name = "item")
    List<SegmentoDaSociedade> segmentosDaSociedade;

    @XmlElementWrapper(name = "areas-de-interesse")
    @XmlElement(name = "item")
    List<AreaDeInteresse> areasDeInteresse;

    @XmlElementWrapper(name = "palavras-chave")
    @XmlElement(name = "item")
    List<String> palavrasChave;

    @XmlElementWrapper(name = "legislacoes")
    @XmlElement(name = "item")
    List<String> legislacoes;

}
