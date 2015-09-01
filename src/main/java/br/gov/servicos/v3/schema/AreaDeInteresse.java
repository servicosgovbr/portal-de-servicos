package br.gov.servicos.v3.schema;

import com.github.slugify.Slugify;
import lombok.SneakyThrows;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.stream.Stream;


@XmlType(name = "AreaDeInteresse")
@XmlEnum
public enum AreaDeInteresse {

    @XmlEnumValue("Abastecimento")
    ABASTECIMENTO("Abastecimento"),

    @XmlEnumValue("Administra\u00e7\u00e3o financeira")
    ADMINISTRAÇÃO_FINANCEIRA("Administra\u00e7\u00e3o financeira"),

    @XmlEnumValue("Agricultura org\u00e2nica")
    AGRICULTURA_ORGÂNICA("Agricultura org\u00e2nica"),

    @XmlEnumValue("Agropecu\u00e1ria")
    AGROPECUÁRIA("Agropecu\u00e1ria"),

    @XmlEnumValue("\u00c1guas")
    ÁGUAS("\u00c1guas"),

    @XmlEnumValue("Alimento")
    ALIMENTO("Alimento"),
    @XmlEnumValue("Ambiente e sa\u00fade")

    AMBIENTE_E_SAÚDE("Ambiente e sa\u00fade"),
    @XmlEnumValue("Comunica\u00e7\u00f5es")

    COMUNICAÇÕES("Comunica\u00e7\u00f5es"),
    @XmlEnumValue("Com\u00e9rcio e Servi\u00e7os")

    COMÉRCIO_E_SERVIÇOS("Com\u00e9rcio e Servi\u00e7os"),
    @XmlEnumValue("Economia e Finan\u00e7as")

    ECONOMIA_E_FINANÇAS("Economia e Finan\u00e7as"),
    @XmlEnumValue("Educa\u00e7\u00e3o")

    EDUCAÇÃO("Educa\u00e7\u00e3o"),
    @XmlEnumValue("Educa\u00e7\u00e3o b\u00e1sica")

    EDUCAÇÃO_BÁSICA("Educa\u00e7\u00e3o b\u00e1sica"),
    @XmlEnumValue("Educa\u00e7\u00e3o superior")

    EDUCAÇÃO_SUPERIOR("Educa\u00e7\u00e3o superior"),
    @XmlEnumValue("Educa\u00e7\u00e3o \u00e0 dist\u00e2ncia")

    EDUCAÇÃO_À_DISTÂNCIA("Educa\u00e7\u00e3o \u00e0 dist\u00e2ncia"),
    @XmlEnumValue("Emerg\u00eancias e Urg\u00eancias")

    EMERGÊNCIAS_E_URGÊNCIAS("Emerg\u00eancias e Urg\u00eancias"),
    @XmlEnumValue("Encargos financeiros")

    ENCARGOS_FINANCEIROS("Encargos financeiros"),
    @XmlEnumValue("Esporte e Lazer")

    ESPORTE_E_LAZER("Esporte e Lazer"),
    @XmlEnumValue("Finan\u00e7as")

    FINANÇAS("Finan\u00e7as"),
    @XmlEnumValue("Habita\u00e7\u00e3o")

    HABITAÇÃO("Habita\u00e7\u00e3o"),
    @XmlEnumValue("Humaniza\u00e7\u00e3o na sa\u00fade")

    HUMANIZAÇÃO_NA_SAÚDE("Humaniza\u00e7\u00e3o na sa\u00fade"),
    @XmlEnumValue("Ind\u00fastria")

    INDÚSTRIA("Ind\u00fastria"),
    @XmlEnumValue("Meio ambiente")

    MEIO_AMBIENTE("Meio ambiente"),
    @XmlEnumValue("Pecu\u00e1ria")

    PECUÁRIA("Pecu\u00e1ria"),
    @XmlEnumValue("Pessoa")

    PESSOA("Pessoa"),
    @XmlEnumValue("Previd\u00eancia Social")

    PREVIDÊNCIA_SOCIAL("Previd\u00eancia Social"),
    @XmlEnumValue("Profissionais da educa\u00e7\u00e3o")

    PROFISSIONAIS_DA_EDUCAÇÃO("Profissionais da educa\u00e7\u00e3o"),
    @XmlEnumValue("Prote\u00e7\u00e3o social")

    PROTEÇÃO_SOCIAL("Prote\u00e7\u00e3o social"),
    @XmlEnumValue("Qualidade ambiental")

    QUALIDADE_AMBIENTAL("Qualidade ambiental"),

    @XmlEnumValue("Rela\u00e7\u00f5es Internacionais")
    RELAÇÕES_INTERNACIONAIS("Rela\u00e7\u00f5es Internacionais"),

    @XmlEnumValue("Sa\u00fade")
    SAÚDE("Sa\u00fade"),

    @XmlEnumValue("Sa\u00fade da crian\u00e7a")
    SAÚDE_DA_CRIANÇA("Sa\u00fade da crian\u00e7a"),

    @XmlEnumValue("Sa\u00fade da fam\u00edlia")
    SAÚDE_DA_FAMÍLIA("Sa\u00fade da fam\u00edlia"),

    @XmlEnumValue("Sa\u00fade da mulher")
    SAÚDE_DA_MULHER("Sa\u00fade da mulher"),

    @XmlEnumValue("Sa\u00fade do homem")
    SAÚDE_DO_HOMEM("Sa\u00fade do homem"),

    @XmlEnumValue("Sa\u00fade do idoso")
    SAÚDE_DO_IDOSO("Sa\u00fade do idoso"),

    @XmlEnumValue("Sa\u00fade dos portadores de defici\u00eancias")
    SAÚDE_DOS_PORTADORES_DE_DEFICIÊNCIAS("Sa\u00fade dos portadores de defici\u00eancias"),

    @XmlEnumValue("Seguran\u00e7a e Ordem P\u00fablica")
    SEGURANÇA_E_ORDEM_PÚBLICA("Seguran\u00e7a e Ordem P\u00fablica"),

    @XmlEnumValue("Trabalho")
    TRABALHO("Trabalho"),

    @XmlEnumValue("Transportes")
    TRANSPORTES("Transportes"),

    @XmlEnumValue("Turismo")
    TURISMO("Turismo"),

    @XmlEnumValue("Urbanismo")
    URBANISMO("Urbanismo");

    private final String id;
    private final String value;

    @SneakyThrows
    AreaDeInteresse(String v) {
        id = new Slugify().slugify(v);
        value = v;
    }

    public static AreaDeInteresse findById(String v) {
        return Stream.of(values())
                .filter(c -> c.getId().equals(v))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(v));
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

}
