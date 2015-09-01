package br.gov.servicos.v3.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.stream.Stream;


@XmlType(name = "TipoDeCanalDePrestacao")
@XmlEnum
public enum TipoDeCanalDePrestacao {

    @XmlEnumValue("aplicativo-movel")
    APLICATIVO_MOVEL("aplicativo-movel"),

    @XmlEnumValue("e-mail")
    E_MAIL("e-mail"),

    @XmlEnumValue("fax")
    FAX("fax"),

    @XmlEnumValue("postal")
    POSTAL("postal"),

    @XmlEnumValue("presencial")
    PRESENCIAL("presencial"),

    @XmlEnumValue("sms")
    SMS("sms"),

    @XmlEnumValue("telefone")
    TELEFONE("telefone"),

    @XmlEnumValue("web")
    WEB("web"),

    @XmlEnumValue("web-acompanhar")
    WEB_ACOMPANHAR("web-acompanhar"),

    @XmlEnumValue("web-agendar")
    WEB_AGENDAR("web-agendar"),

    @XmlEnumValue("web-calcular-taxas")
    WEB_CALCULAR_TAXAS("web-calcular-taxas"),

    @XmlEnumValue("web-consultar")
    WEB_CONSULTAR("web-consultar"),

    @XmlEnumValue("web-declarar")
    WEB_DECLARAR("web-declarar"),

    @XmlEnumValue("web-emitir")
    WEB_EMITIR("web-emitir"),

    @XmlEnumValue("web-inscrever-se")
    WEB_INSCREVER_SE("web-inscrever-se"),

    @XmlEnumValue("web-postos-de-atendimento")
    WEB_POSTOS_DE_ATENDIMENTO("web-postos-de-atendimento"),

    @XmlEnumValue("web-preencher")
    WEB_PREENCHER("web-preencher"),

    @XmlEnumValue("web-simular")
    WEB_SIMULAR("web-simular");

    private final String value;

    TipoDeCanalDePrestacao(String v) {
        value = v;
    }

    public static TipoDeCanalDePrestacao from(String v) {
        return Stream.of(values())
                .filter(c -> c.value.equals(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(v));
    }

    public String value() {
        return value;
    }

}
