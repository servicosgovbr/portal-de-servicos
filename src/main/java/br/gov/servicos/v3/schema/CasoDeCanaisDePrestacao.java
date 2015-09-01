
package br.gov.servicos.v3.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CasoDeCanaisDePrestacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CasoDeCanaisDePrestacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="canal-de-prestacao" type="{http://servicos.gov.br/v3/schema}CanalDePrestacao"/>
 *       &lt;/sequence>
 *       &lt;attribute name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CasoDeCanaisDePrestacao", propOrder = {
    "canalDePrestacao"
})
public class CasoDeCanaisDePrestacao {

    @XmlElement(name = "canal-de-prestacao", required = true)
    protected List<CanalDePrestacao> canalDePrestacao;
    @XmlAttribute(name = "descricao")
    protected String descricao;

    /**
     * Gets the value of the canalDePrestacao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the canalDePrestacao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCanalDePrestacao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CanalDePrestacao }
     * 
     * 
     */
    public List<CanalDePrestacao> getCanalDePrestacao() {
        if (canalDePrestacao == null) {
            canalDePrestacao = new ArrayList<CanalDePrestacao>();
        }
        return this.canalDePrestacao;
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

}
