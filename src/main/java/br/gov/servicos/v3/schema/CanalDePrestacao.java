
package br.gov.servicos.v3.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CanalDePrestacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CanalDePrestacao"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="descricao" type="{http://servicos.gov.br/v3/schema}DescricaoDeCanalDePrestacao"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="tipo" use="required" type="{http://servicos.gov.br/v3/schema}TipoDeCanalDePrestacao" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CanalDePrestacao", propOrder = {
    "descricao"
})
public class CanalDePrestacao {

    @XmlElement(required = true)
    protected List<String> descricao;
    @XmlAttribute(name = "tipo", required = true)
    protected TipoDeCanalDePrestacao tipo;

    /**
     * Gets the value of the descricao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descricao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescricao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDescricao() {
        if (descricao == null) {
            descricao = new ArrayList<String>();
        }
        return this.descricao;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDeCanalDePrestacao }
     *     
     */
    public TipoDeCanalDePrestacao getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDeCanalDePrestacao }
     *     
     */
    public void setTipo(TipoDeCanalDePrestacao value) {
        this.tipo = value;
    }

}
