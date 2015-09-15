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
    APLICATIVO_MOVEL("Aplicativo móvel", false, ""),

    @XmlEnumValue("e-mail")
    E_MAIL("E-mail", false, ""),

    @XmlEnumValue("fax")
    FAX("Fax", false, ""),

    @XmlEnumValue("postal")
    POSTAL("Postal", false, ""),

    @XmlEnumValue("presencial")
    PRESENCIAL("Presencial", false, ""),

    @XmlEnumValue("sms")
    SMS("SMS", false, ""),

    @XmlEnumValue("telefone")
    TELEFONE("Telefone", false, ""),

    @XmlEnumValue("web")
    WEB("Web", true, "Acesse o site"),

    @XmlEnumValue("web-acompanhar")
    WEB_ACOMPANHAR("Web", true, "Acompanhe esta etapa do serviço"),

    @XmlEnumValue("web-agendar")
    WEB_AGENDAR("Web", true, "Agende esta etapa do serviço"),

    @XmlEnumValue("web-calcular-taxas")
    WEB_CALCULAR_TAXAS("Web", true, "Calcule as taxas desta etapa"),

    @XmlEnumValue("web-consultar")
    WEB_CONSULTAR("Web", true, "Realize uma consulta nesta etapa"),

    @XmlEnumValue("web-declarar")
    WEB_DECLARAR("Web", true, "Realize uma declaração nesta etapa"),

    @XmlEnumValue("web-emitir")
    WEB_EMITIR("Web", true, "Faça a emissão para esta etapa"),

    @XmlEnumValue("web-inscrever-se")
    WEB_INSCREVER_SE("Web", true, "Inscreva-se"),

    @XmlEnumValue("web-postos-de-atendimento")
    WEB_POSTOS_DE_ATENDIMENTO("Web", true, "Conheça os postos de atendimento"),

    @XmlEnumValue("web-preencher")
    WEB_PREENCHER("Web", true, "Preencha esta etapa do serviço"),

    @XmlEnumValue("web-simular")
    WEB_SIMULAR("Web", true, "Simule esta etapa do serviço");

    private final String value;
    private final boolean destacado;
    private final String descricaoLink;

    TipoDeCanalDePrestacao(String value, boolean destacado, String descricaoLink) {
        this.value = value;
        this.destacado = destacado;
        this.descricaoLink = descricaoLink;
    }

}
