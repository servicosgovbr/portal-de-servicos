
package br.gov.servicos.v3.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CanaisDePrestacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CanaisDePrestacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="default" type="{http://servicos.gov.br/v3/schema}CasoDeCanaisDePrestacao"/>
 *         &lt;element name="caso" type="{http://servicos.gov.br/v3/schema}CasoDeCanaisDePrestacao" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CanaisDePrestacao", propOrder = {
    "_default",
    "caso"
})
public class CanaisDePrestacao {

    @XmlElement(name = "default", required = true)
    protected CasoDeCanaisDePrestacao _default;
    protected List<CasoDeCanaisDePrestacao> caso;

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link CasoDeCanaisDePrestacao }
     *     
     */
    public CasoDeCanaisDePrestacao getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link CasoDeCanaisDePrestacao }
     *     
     */
    public void setDefault(CasoDeCanaisDePrestacao value) {
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
     * {@link CasoDeCanaisDePrestacao }
     * 
     * 
     */
    public List<CasoDeCanaisDePrestacao> getCaso() {
        if (caso == null) {
            caso = new ArrayList<CasoDeCanaisDePrestacao>();
        }
        return this.caso;
    }

}
