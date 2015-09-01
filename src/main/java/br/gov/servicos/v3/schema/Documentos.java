
package br.gov.servicos.v3.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Documentos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Documentos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="default" type="{http://servicos.gov.br/v3/schema}CasoDeDocumentos"/&gt;
 *         &lt;element name="caso" type="{http://servicos.gov.br/v3/schema}CasoDeDocumentos" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documentos", propOrder = {
    "_default",
    "caso"
})
public class Documentos {

    @XmlElement(name = "default", required = true)
    protected CasoDeDocumentos _default;
    protected List<CasoDeDocumentos> caso;

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link CasoDeDocumentos }
     *     
     */
    public CasoDeDocumentos getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link CasoDeDocumentos }
     *     
     */
    public void setDefault(CasoDeDocumentos value) {
        this._default = value;
    }

    /**
     * Gets the value of the caso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the caso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCaso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CasoDeDocumentos }
     * 
     * 
     */
    public List<CasoDeDocumentos> getCaso() {
        if (caso == null) {
            caso = new ArrayList<CasoDeDocumentos>();
        }
        return this.caso;
    }

}
