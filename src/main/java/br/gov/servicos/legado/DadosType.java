//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.06 at 04:52:01 PM BRST 
//


package br.gov.servicos.legado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dadosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servicos" type="{}servicosType"/>
 *         &lt;element name="orgaos" type="{}orgaosType"/>
 *         &lt;element name="ouvidorias" type="{}ouvidoriasType"/>
 *         &lt;element name="portaisServico" type="{}portaisServicoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosType", propOrder = {
    "servicos",
    "orgaos",
    "ouvidorias",
    "portaisServico"
})
public class DadosType {

    @XmlElement(required = true)
    protected ServicosType servicos;
    @XmlElement(required = true)
    protected OrgaosType orgaos;
    @XmlElement(required = true)
    protected OuvidoriasType ouvidorias;
    @XmlElement(required = true)
    protected PortaisServicoType portaisServico;

    /**
     * Gets the value of the servicos property.
     * 
     * @return
     *     possible object is
     *     {@link ServicosType }
     *     
     */
    public ServicosType getServicos() {
        return servicos;
    }

    /**
     * Sets the value of the servicos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicosType }
     *     
     */
    public void setServicos(ServicosType value) {
        this.servicos = value;
    }

    /**
     * Gets the value of the orgaos property.
     * 
     * @return
     *     possible object is
     *     {@link OrgaosType }
     *     
     */
    public OrgaosType getOrgaos() {
        return orgaos;
    }

    /**
     * Sets the value of the orgaos property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgaosType }
     *     
     */
    public void setOrgaos(OrgaosType value) {
        this.orgaos = value;
    }

    /**
     * Gets the value of the ouvidorias property.
     * 
     * @return
     *     possible object is
     *     {@link OuvidoriasType }
     *     
     */
    public OuvidoriasType getOuvidorias() {
        return ouvidorias;
    }

    /**
     * Sets the value of the ouvidorias property.
     * 
     * @param value
     *     allowed object is
     *     {@link OuvidoriasType }
     *     
     */
    public void setOuvidorias(OuvidoriasType value) {
        this.ouvidorias = value;
    }

    /**
     * Gets the value of the portaisServico property.
     * 
     * @return
     *     possible object is
     *     {@link PortaisServicoType }
     *     
     */
    public PortaisServicoType getPortaisServico() {
        return portaisServico;
    }

    /**
     * Sets the value of the portaisServico property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortaisServicoType }
     *     
     */
    public void setPortaisServico(PortaisServicoType value) {
        this.portaisServico = value;
    }

}
