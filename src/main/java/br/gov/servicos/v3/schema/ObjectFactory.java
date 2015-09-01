
package br.gov.servicos.v3.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.gov.servicos.v3.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Servico_QNAME = new QName("http://servicos.gov.br/v3/schema", "servico");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.gov.servicos.v3.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Servico }
     * 
     */
    public Servico createServico() {
        return new Servico();
    }

    /**
     * Create an instance of {@link NomesPopulares }
     * 
     */
    public NomesPopulares createNomesPopulares() {
        return new NomesPopulares();
    }

    /**
     * Create an instance of {@link Solicitantes }
     * 
     */
    public Solicitantes createSolicitantes() {
        return new Solicitantes();
    }

    /**
     * Create an instance of {@link Solicitante }
     * 
     */
    public Solicitante createSolicitante() {
        return new Solicitante();
    }

    /**
     * Create an instance of {@link TempoTotalEstimado }
     * 
     */
    public TempoTotalEstimado createTempoTotalEstimado() {
        return new TempoTotalEstimado();
    }

    /**
     * Create an instance of {@link Entre }
     * 
     */
    public Entre createEntre() {
        return new Entre();
    }

    /**
     * Create an instance of {@link Ate }
     * 
     */
    public Ate createAte() {
        return new Ate();
    }

    /**
     * Create an instance of {@link Etapas }
     * 
     */
    public Etapas createEtapas() {
        return new Etapas();
    }

    /**
     * Create an instance of {@link Etapa }
     * 
     */
    public Etapa createEtapa() {
        return new Etapa();
    }

    /**
     * Create an instance of {@link Documentos }
     * 
     */
    public Documentos createDocumentos() {
        return new Documentos();
    }

    /**
     * Create an instance of {@link CasoDeDocumentos }
     * 
     */
    public CasoDeDocumentos createCasoDeDocumentos() {
        return new CasoDeDocumentos();
    }

    /**
     * Create an instance of {@link Custos }
     * 
     */
    public Custos createCustos() {
        return new Custos();
    }

    /**
     * Create an instance of {@link CasoDeCustos }
     * 
     */
    public CasoDeCustos createCasoDeCustos() {
        return new CasoDeCustos();
    }

    /**
     * Create an instance of {@link Custo }
     * 
     */
    public Custo createCusto() {
        return new Custo();
    }

    /**
     * Create an instance of {@link CanaisDePrestacao }
     * 
     */
    public CanaisDePrestacao createCanaisDePrestacao() {
        return new CanaisDePrestacao();
    }

    /**
     * Create an instance of {@link CasoDeCanaisDePrestacao }
     * 
     */
    public CasoDeCanaisDePrestacao createCasoDeCanaisDePrestacao() {
        return new CasoDeCanaisDePrestacao();
    }

    /**
     * Create an instance of {@link CanalDePrestacao }
     * 
     */
    public CanalDePrestacao createCanalDePrestacao() {
        return new CanalDePrestacao();
    }

    /**
     * Create an instance of {@link Orgao }
     * 
     */
    public Orgao createOrgao() {
        return new Orgao();
    }

    /**
     * Create an instance of {@link SegmentosDaSociedade }
     * 
     */
    public SegmentosDaSociedade createSegmentosDaSociedade() {
        return new SegmentosDaSociedade();
    }

    /**
     * Create an instance of {@link AreasDeInteresse }
     * 
     */
    public AreasDeInteresse createAreasDeInteresse() {
        return new AreasDeInteresse();
    }

    /**
     * Create an instance of {@link PalavrasChave }
     * 
     */
    public PalavrasChave createPalavrasChave() {
        return new PalavrasChave();
    }

    /**
     * Create an instance of {@link Legislacoes }
     * 
     */
    public Legislacoes createLegislacoes() {
        return new Legislacoes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Servico }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.gov.br/v3/schema", name = "servico")
    public JAXBElement<Servico> createServico(Servico value) {
        return new JAXBElement<Servico>(_Servico_QNAME, Servico.class, null, value);
    }

}
