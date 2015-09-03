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
    VCGE2_ABASTECIMENTO("Abastecimento"),

    @XmlEnumValue("Administração")
    VCGE2_ADMINISTRACAO("Administração"),

    @XmlEnumValue("Agropecuária")
    VCGE2_AGROPECUARIA("Agropecuária"),

    @XmlEnumValue("Assistência Hospitalar e Ambulatorial")
    VCGE2_ASSISTENCIA_HOSPITALAR_E_AMBULATORIAL("Assistência Hospitalar e Ambulatorial"),

    @XmlEnumValue("Assistência ao Idoso")
    VCGE2_ASSISTENCIA_AO_IDOSO("Assistência ao Idoso"),

    @XmlEnumValue("Assistência ao Portador de Deficiência")
    VCGE2_ASSISTENCIA_AO_PORTADOR_DE_DEFICIENCIA("Assistência ao Portador de Deficiência"),

    @XmlEnumValue("Assistência à Criança e ao Adolescente")
    VCGE2_ASSISTENCIA_A_CRIANCA_E_AO_ADOLESCENTE("Assistência à Criança e ao Adolescente"),

    @XmlEnumValue("Atendimento básico")
    VCGE2_ATENDIMENTO_BASICO("Atendimento básico"),

    @XmlEnumValue("Biodiversidade")
    VCGE2_BIODIVERSIDADE("Biodiversidade"),

    @XmlEnumValue("Cidadania")
    VCGE2_CIDADANIA("Cidadania"),

    @XmlEnumValue("Clima")
    VCGE2_CLIMA("Clima"),

    @XmlEnumValue("Combate a desigualdade")
    VCGE2_COMBATE_A_DESIGUALDADE("Combate a desigualdade"),

    @XmlEnumValue("Combate a epidemias")
    VCGE2_COMBATE_A_EPIDEMIAS("Combate a epidemias"),

    @XmlEnumValue("Combustíveis")
    VCGE2_COMBUSTIVEIS("Combustíveis"),

    @XmlEnumValue("Comercio externo")
    VCGE2_COMERCIO_EXTERNO("Comercio externo"),

    @XmlEnumValue("Compras governamentais")
    VCGE2_COMPRAS_GOVERNAMENTAIS("Compras governamentais"),

    @XmlEnumValue("Comunicações")
    VCGE2_COMUNICACOES("Comunicações"),

    @XmlEnumValue("Comunicações Postais")
    VCGE2_COMUNICACOES_POSTAIS("Comunicações Postais"),

    @XmlEnumValue("Comércio e Serviços")
    VCGE2_COMERCIO_E_SERVICOS("Comércio e Serviços"),

    @XmlEnumValue("Cooperação Internacional")
    VCGE2_COOPERACAO_INTERNACIONAL("Cooperação Internacional"),

    @XmlEnumValue("Cultura")
    VCGE2_CULTURA("Cultura"),

    @XmlEnumValue("Defesa Civil")
    VCGE2_DEFESA_CIVIL("Defesa Civil"),

    @XmlEnumValue("Defesa Nacional")
    VCGE2_DEFESA_NACIONAL("Defesa Nacional"),

    @XmlEnumValue("Defesa agropecuária")
    VCGE2_DEFESA_AGROPECUARIA("Defesa agropecuária"),

    @XmlEnumValue("Defesa do Consumidor")
    VCGE2_DEFESA_DO_CONSUMIDOR("Defesa do Consumidor"),

    @XmlEnumValue("Defesa militar")
    VCGE2_DEFESA_MILITAR("Defesa militar"),

    @XmlEnumValue("Difusão")
    VCGE2_DIFUSAO("Difusão"),

    @XmlEnumValue("Difusão Cultural")
    VCGE2_DIFUSAO_CULTURAL("Difusão Cultural"),

    @XmlEnumValue("Economia e Finanças")
    VCGE2_ECONOMIA_E_FINANCAS("Economia e Finanças"),

    @XmlEnumValue("Educação")
    VCGE2_EDUCACAO("Educação"),

    @XmlEnumValue("Educação básica")
    VCGE2_EDUCACAO_BASICA("Educação básica"),

    @XmlEnumValue("Educação profissionalizante")
    VCGE2_EDUCACAO_PROFISSIONALIZANTE("Educação profissionalizante"),

    @XmlEnumValue("Educação superior")
    VCGE2_EDUCACAO_SUPERIOR("Educação superior"),

    @XmlEnumValue("Empregabilidade")
    VCGE2_EMPREGABILIDADE("Empregabilidade"),

    @XmlEnumValue("Energia")
    VCGE2_ENERGIA("Energia"),

    @XmlEnumValue("Energia elétrica")
    VCGE2_ENERGIA_ELETRICA("Energia elétrica"),

    @XmlEnumValue("Esporte comunitário")
    VCGE2_ESPORTE_COMUNITARIO("Esporte comunitário"),

    @XmlEnumValue("Esporte e Lazer")
    VCGE2_ESPORTE_E_LAZER("Esporte e Lazer"),

    @XmlEnumValue("Esporte profissional")
    VCGE2_ESPORTE_PROFISSIONAL("Esporte profissional"),

    @XmlEnumValue("Fiscalização do Estado")
    VCGE2_FISCALIZACAO_DO_ESTADO("Fiscalização do Estado"),

    @XmlEnumValue("Fomento ao Trabalho")
    VCGE2_FOMENTO_AO_TRABALHO("Fomento ao Trabalho"),

    @XmlEnumValue("Habitação")
    VCGE2_HABITACAO("Habitação"),

    @XmlEnumValue("Habitação Rural")
    VCGE2_HABITACAO_RURAL("Habitação Rural"),

    @XmlEnumValue("Habitação Urbana")
    VCGE2_HABITACAO_URBANA("Habitação Urbana"),

    @XmlEnumValue("Indústria")
    VCGE2_INDUSTRIA("Indústria"),

    @XmlEnumValue("Infraestrutura Urbana")
    VCGE2_INFRAESTRUTURA_URBANA("Infraestrutura Urbana"),

    @XmlEnumValue("Lazer")
    VCGE2_LAZER("Lazer"),

    @XmlEnumValue("Medicamentos e aparelhos")
    VCGE2_MEDICAMENTOS_E_APARELHOS("Medicamentos e aparelhos"),

    @XmlEnumValue("Meio ambiente")
    VCGE2_MEIO_AMBIENTE("Meio ambiente"),

    @XmlEnumValue("Mineração")
    VCGE2_MINERACAO("Mineração"),

    @XmlEnumValue("Normalização e Qualidade")
    VCGE2_NORMALIZACAO_E_QUALIDADE("Normalização e Qualidade"),

    @XmlEnumValue("Operações de dívida pública")
    VCGE2_OPERACOES_DE_DIVIDA_PUBLICA("Operações de dívida pública"),

    @XmlEnumValue("Orçamento")
    VCGE2_ORCAMENTO("Orçamento"),

    @XmlEnumValue("Patrimônio")
    VCGE2_PATRIMONIO("Patrimônio"),

    @XmlEnumValue("Patrimônio Cultural")
    VCGE2_PATRIMONIO_CULTURAL("Patrimônio Cultural"),

    @XmlEnumValue("Pesquisa e Desenvolvimento")
    VCGE2_PESQUISA_E_DESENVOLVIMENTO("Pesquisa e Desenvolvimento"),

    @XmlEnumValue("Planejamento")
    VCGE2_PLANEJAMENTO("Planejamento"),

    @XmlEnumValue("Policiamento")
    VCGE2_POLICIAMENTO("Policiamento"),

    @XmlEnumValue("Politica econômica")
    VCGE2_POLITICA_ECONOMICA("Politica econômica"),

    @XmlEnumValue("Preservação e Conservação Ambiental")
    VCGE2_PRESERVACAO_E_CONSERVACAO_AMBIENTAL("Preservação e Conservação Ambiental"),

    @XmlEnumValue("Previdência Básica")
    VCGE2_PREVIDENCIA_BASICA("Previdência Básica"),

    @XmlEnumValue("Previdência Complementar")
    VCGE2_PREVIDENCIA_COMPLEMENTAR("Previdência Complementar"),

    @XmlEnumValue("Previdência Social")
    VCGE2_PREVIDENCIA_SOCIAL("Previdência Social"),

    @XmlEnumValue("Produção Industrial")
    VCGE2_PRODUCAO_INDUSTRIAL("Produção Industrial"),

    @XmlEnumValue("Produção agropecuária")
    VCGE2_PRODUCAO_AGROPECUARIA("Produção agropecuária"),

    @XmlEnumValue("Propriedade Industrial")
    VCGE2_PROPRIEDADE_INDUSTRIAL("Propriedade Industrial"),

    @XmlEnumValue("Proteção Social")
    VCGE2_PROTECAO_SOCIAL("Proteção Social"),

    @XmlEnumValue("Proteção e Benefícios ao Trabalhador")
    VCGE2_PROTECAO_E_BENEFICIOS_AO_TRABALHADOR("Proteção e Benefícios ao Trabalhador"),

    @XmlEnumValue("Recursos humanos")
    VCGE2_RECURSOS_HUMANOS("Recursos humanos"),

    @XmlEnumValue("Relações Diplomáticas")
    VCGE2_RELACOES_DIPLOMATICAS("Relações Diplomáticas"),

    @XmlEnumValue("Relações Internacionais")
    VCGE2_RELACOES_INTERNACIONAIS("Relações Internacionais"),

    @XmlEnumValue("Relações de Trabalho")
    VCGE2_RELACOES_DE_TRABALHO("Relações de Trabalho"),

    @XmlEnumValue("Saneamento")
    VCGE2_SANEAMENTO("Saneamento"),

    @XmlEnumValue("Saneamento Básico Rural")
    VCGE2_SANEAMENTO_BASICO_RURAL("Saneamento Básico Rural"),

    @XmlEnumValue("Saneamento Básico Urbano")
    VCGE2_SANEAMENTO_BASICO_URBANO("Saneamento Básico Urbano"),

    @XmlEnumValue("Saúde")
    VCGE2_SAUDE("Saúde"),

    @XmlEnumValue("Segurança e Ordem Pública")
    VCGE2_SEGURANCA_E_ORDEM_PUBLICA("Segurança e Ordem Pública"),

    @XmlEnumValue("Serviços Públicos")
    VCGE2_SERVICOS_PUBLICOS("Serviços Públicos"),

    @XmlEnumValue("Serviços Urbanos")
    VCGE2_SERVICOS_URBANOS("Serviços Urbanos"),

    @XmlEnumValue("Sistema Financeiro")
    VCGE2_SISTEMA_FINANCEIRO("Sistema Financeiro"),

    @XmlEnumValue("Telecomunicações")
    VCGE2_TELECOMUNICACOES("Telecomunicações"),

    @XmlEnumValue("Trabalho")
    VCGE2_TRABALHO("Trabalho"),

    @XmlEnumValue("Transporte Aéreo")
    VCGE2_TRANSPORTE_AEREO("Transporte Aéreo"),

    @XmlEnumValue("Transporte Ferroviário")
    VCGE2_TRANSPORTE_FERROVIARIO("Transporte Ferroviário"),

    @XmlEnumValue("Transporte Hidroviário")
    VCGE2_TRANSPORTE_HIDROVIARIO("Transporte Hidroviário"),

    @XmlEnumValue("Transporte Rodoviário")
    VCGE2_TRANSPORTE_RODOVIARIO("Transporte Rodoviário"),

    @XmlEnumValue("Transportes")
    VCGE2_TRANSPORTES("Transportes"),

    @XmlEnumValue("Turismo")
    VCGE2_TURISMO("Turismo"),

    @XmlEnumValue("Urbanismo")
    VCGE2_URBANISMO("Urbanismo"),

    @XmlEnumValue("Vigilância Sanitária")
    VCGE2_VIGILANCIA_SANITARIA("Vigilância Sanitária"),

    @XmlEnumValue("Água")
    VCGE2_AGUA("Água"),

    @XmlEnumValue("Abastecimento")
    VCGE1_ABASTECIMENTO("Abastecimento"),

    @XmlEnumValue("Administração financeira")
    VCGE1_ADMINISTRACAO_FINANCEIRA("Administração financeira"),

    @XmlEnumValue("Agricultura orgânica")
    VCGE1_AGRICULTURA_ORGANICA("Agricultura orgânica"),

    @XmlEnumValue("Agropecuária")
    VCGE1_AGROPECUARIA("Agropecuária"),

    @XmlEnumValue("Alimento")
    VCGE1_ALIMENTO("Alimento"),

    @XmlEnumValue("Ambiente e saúde")
    VCGE1_AMBIENTE_E_SAUDE("Ambiente e saúde"),

    @XmlEnumValue("Comunicações")
    VCGE1_COMUNICACOES("Comunicações"),

    @XmlEnumValue("Comércio e Serviços")
    VCGE1_COMERCIO_E_SERVICOS("Comércio e Serviços"),

    @XmlEnumValue("Economia e Finanças")
    VCGE1_ECONOMIA_E_FINANCAS("Economia e Finanças"),

    @XmlEnumValue("Educação")
    VCGE1_EDUCACAO("Educação"),

    @XmlEnumValue("Educação básica")
    VCGE1_EDUCACAO_BASICA("Educação básica"),

    @XmlEnumValue("Educação superior")
    VCGE1_EDUCACAO_SUPERIOR("Educação superior"),

    @XmlEnumValue("Educação à distância")
    VCGE1_EDUCACAO_A_DISTANCIA("Educação à distância"),

    @XmlEnumValue("Emergências e Urgências")
    VCGE1_EMERGENCIAS_E_URGENCIAS("Emergências e Urgências"),

    @XmlEnumValue("Encargos financeiros")
    VCGE1_ENCARGOS_FINANCEIROS("Encargos financeiros"),

    @XmlEnumValue("Esporte e Lazer")
    VCGE1_ESPORTE_E_LAZER("Esporte e Lazer"),

    @XmlEnumValue("Finanças")
    VCGE1_FINANCAS("Finanças"),

    @XmlEnumValue("Habitação")
    VCGE1_HABITACAO("Habitação"),

    @XmlEnumValue("Humanização na saúde")
    VCGE1_HUMANIZACAO_NA_SAUDE("Humanização na saúde"),

    @XmlEnumValue("Indústria")
    VCGE1_INDUSTRIA("Indústria"),

    @XmlEnumValue("Meio ambiente")
    VCGE1_MEIO_AMBIENTE("Meio ambiente"),

    @XmlEnumValue("Pecuária")
    VCGE1_PECUARIA("Pecuária"),

    @XmlEnumValue("Pessoa")
    VCGE1_PESSOA("Pessoa"),

    @XmlEnumValue("Previdência Social")
    VCGE1_PREVIDENCIA_SOCIAL("Previdência Social"),

    @XmlEnumValue("Profissionais da educação")
    VCGE1_PROFISSIONAIS_DA_EDUCACAO("Profissionais da educação"),

    @XmlEnumValue("Proteção social")
    VCGE1_PROTECAO_SOCIAL("Proteção social"),

    @XmlEnumValue("Qualidade ambiental")
    VCGE1_QUALIDADE_AMBIENTAL("Qualidade ambiental"),

    @XmlEnumValue("Relações Internacionais")
    VCGE1_RELACOES_INTERNACIONAIS("Relações Internacionais"),

    @XmlEnumValue("Saúde")
    VCGE1_SAUDE("Saúde"),

    @XmlEnumValue("Saúde da criança")
    VCGE1_SAUDE_DA_CRIANCA("Saúde da criança"),

    @XmlEnumValue("Saúde da família")
    VCGE1_SAUDE_DA_FAMILIA("Saúde da família"),

    @XmlEnumValue("Saúde da mulher")
    VCGE1_SAUDE_DA_MULHER("Saúde da mulher"),

    @XmlEnumValue("Saúde do homem")
    VCGE1_SAUDE_DO_HOMEM("Saúde do homem"),

    @XmlEnumValue("Saúde do idoso")
    VCGE1_SAUDE_DO_IDOSO("Saúde do idoso"),

    @XmlEnumValue("Saúde dos portadores de deficiências")
    VCGE1_SAUDE_DOS_PORTADORES_DE_DEFICIENCIAS("Saúde dos portadores de deficiências"),

    @XmlEnumValue("Segurança e Ordem Pública")
    VCGE1_SEGURANCA_E_ORDEM_PUBLICA("Segurança e Ordem Pública"),

    @XmlEnumValue("Trabalho")
    VCGE1_TRABALHO("Trabalho"),

    @XmlEnumValue("Transportes")
    VCGE1_TRANSPORTES("Transportes"),

    @XmlEnumValue("Turismo")
    VCGE1_TURISMO("Turismo"),

    @XmlEnumValue("Urbanismo")
    VCGE1_URBANISMO("Urbanismo"),

    @XmlEnumValue("Águas")
    VCGE1_AGUAS("Águas");

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
