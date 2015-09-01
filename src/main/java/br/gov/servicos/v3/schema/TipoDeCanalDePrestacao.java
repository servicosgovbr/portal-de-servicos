package br.gov.servicos.v3.schema;

import lombok.Getter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@Getter
@XmlType(name = "TipoDeCanalDePrestacao")
@XmlEnum
public enum TipoDeCanalDePrestacao {

    @XmlEnumValue("aplicativo-movel")
    APLICATIVO_MOVEL("Aplicativo móvel", false),

    @XmlEnumValue("e-mail")
    E_MAIL("E-mail", false),

    @XmlEnumValue("fax")
    FAX("Fax", false),

    @XmlEnumValue("postal")
    POSTAL("Postal", false),

    @XmlEnumValue("presencial")
    PRESENCIAL("Presencial", false),

    @XmlEnumValue("sms")
    SMS("SMS", false),

    @XmlEnumValue("telefone")
    TELEFONE("Telefone", false),

    @XmlEnumValue("web")
    WEB("Acessar", true),

    @XmlEnumValue("web-acompanhar")
    WEB_ACOMPANHAR("Acompanhar", true),

    @XmlEnumValue("web-agendar")
    WEB_AGENDAR("Agendar", true),

    @XmlEnumValue("web-calcular-taxas")
    WEB_CALCULAR_TAXAS("Calcular taxas", true),

    @XmlEnumValue("web-consultar")
    WEB_CONSULTAR("Consultar", true),

    @XmlEnumValue("web-declarar")
    WEB_DECLARAR("Declarar", true),

    @XmlEnumValue("web-emitir")
    WEB_EMITIR("Emitir", true),

    @XmlEnumValue("web-inscrever-se")
    WEB_INSCREVER_SE("Inscrever-se", true),

    @XmlEnumValue("web-postos-de-atendimento")
    WEB_POSTOS_DE_ATENDIMENTO("Postos de atendimento", true),

    @XmlEnumValue("web-preencher")
    WEB_PREENCHER("Preencher", true),

    @XmlEnumValue("web-simular")
    WEB_SIMULAR("Simular", true);

    private final String value;
    private final boolean destacado;

    TipoDeCanalDePrestacao(String value, boolean destacado) {
        this.value = value;
        this.destacado = destacado;
    }

}
