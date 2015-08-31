
package br.gov.servicos.v3.schema;

import com.github.slugify.Slugify;
import lombok.SneakyThrows;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AreaDeInteresse.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AreaDeInteresse"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Abastecimento"/&gt;
 *     &lt;enumeration value="Administração financeira"/&gt;
 *     &lt;enumeration value="Agricultura orgânica"/&gt;
 *     &lt;enumeration value="Agropecuária"/&gt;
 *     &lt;enumeration value="Águas"/&gt;
 *     &lt;enumeration value="Alimento"/&gt;
 *     &lt;enumeration value="Ambiente e saúde"/&gt;
 *     &lt;enumeration value="Comunicações"/&gt;
 *     &lt;enumeration value="Comércio e Serviços"/&gt;
 *     &lt;enumeration value="Economia e Finanças"/&gt;
 *     &lt;enumeration value="Educação"/&gt;
 *     &lt;enumeration value="Educação básica"/&gt;
 *     &lt;enumeration value="Educação superior"/&gt;
 *     &lt;enumeration value="Educação à distância"/&gt;
 *     &lt;enumeration value="Emergências e Urgências"/&gt;
 *     &lt;enumeration value="Encargos financeiros"/&gt;
 *     &lt;enumeration value="Esporte e Lazer"/&gt;
 *     &lt;enumeration value="Finanças"/&gt;
 *     &lt;enumeration value="Habitação"/&gt;
 *     &lt;enumeration value="Humanização na saúde"/&gt;
 *     &lt;enumeration value="Indústria"/&gt;
 *     &lt;enumeration value="Meio ambiente"/&gt;
 *     &lt;enumeration value="Pecuária"/&gt;
 *     &lt;enumeration value="Pessoa"/&gt;
 *     &lt;enumeration value="Previdência Social"/&gt;
 *     &lt;enumeration value="Profissionais da educação"/&gt;
 *     &lt;enumeration value="Proteção social"/&gt;
 *     &lt;enumeration value="Qualidade ambiental"/&gt;
 *     &lt;enumeration value="Relações Internacionais"/&gt;
 *     &lt;enumeration value="Saúde"/&gt;
 *     &lt;enumeration value="Saúde da criança"/&gt;
 *     &lt;enumeration value="Saúde da família"/&gt;
 *     &lt;enumeration value="Saúde da mulher"/&gt;
 *     &lt;enumeration value="Saúde do homem"/&gt;
 *     &lt;enumeration value="Saúde do idoso"/&gt;
 *     &lt;enumeration value="Saúde dos portadores de deficiências"/&gt;
 *     &lt;enumeration value="Segurança e Ordem Pública"/&gt;
 *     &lt;enumeration value="Trabalho"/&gt;
 *     &lt;enumeration value="Transportes"/&gt;
 *     &lt;enumeration value="Turismo"/&gt;
 *     &lt;enumeration value="Urbanismo"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
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
    private final String value;

    AreaDeInteresse(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AreaDeInteresse fromValue(String v) {
        for (AreaDeInteresse c: AreaDeInteresse.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @SneakyThrows
    public String getId() {
        return new Slugify().slugify(value);
    }

}
