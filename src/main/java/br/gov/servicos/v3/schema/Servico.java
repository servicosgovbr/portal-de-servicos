package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.xml.bind.annotation.*;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static lombok.AccessLevel.PRIVATE;


/**
 * <p>Java class for Servico complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Servico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nome" type="{http://servicos.gov.br/v3/schema}NomeDoServico"/&gt;
 *         &lt;element name="sigla" type="{http://servicos.gov.br/v3/schema}Sigla" minOccurs="0"/&gt;
 *         &lt;element name="nomes-populares" type="{http://servicos.gov.br/v3/schema}NomesPopulares" minOccurs="0"/&gt;
 *         &lt;element name="descricao" type="{http://servicos.gov.br/v3/schema}DescricaoDoServico" minOccurs="0"/&gt;
 *         &lt;element name="gratuito" type="{http://servicos.gov.br/v3/schema}Gratuito" minOccurs="0"/&gt;
 *         &lt;element name="solicitantes" type="{http://servicos.gov.br/v3/schema}Solicitantes" minOccurs="0"/&gt;
 *         &lt;element name="tempo-total-estimado" type="{http://servicos.gov.br/v3/schema}TempoTotalEstimado" minOccurs="0"/&gt;
 *         &lt;element name="etapas" type="{http://servicos.gov.br/v3/schema}Etapas" minOccurs="0"/&gt;
 *         &lt;element name="orgao" type="{http://servicos.gov.br/v3/schema}Orgao" minOccurs="0"/&gt;
 *         &lt;element name="segmentos-da-sociedade" type="{http://servicos.gov.br/v3/schema}SegmentosDaSociedade" minOccurs="0"/&gt;
 *         &lt;element name="areas-de-interesse" type="{http://servicos.gov.br/v3/schema}AreasDeInteresse" minOccurs="0"/&gt;
 *         &lt;element name="palavras-chave" type="{http://servicos.gov.br/v3/schema}PalavrasChave" minOccurs="0"/&gt;
 *         &lt;element name="legislacoes" type="{http://servicos.gov.br/v3/schema}Legislacoes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
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
@Document(indexName = IMPORTADOR, type = "servico")
@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Servico {

    @Id
    @XmlTransient
    String id;

    @XmlElement(required = true)
    String nome;

    @XmlElement
    String sigla;

    @XmlElement(name = "nomes-populares")
    NomesPopulares nomesPopulares;

    @XmlElement
    String descricao;

    @XmlElement
    String gratuito;

    @XmlElement
    Solicitantes solicitantes;

    @XmlElement(name = "tempo-total-estimado")
    TempoTotalEstimado tempoTotalEstimado;

    @XmlElement
    Etapas etapas;

    @XmlElement
    Orgao orgao;

    @XmlElement(name = "segmentos-da-sociedade")
    SegmentosDaSociedade segmentosDaSociedade;

    @XmlElement(name = "areas-de-interesse")
    AreasDeInteresse areasDeInteresse;

    @XmlElement(name = "palavras-chave")
    PalavrasChave palavrasChave;

    @XmlElement
    Legislacoes legislacoes;

}
