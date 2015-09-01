
package br.gov.servicos.v3.schema;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;


/**
 * <p>Java class for Servico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Servico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://servicos.gov.br/v3/schema}NomeDoServico"/>
 *         &lt;element name="sigla" type="{http://servicos.gov.br/v3/schema}Sigla" minOccurs="0"/>
 *         &lt;element name="nomes-populares" type="{http://servicos.gov.br/v3/schema}NomesPopulares" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://servicos.gov.br/v3/schema}DescricaoDoServico" minOccurs="0"/>
 *         &lt;element name="gratuito" type="{http://servicos.gov.br/v3/schema}Gratuito" minOccurs="0"/>
 *         &lt;element name="solicitantes" type="{http://servicos.gov.br/v3/schema}Solicitantes" minOccurs="0"/>
 *         &lt;element name="tempo-total-estimado" type="{http://servicos.gov.br/v3/schema}TempoTotalEstimado" minOccurs="0"/>
 *         &lt;element name="etapas" type="{http://servicos.gov.br/v3/schema}Etapas" minOccurs="0"/>
 *         &lt;element name="orgao" type="{http://servicos.gov.br/v3/schema}Orgao" minOccurs="0"/>
 *         &lt;element name="segmentos-da-sociedade" type="{http://servicos.gov.br/v3/schema}SegmentosDaSociedade" minOccurs="0"/>
 *         &lt;element name="areas-de-interesse" type="{http://servicos.gov.br/v3/schema}AreasDeInteresse" minOccurs="0"/>
 *         &lt;element name="palavras-chave" type="{http://servicos.gov.br/v3/schema}PalavrasChave" minOccurs="0"/>
 *         &lt;element name="legislacoes" type="{http://servicos.gov.br/v3/schema}Legislacoes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
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
    "legislacoes"
})
@Document(indexName = IMPORTADOR+"-v3", type = "servico")
public class Servico {

    @Id
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

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the nomesPopulares property.
     * 
     * @return
     *     possible object is
     *     {@link NomesPopulares }
     *     
     */
    public NomesPopulares getNomesPopulares() {
        return nomesPopulares;
    }

    /**
     * Sets the value of the nomesPopulares property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomesPopulares }
     *     
     */
    public void setNomesPopulares(NomesPopulares value) {
        this.nomesPopulares = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Gets the value of the gratuito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGratuito() {
        return gratuito;
    }

    /**
     * Sets the value of the gratuito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGratuito(String value) {
        this.gratuito = value;
    }

    /**
     * Gets the value of the solicitantes property.
     * 
     * @return
     *     possible object is
     *     {@link Solicitantes }
     *     
     */
    public Solicitantes getSolicitantes() {
        return solicitantes;
    }

    /**
     * Sets the value of the solicitantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Solicitantes }
     *     
     */
    public void setSolicitantes(Solicitantes value) {
        this.solicitantes = value;
    }

    /**
     * Gets the value of the tempoTotalEstimado property.
     * 
     * @return
     *     possible object is
     *     {@link TempoTotalEstimado }
     *     
     */
    public TempoTotalEstimado getTempoTotalEstimado() {
        return tempoTotalEstimado;
    }

    /**
     * Sets the value of the tempoTotalEstimado property.
     * 
     * @param value
     *     allowed object is
     *     {@link TempoTotalEstimado }
     *     
     */
    public void setTempoTotalEstimado(TempoTotalEstimado value) {
        this.tempoTotalEstimado = value;
    }

    /**
     * Gets the value of the etapas property.
     * 
     * @return
     *     possible object is
     *     {@link Etapas }
     *     
     */
    public Etapas getEtapas() {
        return etapas;
    }

    /**
     * Sets the value of the etapas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Etapas }
     *     
     */
    public void setEtapas(Etapas value) {
        this.etapas = value;
    }

    /**
     * Gets the value of the orgao property.
     * 
     * @return
     *     possible object is
     *     {@link Orgao }
     *     
     */
    public Orgao getOrgao() {
        return orgao;
    }

    /**
     * Sets the value of the orgao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Orgao }
     *     
     */
    public void setOrgao(Orgao value) {
        this.orgao = value;
    }

    /**
     * Gets the value of the segmentosDaSociedade property.
     * 
     * @return
     *     possible object is
     *     {@link SegmentosDaSociedade }
     *     
     */
    public SegmentosDaSociedade getSegmentosDaSociedade() {
        return segmentosDaSociedade;
    }

    /**
     * Sets the value of the segmentosDaSociedade property.
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentosDaSociedade }
     *     
     */
    public void setSegmentosDaSociedade(SegmentosDaSociedade value) {
        this.segmentosDaSociedade = value;
    }

    /**
     * Gets the value of the areasDeInteresse property.
     * 
     * @return
     *     possible object is
     *     {@link AreasDeInteresse }
     *     
     */
    public AreasDeInteresse getAreasDeInteresse() {
        return areasDeInteresse;
    }

    /**
     * Sets the value of the areasDeInteresse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreasDeInteresse }
     *     
     */
    public void setAreasDeInteresse(AreasDeInteresse value) {
        this.areasDeInteresse = value;
    }

    /**
     * Gets the value of the palavrasChave property.
     * 
     * @return
     *     possible object is
     *     {@link PalavrasChave }
     *     
     */
    public PalavrasChave getPalavrasChave() {
        return palavrasChave;
    }

    /**
     * Sets the value of the palavrasChave property.
     * 
     * @param value
     *     allowed object is
     *     {@link PalavrasChave }
     *     
     */
    public void setPalavrasChave(PalavrasChave value) {
        this.palavrasChave = value;
    }

    /**
     * Gets the value of the legislacoes property.
     * 
     * @return
     *     possible object is
     *     {@link Legislacoes }
     *     
     */
    public Legislacoes getLegislacoes() {
        return legislacoes;
    }

    /**
     * Sets the value of the legislacoes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Legislacoes }
     *     
     */
    public void setLegislacoes(Legislacoes value) {
        this.legislacoes = value;
    }

}
