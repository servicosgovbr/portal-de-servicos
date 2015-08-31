package br.gov.servicos.v3.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;


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
@XmlAccessorType(XmlAccessType.FIELD)
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
        "id"
})
@Document(indexName = IMPORTADOR, type = "servico")
@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    protected String id;

    @XmlElement(required = true)
    protected String nome;

    protected String sigla;

    @XmlElement(name = "nomes-populares")
    protected NomesPopulares nomesPopulares;

    protected String descricao;

    protected String gratuito;

    protected Solicitantes solicitantes;

    @XmlElement(name = "tempo-total-estimado")
    protected TempoTotalEstimado tempoTotalEstimado;

    protected Etapas etapas;

    protected Orgao orgao;

    @XmlElement(name = "segmentos-da-sociedade")
    protected SegmentosDaSociedade segmentosDaSociedade;

    @XmlElement(name = "areas-de-interesse")
    protected AreasDeInteresse areasDeInteresse;

    @XmlElement(name = "palavras-chave")
    protected PalavrasChave palavrasChave;

    protected Legislacoes legislacoes;

}
