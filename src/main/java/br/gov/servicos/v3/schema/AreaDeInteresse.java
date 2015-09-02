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

    @XmlEnumValue("AIDS")
    VCGE1_AIDS("AIDS"),

    @XmlEnumValue("Abastecimento")
    VCGE1_ABASTECIMENTO("Abastecimento"),

    @XmlEnumValue("Abastecimento de água")
    VCGE1_ABASTECIMENTO_DE_AGUA("Abastecimento de água"),

    @XmlEnumValue("Acesso a recursos genéticos naturais")
    VCGE1_ACESSO_A_RECURSOS_GENETICOS_NATURAIS("Acesso a recursos genéticos naturais"),

    @XmlEnumValue("Acesso ao patrimônio genético")
    VCGE1_ACESSO_AO_PATRIMONIO_GENETICO("Acesso ao patrimônio genético"),

    @XmlEnumValue("Acesso e divulgação da produção cientifica")
    VCGE1_ACESSO_E_DIVULGACAO_DA_PRODUCAO_CIENTIFICA("Acesso e divulgação da produção cientifica"),

    @XmlEnumValue("Achados e perdidos")
    VCGE1_ACHADOS_E_PERDIDOS("Achados e perdidos"),

    @XmlEnumValue("Acidentes com produtos químicos perigosos")
    VCGE1_ACIDENTES_COM_PRODUTOS_QUIMICOS_PERIGOSOS("Acidentes com produtos químicos perigosos"),

    @XmlEnumValue("Acidentes de trânsito")
    VCGE1_ACIDENTES_DE_TRANSITO("Acidentes de trânsito"),

    @XmlEnumValue("Acompanhamento processual")
    VCGE1_ACOMPANHAMENTO_PROCESSUAL("Acompanhamento processual"),

    @XmlEnumValue("Acordo de nível de serviço de gestão ambiental")
    VCGE1_ACORDO_DE_NIVEL_DE_SERVICO_DE_GESTAO_AMBIENTAL("Acordo de nível de serviço de gestão ambiental"),

    @XmlEnumValue("Acordos e tratados de cooperação internacionais")
    VCGE1_ACORDOS_E_TRATADOS_DE_COOPERACAO_INTERNACIONAIS("Acordos e tratados de cooperação internacionais"),

    @XmlEnumValue("Acordos internacionais")
    VCGE1_ACORDOS_INTERNACIONAIS("Acordos internacionais"),

    @XmlEnumValue("Acupuntura")
    VCGE1_ACUPUNTURA("Acupuntura"),

    @XmlEnumValue("Adaptação à mudança do clima")
    VCGE1_ADAPTACAO_A_MUDANCA_DO_CLIMA("Adaptação à mudança do clima"),

    @XmlEnumValue("Aditivos de alimentos")
    VCGE1_ADITIVOS_DE_ALIMENTOS("Aditivos de alimentos"),

    @XmlEnumValue("Administração de concessões")
    VCGE1_ADMINISTRACAO_DE_CONCESSOES("Administração de concessões"),

    @XmlEnumValue("Administração de despesas")
    VCGE1_ADMINISTRACAO_DE_DESPESAS("Administração de despesas"),

    @XmlEnumValue("Administração de receitas")
    VCGE1_ADMINISTRACAO_DE_RECEITAS("Administração de receitas"),

    @XmlEnumValue("Administração financeira")
    VCGE1_ADMINISTRACAO_FINANCEIRA("Administração financeira"),

    @XmlEnumValue("Administração pública")
    VCGE1_ADMINISTRACAO_PUBLICA("Administração pública"),

    @XmlEnumValue("Adoção")
    VCGE1_ADOCAO("Adoção"),

    @XmlEnumValue("Adoção de tecnologias sustentáveis")
    VCGE1_ADOCAO_DE_TECNOLOGIAS_SUSTENTAVEIS("Adoção de tecnologias sustentáveis"),

    @XmlEnumValue("Adubação e correção")
    VCGE1_ADUBACAO_E_CORRECAO("Adubação e correção"),

    @XmlEnumValue("Aeronáutica")
    VCGE1_AERONAUTICA("Aeronáutica"),

    @XmlEnumValue("Aeroportos")
    VCGE1_AEROPORTOS("Aeroportos"),

    @XmlEnumValue("Afro-brasileiro")
    VCGE1_AFRO_BRASILEIRO("Afro-brasileiro"),

    @XmlEnumValue("Agenda 21")
    VCGE1_AGENDA_21("Agenda 21"),

    @XmlEnumValue("Agenda ambiental na administração pública (A3P)")
    VCGE1_AGENDA_AMBIENTAL_NA_ADMINISTRACAO_PUBLICA_A3P("Agenda ambiental na administração pública (A3P)"),

    @XmlEnumValue("Agricultura de precisão")
    VCGE1_AGRICULTURA_DE_PRECISAO("Agricultura de precisão"),

    @XmlEnumValue("Agricultura de subsistência")
    VCGE1_AGRICULTURA_DE_SUBSISTENCIA("Agricultura de subsistência"),

    @XmlEnumValue("Agricultura familiar")
    VCGE1_AGRICULTURA_FAMILIAR("Agricultura familiar"),

    @XmlEnumValue("Agricultura orgânica")
    VCGE1_AGRICULTURA_ORGANICA("Agricultura orgânica"),

    @XmlEnumValue("Agricultura, extrativismo e pesca")
    VCGE1_AGRICULTURA_EXTRATIVISMO_E_PESCA("Agricultura, extrativismo e pesca"),

    @XmlEnumValue("Agrobiodiversidade")
    VCGE1_AGROBIODIVERSIDADE("Agrobiodiversidade"),

    @XmlEnumValue("Agroestrativismo")
    VCGE1_AGROESTRATIVISMO("Agroestrativismo"),

    @XmlEnumValue("Agroextrativismo")
    VCGE1_AGROEXTRATIVISMO("Agroextrativismo"),

    @XmlEnumValue("Agroindústria")
    VCGE1_AGROINDUSTRIA("Agroindústria"),

    @XmlEnumValue("Agronegócio")
    VCGE1_AGRONEGOCIO("Agronegócio"),

    @XmlEnumValue("Agrotóxicos")
    VCGE1_AGROTOXICOS("Agrotóxicos"),

    @XmlEnumValue("Agência de fomento a pós-graduação")
    VCGE1_AGENCIA_DE_FOMENTO_A_POS_GRADUACAO("Agência de fomento a pós-graduação"),

    @XmlEnumValue("Agência de água")
    VCGE1_AGENCIA_DE_AGUA("Agência de água"),

    @XmlEnumValue("Agências de viagens")
    VCGE1_AGENCIAS_DE_VIAGENS("Agências de viagens"),

    @XmlEnumValue("Aleitamento materno")
    VCGE1_ALEITAMENTO_MATERNO("Aleitamento materno"),

    @XmlEnumValue("Alfabetização de jovens e adultos")
    VCGE1_ALFABETIZACAO_DE_JOVENS_E_ADULTOS("Alfabetização de jovens e adultos"),

    @XmlEnumValue("Alienação de bens")
    VCGE1_ALIENACAO_DE_BENS("Alienação de bens"),

    @XmlEnumValue("Alimentação do trabalhador")
    VCGE1_ALIMENTACAO_DO_TRABALHADOR("Alimentação do trabalhador"),

    @XmlEnumValue("Alimentação e nutrição")
    VCGE1_ALIMENTACAO_E_NUTRICAO("Alimentação e nutrição"),

    @XmlEnumValue("Alimentação escolar")
    VCGE1_ALIMENTACAO_ESCOLAR("Alimentação escolar"),

    @XmlEnumValue("Alimentação saudável")
    VCGE1_ALIMENTACAO_SAUDAVEL("Alimentação saudável"),

    @XmlEnumValue("Alimento")
    VCGE1_ALIMENTO("Alimento"),

    @XmlEnumValue("Alistamento militar")
    VCGE1_ALISTAMENTO_MILITAR("Alistamento militar"),

    @XmlEnumValue("Alto Paraguai")
    VCGE1_ALTO_PARAGUAI("Alto Paraguai"),

    @XmlEnumValue("Alvará de funcionamento")
    VCGE1_ALVARA_DE_FUNCIONAMENTO("Alvará de funcionamento"),

    @XmlEnumValue("Amazônia")
    VCGE1_AMAZONIA("Amazônia"),

    @XmlEnumValue("Amazônica")
    VCGE1_AMAZONICA("Amazônica"),

    @XmlEnumValue("Ambiente e saúde")
    VCGE1_AMBIENTE_E_SAUDE("Ambiente e saúde"),

    @XmlEnumValue("Ambientes ocupados pelo homem")
    VCGE1_AMBIENTES_OCUPADOS_PELO_HOMEM("Ambientes ocupados pelo homem"),

    @XmlEnumValue("Ambulatório")
    VCGE1_AMBULATORIO("Ambulatório"),

    @XmlEnumValue("Ameaças à biodiversidade")
    VCGE1_AMEACAS_A_BIODIVERSIDADE("Ameaças à biodiversidade"),

    @XmlEnumValue("Ampliação")
    VCGE1_AMPLIACAO("Ampliação"),

    @XmlEnumValue("Animais exóticos")
    VCGE1_ANIMAIS_EXOTICOS("Animais exóticos"),

    @XmlEnumValue("Animação")
    VCGE1_ANIMACAO("Animação"),

    @XmlEnumValue("Anistia política")
    VCGE1_ANISTIA_POLITICA("Anistia política"),

    @XmlEnumValue("Antártida")
    VCGE1_ANTARTIDA("Antártida"),

    @XmlEnumValue("Análise de risco - área animal")
    VCGE1_ANALISE_DE_RISCO_AREA_ANIMAL("Análise de risco - área animal"),

    @XmlEnumValue("Análise de risco - área vegetal")
    VCGE1_ANALISE_DE_RISCO_AREA_VEGETAL("Análise de risco - área vegetal"),

    @XmlEnumValue("Análise de risco das espécies exóticas invasoras")
    VCGE1_ANALISE_DE_RISCO_DAS_ESPECIES_EXOTICAS_INVASORAS("Análise de risco das espécies exóticas invasoras"),

    @XmlEnumValue("Apicultura")
    VCGE1_APICULTURA("Apicultura"),

    @XmlEnumValue("Aplicação dos recursos")
    VCGE1_APLICACAO_DOS_RECURSOS("Aplicação dos recursos"),

    @XmlEnumValue("Apoio escolar")
    VCGE1_APOIO_ESCOLAR("Apoio escolar"),

    @XmlEnumValue("Apoio técnico a pesquisa e iniciação cientifica")
    VCGE1_APOIO_TECNICO_A_PESQUISA_E_INICIACAO_CIENTIFICA("Apoio técnico a pesquisa e iniciação cientifica"),

    @XmlEnumValue("Aposentadoria especial")
    VCGE1_APOSENTADORIA_ESPECIAL("Aposentadoria especial"),

    @XmlEnumValue("Aposentadoria por idade")
    VCGE1_APOSENTADORIA_POR_IDADE("Aposentadoria por idade"),

    @XmlEnumValue("Aposentadoria por invalidez")
    VCGE1_APOSENTADORIA_POR_INVALIDEZ("Aposentadoria por invalidez"),

    @XmlEnumValue("Aposentadoria por tempo de contribuição")
    VCGE1_APOSENTADORIA_POR_TEMPO_DE_CONTRIBUICAO("Aposentadoria por tempo de contribuição"),

    @XmlEnumValue("Aquecimento global")
    VCGE1_AQUECIMENTO_GLOBAL("Aquecimento global"),

    @XmlEnumValue("Aquicultura e pesca")
    VCGE1_AQUICULTURA_E_PESCA("Aquicultura e pesca"),

    @XmlEnumValue("Aqüicultura")
    VCGE1_AQUEICULTURA("Aqüicultura"),

    @XmlEnumValue("Armazenamento de alimento")
    VCGE1_ARMAZENAMENTO_DE_ALIMENTO("Armazenamento de alimento"),

    @XmlEnumValue("Arquitetura e engenharia em saúde")
    VCGE1_ARQUITETURA_E_ENGENHARIA_EM_SAUDE("Arquitetura e engenharia em saúde"),

    @XmlEnumValue("Arquivos")
    VCGE1_ARQUIVOS("Arquivos"),

    @XmlEnumValue("Arranjos produtivos locais (APL)")
    VCGE1_ARRANJOS_PRODUTIVOS_LOCAIS_APL("Arranjos produtivos locais (APL)"),

    @XmlEnumValue("Artes cênicas")
    VCGE1_ARTES_CENICAS("Artes cênicas"),

    @XmlEnumValue("Artes gráficas")
    VCGE1_ARTES_GRAFICAS("Artes gráficas"),

    @XmlEnumValue("Artes visuais")
    VCGE1_ARTES_VISUAIS("Artes visuais"),

    @XmlEnumValue("Asilo político")
    VCGE1_ASILO_POLITICO("Asilo político"),

    @XmlEnumValue("Assentamentos rurais")
    VCGE1_ASSENTAMENTOS_RURAIS("Assentamentos rurais"),

    @XmlEnumValue("Assistência ao estudante")
    VCGE1_ASSISTENCIA_AO_ESTUDANTE("Assistência ao estudante"),

    @XmlEnumValue("Assistência e desenvolvimento social")
    VCGE1_ASSISTENCIA_E_DESENVOLVIMENTO_SOCIAL("Assistência e desenvolvimento social"),

    @XmlEnumValue("Assistência jurídica gratuita")
    VCGE1_ASSISTENCIA_JURIDICA_GRATUITA("Assistência jurídica gratuita"),

    @XmlEnumValue("Assistência social ao preso e a família")
    VCGE1_ASSISTENCIA_SOCIAL_AO_PRESO_E_A_FAMILIA("Assistência social ao preso e a família"),

    @XmlEnumValue("Assistência técnica")
    VCGE1_ASSISTENCIA_TECNICA("Assistência técnica"),

    @XmlEnumValue("Associação")
    VCGE1_ASSOCIACAO("Associação"),

    @XmlEnumValue("Associação agrícola")
    VCGE1_ASSOCIACAO_AGRICOLA("Associação agrícola"),

    @XmlEnumValue("Atendimento ao cidadão")
    VCGE1_ATENDIMENTO_AO_CIDADAO("Atendimento ao cidadão"),

    @XmlEnumValue("Aterro sanitário")
    VCGE1_ATERRO_SANITARIO("Aterro sanitário"),

    @XmlEnumValue("Aterros sanitários")
    VCGE1_ATERROS_SANITARIOS("Aterros sanitários"),

    @XmlEnumValue("Atestado de antecedentes")
    VCGE1_ATESTADO_DE_ANTECEDENTES("Atestado de antecedentes"),

    @XmlEnumValue("Atividade sucroalcooleira")
    VCGE1_ATIVIDADE_SUCROALCOOLEIRA("Atividade sucroalcooleira"),

    @XmlEnumValue("Atividades científicas e de pesquisa de patrimônio genético")
    VCGE1_ATIVIDADES_CIENTIFICAS_E_DE_PESQUISA_DE_PATRIMONIO_GENETICO("Atividades científicas e de pesquisa de patrimônio genético"),

    @XmlEnumValue("Atividades de turismo")
    VCGE1_ATIVIDADES_DE_TURISMO("Atividades de turismo"),

    @XmlEnumValue("Atlântico leste")
    VCGE1_ATLANTICO_LESTE("Atlântico leste"),

    @XmlEnumValue("Atlântico nordeste ocidental")
    VCGE1_ATLANTICO_NORDESTE_OCIDENTAL("Atlântico nordeste ocidental"),

    @XmlEnumValue("Atlântico nordeste oriental")
    VCGE1_ATLANTICO_NORDESTE_ORIENTAL("Atlântico nordeste oriental"),

    @XmlEnumValue("Atlântico sudeste")
    VCGE1_ATLANTICO_SUDESTE("Atlântico sudeste"),

    @XmlEnumValue("Atlântico sul")
    VCGE1_ATLANTICO_SUL("Atlântico sul"),

    @XmlEnumValue("Atmosfera")
    VCGE1_ATMOSFERA("Atmosfera"),

    @XmlEnumValue("Audiovisual")
    VCGE1_AUDIOVISUAL("Audiovisual"),

    @XmlEnumValue("Auditoria em saúde")
    VCGE1_AUDITORIA_EM_SAUDE("Auditoria em saúde"),

    @XmlEnumValue("Audiências públicas")
    VCGE1_AUDIENCIAS_PUBLICAS("Audiências públicas"),

    @XmlEnumValue("Autorização de cursos")
    VCGE1_AUTORIZACAO_DE_CURSOS("Autorização de cursos"),

    @XmlEnumValue("Autorização de saída")
    VCGE1_AUTORIZACAO_DE_SAIDA("Autorização de saída"),

    @XmlEnumValue("Autorização de transporte aquaviário")
    VCGE1_AUTORIZACAO_DE_TRANSPORTE_AQUAVIARIO("Autorização de transporte aquaviário"),

    @XmlEnumValue("Autorizações para uso de patrimônio genético")
    VCGE1_AUTORIZACOES_PARA_USO_DE_PATRIMONIO_GENETICO("Autorizações para uso de patrimônio genético"),

    @XmlEnumValue("Auxilio a pesquisa")
    VCGE1_AUXILIO_A_PESQUISA("Auxilio a pesquisa"),

    @XmlEnumValue("Auxilio doença")
    VCGE1_AUXILIO_DOENCA("Auxilio doença"),

    @XmlEnumValue("Auxilio reclusão")
    VCGE1_AUXILIO_RECLUSAO("Auxilio reclusão"),

    @XmlEnumValue("Auxilio sociais")
    VCGE1_AUXILIO_SOCIAIS("Auxilio sociais"),

    @XmlEnumValue("Avaliação ambiental")
    VCGE1_AVALIACAO_AMBIENTAL("Avaliação ambiental"),

    @XmlEnumValue("Avaliação ambiental estratégica")
    VCGE1_AVALIACAO_AMBIENTAL_ESTRATEGICA("Avaliação ambiental estratégica"),

    @XmlEnumValue("Avaliação ambiental integrada de bacia hidrográfica")
    VCGE1_AVALIACAO_AMBIENTAL_INTEGRADA_DE_BACIA_HIDROGRAFICA("Avaliação ambiental integrada de bacia hidrográfica"),

    @XmlEnumValue("Avaliação da qualidade ambiental")
    VCGE1_AVALIACAO_DA_QUALIDADE_AMBIENTAL("Avaliação da qualidade ambiental"),

    @XmlEnumValue("Avaliação de estoque pesqueiro")
    VCGE1_AVALIACAO_DE_ESTOQUE_PESQUEIRO("Avaliação de estoque pesqueiro"),

    @XmlEnumValue("Avaliação de impactos ambientais")
    VCGE1_AVALIACAO_DE_IMPACTOS_AMBIENTAIS("Avaliação de impactos ambientais"),

    @XmlEnumValue("Aviação agrícola")
    VCGE1_AVIACAO_AGRICOLA("Aviação agrícola"),

    @XmlEnumValue("Avicultura")
    VCGE1_AVICULTURA("Avicultura"),

    @XmlEnumValue("Ação judiciária")
    VCGE1_ACAO_JUDICIARIA("Ação judiciária"),

    @XmlEnumValue("Ações e política de prevenção e repressão ao crime")
    VCGE1_ACOES_E_POLITICA_DE_PREVENCAO_E_REPRESSAO_AO_CRIME("Ações e política de prevenção e repressão ao crime"),

    @XmlEnumValue("Bacharelado")
    VCGE1_BACHARELADO("Bacharelado"),

    @XmlEnumValue("Bacias hidrográficas")
    VCGE1_BACIAS_HIDROGRAFICAS("Bacias hidrográficas"),

    @XmlEnumValue("Banco de dados")
    VCGE1_BANCO_DE_DADOS("Banco de dados"),

    @XmlEnumValue("Banco de germoplasma")
    VCGE1_BANCO_DE_GERMOPLASMA("Banco de germoplasma"),

    @XmlEnumValue("Banco de leite humano")
    VCGE1_BANCO_DE_LEITE_HUMANO("Banco de leite humano"),

    @XmlEnumValue("Banco de sangue")
    VCGE1_BANCO_DE_SANGUE("Banco de sangue"),

    @XmlEnumValue("Bancos")
    VCGE1_BANCOS("Bancos"),

    @XmlEnumValue("Barreiras externas")
    VCGE1_BARREIRAS_EXTERNAS("Barreiras externas"),

    @XmlEnumValue("Beneficio assistencial ao idoso e ao deficiente")
    VCGE1_BENEFICIO_ASSISTENCIAL_AO_IDOSO_E_AO_DEFICIENTE("Beneficio assistencial ao idoso e ao deficiente"),

    @XmlEnumValue("Beneficio previdenciários")
    VCGE1_BENEFICIO_PREVIDENCIARIOS("Beneficio previdenciários"),

    @XmlEnumValue("Bibliotecas")
    VCGE1_BIBLIOTECAS("Bibliotecas"),

    @XmlEnumValue("Biocombustível")
    VCGE1_BIOCOMBUSTIVEL("Biocombustível"),

    @XmlEnumValue("Biocomércio")
    VCGE1_BIOCOMERCIO("Biocomércio"),

    @XmlEnumValue("Biodiversidade")
    VCGE1_BIODIVERSIDADE("Biodiversidade"),

    @XmlEnumValue("Bioenergia")
    VCGE1_BIOENERGIA("Bioenergia"),

    @XmlEnumValue("Biogás")
    VCGE1_BIOGAS("Biogás"),

    @XmlEnumValue("Biologia pesqueira")
    VCGE1_BIOLOGIA_PESQUEIRA("Biologia pesqueira"),

    @XmlEnumValue("Biologia vegetal")
    VCGE1_BIOLOGIA_VEGETAL("Biologia vegetal"),

    @XmlEnumValue("Biomas brasileiros")
    VCGE1_BIOMAS_BRASILEIROS("Biomas brasileiros"),

    @XmlEnumValue("Biomassa")
    VCGE1_BIOMASSA("Biomassa"),

    @XmlEnumValue("Biopirataria")
    VCGE1_BIOPIRATARIA("Biopirataria"),

    @XmlEnumValue("Bioprospecção")
    VCGE1_BIOPROSPECCAO("Bioprospecção"),

    @XmlEnumValue("Biossegurança")
    VCGE1_BIOSSEGURANCA("Biossegurança"),

    @XmlEnumValue("Biotecnologia")
    VCGE1_BIOTECNOLOGIA("Biotecnologia"),

    @XmlEnumValue("Bioética")
    VCGE1_BIOETICA("Bioética"),

    @XmlEnumValue("Bolsa de estudos pós-graduação")
    VCGE1_BOLSA_DE_ESTUDOS_POS_GRADUACAO("Bolsa de estudos pós-graduação"),

    @XmlEnumValue("Bombeiro 193")
    VCGE1_BOMBEIRO_193("Bombeiro 193"),

    @XmlEnumValue("Bovinocultura")
    VCGE1_BOVINOCULTURA("Bovinocultura"),

    @XmlEnumValue("Brasil Sorridente")
    VCGE1_BRASIL_SORRIDENTE("Brasil Sorridente"),

    @XmlEnumValue("Bubalinocultura")
    VCGE1_BUBALINOCULTURA("Bubalinocultura"),

    @XmlEnumValue("CPF")
    VCGE1_CPF("CPF"),

    @XmlEnumValue("Caatinga")
    VCGE1_CAATINGA("Caatinga"),

    @XmlEnumValue("Cadastro de coleções de espécies")
    VCGE1_CADASTRO_DE_COLECOES_DE_ESPECIES("Cadastro de coleções de espécies"),

    @XmlEnumValue("Cadastro nacional de estabelecimento de saúde")
    VCGE1_CADASTRO_NACIONAL_DE_ESTABELECIMENTO_DE_SAUDE("Cadastro nacional de estabelecimento de saúde"),

    @XmlEnumValue("Cadeias")
    VCGE1_CADEIAS("Cadeias"),

    @XmlEnumValue("Cadeias produtivas da sociobiodiversidade")
    VCGE1_CADEIAS_PRODUTIVAS_DA_SOCIOBIODIVERSIDADE("Cadeias produtivas da sociobiodiversidade"),

    @XmlEnumValue("Cadeias produtivas de base florestal")
    VCGE1_CADEIAS_PRODUTIVAS_DE_BASE_FLORESTAL("Cadeias produtivas de base florestal"),

    @XmlEnumValue("Cadeias produtivas sustentáveis")
    VCGE1_CADEIAS_PRODUTIVAS_SUSTENTAVEIS("Cadeias produtivas sustentáveis"),

    @XmlEnumValue("Calendário de vacinação da criança")
    VCGE1_CALENDARIO_DE_VACINACAO_DA_CRIANCA("Calendário de vacinação da criança"),

    @XmlEnumValue("Calendário de vacinação do adulto")
    VCGE1_CALENDARIO_DE_VACINACAO_DO_ADULTO("Calendário de vacinação do adulto"),

    @XmlEnumValue("Calendário de vacinação do idoso")
    VCGE1_CALENDARIO_DE_VACINACAO_DO_IDOSO("Calendário de vacinação do idoso"),

    @XmlEnumValue("Camada de ozônio")
    VCGE1_CAMADA_DE_OZONIO("Camada de ozônio"),

    @XmlEnumValue("Campanha conduta consciente")
    VCGE1_CAMPANHA_CONDUTA_CONSCIENTE("Campanha conduta consciente"),

    @XmlEnumValue("Campanha férias sustentáveis")
    VCGE1_CAMPANHA_FERIAS_SUSTENTAVEIS("Campanha férias sustentáveis"),

    @XmlEnumValue("Campanhas de saúde")
    VCGE1_CAMPANHAS_DE_SAUDE("Campanhas de saúde"),

    @XmlEnumValue("Capacitação")
    VCGE1_CAPACITACAO("Capacitação"),

    @XmlEnumValue("Capacitação para a implementação de atividades florestais")
    VCGE1_CAPACITACAO_PARA_A_IMPLEMENTACAO_DE_ATIVIDADES_FLORESTAIS("Capacitação para a implementação de atividades florestais"),

    @XmlEnumValue("Capacitação social nas regiões hidrográficas")
    VCGE1_CAPACITACAO_SOCIAL_NAS_REGIOES_HIDROGRAFICAS("Capacitação social nas regiões hidrográficas"),

    @XmlEnumValue("Capina")
    VCGE1_CAPINA("Capina"),

    @XmlEnumValue("Caprinocultura")
    VCGE1_CAPRINOCULTURA("Caprinocultura"),

    @XmlEnumValue("Caracterização técnico-econômica de cadeias produtivas sustentáveis")
    VCGE1_CARACTERIZACAO_TECNICO_ECONOMICA_DE_CADEIAS_PRODUTIVAS_SUSTENTAVEIS("Caracterização técnico-econômica de cadeias produtivas sustentáveis"),

    @XmlEnumValue("Carcinicultura")
    VCGE1_CARCINICULTURA("Carcinicultura"),

    @XmlEnumValue("Carcinocultura")
    VCGE1_CARCINOCULTURA("Carcinocultura"),

    @XmlEnumValue("Carpotecas")
    VCGE1_CARPOTECAS("Carpotecas"),

    @XmlEnumValue("Carteira de estudante")
    VCGE1_CARTEIRA_DE_ESTUDANTE("Carteira de estudante"),

    @XmlEnumValue("Carteira de identidade")
    VCGE1_CARTEIRA_DE_IDENTIDADE("Carteira de identidade"),

    @XmlEnumValue("Carteira de identificação do estrangeiro")
    VCGE1_CARTEIRA_DE_IDENTIFICACAO_DO_ESTRANGEIRO("Carteira de identificação do estrangeiro"),

    @XmlEnumValue("Carteira de trabalho")
    VCGE1_CARTEIRA_DE_TRABALHO("Carteira de trabalho"),

    @XmlEnumValue("Carteira nacional de habilitação")
    VCGE1_CARTEIRA_NACIONAL_DE_HABILITACAO("Carteira nacional de habilitação"),

    @XmlEnumValue("Cartão nacional de saúde")
    VCGE1_CARTAO_NACIONAL_DE_SAUDE("Cartão nacional de saúde"),

    @XmlEnumValue("Cartão transporte")
    VCGE1_CARTAO_TRANSPORTE("Cartão transporte"),

    @XmlEnumValue("Carvão mineral")
    VCGE1_CARVAO_MINERAL("Carvão mineral"),

    @XmlEnumValue("Casa de albergados")
    VCGE1_CASA_DE_ALBERGADOS("Casa de albergados"),

    @XmlEnumValue("Casa própria")
    VCGE1_CASA_PROPRIA("Casa própria"),

    @XmlEnumValue("Casamento")
    VCGE1_CASAMENTO("Casamento"),

    @XmlEnumValue("Cavernas")
    VCGE1_CAVERNAS("Cavernas"),

    @XmlEnumValue("Cavidades")
    VCGE1_CAVIDADES("Cavidades"),

    @XmlEnumValue("Cavidades naturais subterrâneas")
    VCGE1_CAVIDADES_NATURAIS_SUBTERRANEAS("Cavidades naturais subterrâneas"),

    @XmlEnumValue("Cemitérios")
    VCGE1_CEMITERIOS("Cemitérios"),

    @XmlEnumValue("Centrais de atendimento")
    VCGE1_CENTRAIS_DE_ATENDIMENTO("Centrais de atendimento"),

    @XmlEnumValue("Centro de documentação")
    VCGE1_CENTRO_DE_DOCUMENTACAO("Centro de documentação"),

    @XmlEnumValue("Centro de triagem")
    VCGE1_CENTRO_DE_TRIAGEM("Centro de triagem"),

    @XmlEnumValue("Cera vegetal")
    VCGE1_CERA_VEGETAL("Cera vegetal"),

    @XmlEnumValue("Cerrado")
    VCGE1_CERRADO("Cerrado"),

    @XmlEnumValue("Certidões")
    VCGE1_CERTIDOES("Certidões"),

    @XmlEnumValue("Certificado Internacional de Vacinação e Profilaxia (CIVP)")
    VCGE1_CERTIFICADO_INTERNACIONAL_DE_VACINACAO_E_PROFILAXIA_CIVP("Certificado Internacional de Vacinação e Profilaxia (CIVP)"),

    @XmlEnumValue("Certificado de Produto Brasileiro (CPB)")
    VCGE1_CERTIFICADO_DE_PRODUTO_BRASILEIRO_CPB("Certificado de Produto Brasileiro (CPB)"),

    @XmlEnumValue("Certificado de proficiência em língua portuguesa para estrangeiro")
    VCGE1_CERTIFICADO_DE_PROFICIENCIA_EM_LINGUA_PORTUGUESA_PARA_ESTRANGEIRO("Certificado de proficiência em língua portuguesa para estrangeiro"),

    @XmlEnumValue("Certificação de produto de telecomunicações")
    VCGE1_CERTIFICACAO_DE_PRODUTO_DE_TELECOMUNICACOES("Certificação de produto de telecomunicações"),

    @XmlEnumValue("Certificação de sementes")
    VCGE1_CERTIFICACAO_DE_SEMENTES("Certificação de sementes"),

    @XmlEnumValue("Cidadania ambiental")
    VCGE1_CIDADANIA_AMBIENTAL("Cidadania ambiental"),

    @XmlEnumValue("Circo")
    VCGE1_CIRCO("Circo"),

    @XmlEnumValue("Ciência e Tecnologia")
    VCGE1_CIENCIA_E_TECNOLOGIA("Ciência e Tecnologia"),

    @XmlEnumValue("Ciência e tecnologia em saúde")
    VCGE1_CIENCIA_E_TECNOLOGIA_EM_SAUDE("Ciência e tecnologia em saúde"),

    @XmlEnumValue("Ciência espacial")
    VCGE1_CIENCIA_ESPACIAL("Ciência espacial"),

    @XmlEnumValue("Ciência, Informação e Comunicação")
    VCGE1_CIENCIA_INFORMACAO_E_COMUNICACAO("Ciência, Informação e Comunicação"),

    @XmlEnumValue("Classificação brasileira de ocupações")
    VCGE1_CLASSIFICACAO_BRASILEIRA_DE_OCUPACOES("Classificação brasileira de ocupações"),

    @XmlEnumValue("Classificação da informação")
    VCGE1_CLASSIFICACAO_DA_INFORMACAO("Classificação da informação"),

    @XmlEnumValue("Classificação indicativa de vídeos, jogos, cinemas e outros")
    VCGE1_CLASSIFICACAO_INDICATIVA_DE_VIDEOS_JOGOS_CINEMAS_E_OUTROS("Classificação indicativa de vídeos, jogos, cinemas e outros"),

    @XmlEnumValue("Clima")
    VCGE1_CLIMA("Clima"),

    @XmlEnumValue("Clínica animal")
    VCGE1_CLINICA_ANIMAL("Clínica animal"),

    @XmlEnumValue("Cobrança pelo uso da água")
    VCGE1_COBRANCA_PELO_USO_DA_AGUA("Cobrança pelo uso da água"),

    @XmlEnumValue("Colegiados")
    VCGE1_COLEGIADOS("Colegiados"),

    @XmlEnumValue("Coleta de patrimônio genético")
    VCGE1_COLETA_DE_PATRIMONIO_GENETICO("Coleta de patrimônio genético"),

    @XmlEnumValue("Coleta domiciliar (rotas, datas e horários)")
    VCGE1_COLETA_DOMICILIAR_ROTAS_DATAS_E_HORARIOS("Coleta domiciliar (rotas, datas e horários)"),

    @XmlEnumValue("Coleta e transporte de recursos genéticos naturais")
    VCGE1_COLETA_E_TRANSPORTE_DE_RECURSOS_GENETICOS_NATURAIS("Coleta e transporte de recursos genéticos naturais"),

    @XmlEnumValue("Coleta seletiva")
    VCGE1_COLETA_SELETIVA("Coleta seletiva"),

    @XmlEnumValue("Coletivos educadores")
    VCGE1_COLETIVOS_EDUCADORES("Coletivos educadores"),

    @XmlEnumValue("Coleções")
    VCGE1_COLECOES("Coleções"),

    @XmlEnumValue("Coleções científicas de patrimônio genético")
    VCGE1_COLECOES_CIENTIFICAS_DE_PATRIMONIO_GENETICO("Coleções científicas de patrimônio genético"),

    @XmlEnumValue("Coleções de plantas vivas")
    VCGE1_COLECOES_DE_PLANTAS_VIVAS("Coleções de plantas vivas"),

    @XmlEnumValue("Coleções nacionais de referência")
    VCGE1_COLECOES_NACIONAIS_DE_REFERENCIA("Coleções nacionais de referência"),

    @XmlEnumValue("Colheita")
    VCGE1_COLHEITA("Colheita"),

    @XmlEnumValue("Colonização")
    VCGE1_COLONIZACAO("Colonização"),

    @XmlEnumValue("Combate ao desmatamento")
    VCGE1_COMBATE_AO_DESMATAMENTO("Combate ao desmatamento"),

    @XmlEnumValue("Combate à desertificação")
    VCGE1_COMBATE_A_DESERTIFICACAO("Combate à desertificação"),

    @XmlEnumValue("Combate à pirataria")
    VCGE1_COMBATE_A_PIRATARIA("Combate à pirataria"),

    @XmlEnumValue("Combustível")
    VCGE1_COMBUSTIVEL("Combustível"),

    @XmlEnumValue("Comercialização agrícola")
    VCGE1_COMERCIALIZACAO_AGRICOLA("Comercialização agrícola"),

    @XmlEnumValue("Comissão nacional de segurança química - CONASQ")
    VCGE1_COMISSAO_NACIONAL_DE_SEGURANCA_QUIMICA_CONASQ("Comissão nacional de segurança química - CONASQ"),

    @XmlEnumValue("Comitês de bacias hidrográficas")
    VCGE1_COMITES_DE_BACIAS_HIDROGRAFICAS("Comitês de bacias hidrográficas"),

    @XmlEnumValue("Compensação ambiental")
    VCGE1_COMPENSACAO_AMBIENTAL("Compensação ambiental"),

    @XmlEnumValue("Compensação de reserva legal")
    VCGE1_COMPENSACAO_DE_RESERVA_LEGAL("Compensação de reserva legal"),

    @XmlEnumValue("Complementação de estudos")
    VCGE1_COMPLEMENTACAO_DE_ESTUDOS("Complementação de estudos"),

    @XmlEnumValue("Composição de alimentos")
    VCGE1_COMPOSICAO_DE_ALIMENTOS("Composição de alimentos"),

    @XmlEnumValue("Compra de lixo")
    VCGE1_COMPRA_DE_LIXO("Compra de lixo"),

    @XmlEnumValue("Compras governamentais")
    VCGE1_COMPRAS_GOVERNAMENTAIS("Compras governamentais"),

    @XmlEnumValue("Compras públicas sustentáveis")
    VCGE1_COMPRAS_PUBLICAS_SUSTENTAVEIS("Compras públicas sustentáveis"),

    @XmlEnumValue("Comunicação")
    VCGE1_COMUNICACAO("Comunicação"),

    @XmlEnumValue("Comunicação multimídia")
    VCGE1_COMUNICACAO_MULTIMIDIA("Comunicação multimídia"),

    @XmlEnumValue("Comunicação móvel")
    VCGE1_COMUNICACAO_MOVEL("Comunicação móvel"),

    @XmlEnumValue("Comunidade e sociedade")
    VCGE1_COMUNIDADE_E_SOCIEDADE("Comunidade e sociedade"),

    @XmlEnumValue("Comunidade pesqueira")
    VCGE1_COMUNIDADE_PESQUEIRA("Comunidade pesqueira"),

    @XmlEnumValue("Comunidades locais de florestas públicas")
    VCGE1_COMUNIDADES_LOCAIS_DE_FLORESTAS_PUBLICAS("Comunidades locais de florestas públicas"),

    @XmlEnumValue("Comércio")
    VCGE1_COMERCIO("Comércio"),

    @XmlEnumValue("Comércio e Serviços")
    VCGE1_COMERCIO_E_SERVICOS("Comércio e Serviços"),

    @XmlEnumValue("Comércio eletrônico")
    VCGE1_COMERCIO_ELETRONICO("Comércio eletrônico"),

    @XmlEnumValue("Comércio em parques")
    VCGE1_COMERCIO_EM_PARQUES("Comércio em parques"),

    @XmlEnumValue("Comércio exterior")
    VCGE1_COMERCIO_EXTERIOR("Comércio exterior"),

    @XmlEnumValue("Comércio internacional e meio ambiente")
    VCGE1_COMERCIO_INTERNACIONAL_E_MEIO_AMBIENTE("Comércio internacional e meio ambiente"),

    @XmlEnumValue("Comércio, Serviços e Turismo")
    VCGE1_COMERCIO_SERVICOS_E_TURISMO("Comércio, Serviços e Turismo"),

    @XmlEnumValue("Concessão aos taxistas")
    VCGE1_CONCESSAO_AOS_TAXISTAS("Concessão aos taxistas"),

    @XmlEnumValue("Concessão de transporte ferroviário")
    VCGE1_CONCESSAO_DE_TRANSPORTE_FERROVIARIO("Concessão de transporte ferroviário"),

    @XmlEnumValue("Concessão de transporte rodoviário")
    VCGE1_CONCESSAO_DE_TRANSPORTE_RODOVIARIO("Concessão de transporte rodoviário"),

    @XmlEnumValue("Concessão de uso florestas públicas")
    VCGE1_CONCESSAO_DE_USO_FLORESTAS_PUBLICAS("Concessão de uso florestas públicas"),

    @XmlEnumValue("Concessão florestal")
    VCGE1_CONCESSAO_FLORESTAL("Concessão florestal"),

    @XmlEnumValue("Concessões")
    VCGE1_CONCESSOES("Concessões"),

    @XmlEnumValue("Concessões de equipamentos urbanos")
    VCGE1_CONCESSOES_DE_EQUIPAMENTOS_URBANOS("Concessões de equipamentos urbanos"),

    @XmlEnumValue("Conclusão de obra")
    VCGE1_CONCLUSAO_DE_OBRA("Conclusão de obra"),

    @XmlEnumValue("Concubinato")
    VCGE1_CONCUBINATO("Concubinato"),

    @XmlEnumValue("Concursos")
    VCGE1_CONCURSOS("Concursos"),

    @XmlEnumValue("Condicionantes dos licenciamentos")
    VCGE1_CONDICIONANTES_DOS_LICENCIAMENTOS("Condicionantes dos licenciamentos"),

    @XmlEnumValue("Condutas ambientalmente adequadas")
    VCGE1_CONDUTAS_AMBIENTALMENTE_ADEQUADAS("Condutas ambientalmente adequadas"),

    @XmlEnumValue("Conferência nacional de meio ambiente")
    VCGE1_CONFERENCIA_NACIONAL_DE_MEIO_AMBIENTE("Conferência nacional de meio ambiente"),

    @XmlEnumValue("Conferências")
    VCGE1_CONFERENCIAS("Conferências"),

    @XmlEnumValue("Conhecimento tradicional associado ao patrimônio genético (CTA)")
    VCGE1_CONHECIMENTO_TRADICIONAL_ASSOCIADO_AO_PATRIMONIO_GENETICO_CTA("Conhecimento tradicional associado ao patrimônio genético (CTA)"),

    @XmlEnumValue("Conselho nacional de educação")
    VCGE1_CONSELHO_NACIONAL_DE_EDUCACAO("Conselho nacional de educação"),

    @XmlEnumValue("Conselho nacional de recursos hídricos")
    VCGE1_CONSELHO_NACIONAL_DE_RECURSOS_HIDRICOS("Conselho nacional de recursos hídricos"),

    @XmlEnumValue("Conselhos")
    VCGE1_CONSELHOS("Conselhos"),

    @XmlEnumValue("Conselhos de justiça")
    VCGE1_CONSELHOS_DE_JUSTICA("Conselhos de justiça"),

    @XmlEnumValue("Conselhos de saúde")
    VCGE1_CONSELHOS_DE_SAUDE("Conselhos de saúde"),

    @XmlEnumValue("Conselhos e câmaras temáticas de turismo")
    VCGE1_CONSELHOS_E_CAMARAS_TEMATICAS_DE_TURISMO("Conselhos e câmaras temáticas de turismo"),

    @XmlEnumValue("Conselhos estaduais de recursos hídricos")
    VCGE1_CONSELHOS_ESTADUAIS_DE_RECURSOS_HIDRICOS("Conselhos estaduais de recursos hídricos"),

    @XmlEnumValue("Conservação da energia")
    VCGE1_CONSERVACAO_DA_ENERGIA("Conservação da energia"),

    @XmlEnumValue("Conservação de energia")
    VCGE1_CONSERVACAO_DE_ENERGIA("Conservação de energia"),

    @XmlEnumValue("Conservação de espécies ameçadas de extinção")
    VCGE1_CONSERVACAO_DE_ESPECIES_AMECADAS_DE_EXTINCAO("Conservação de espécies ameçadas de extinção"),

    @XmlEnumValue("Conservação de patrimônio genético")
    VCGE1_CONSERVACAO_DE_PATRIMONIO_GENETICO("Conservação de patrimônio genético"),

    @XmlEnumValue("Conservação e preservação de biomas")
    VCGE1_CONSERVACAO_E_PRESERVACAO_DE_BIOMAS("Conservação e preservação de biomas"),

    @XmlEnumValue("Consolidação territorial")
    VCGE1_CONSOLIDACAO_TERRITORIAL("Consolidação territorial"),

    @XmlEnumValue("Construção")
    VCGE1_CONSTRUCAO("Construção"),

    @XmlEnumValue("Construção civil")
    VCGE1_CONSTRUCAO_CIVIL("Construção civil"),

    @XmlEnumValue("Construção rural")
    VCGE1_CONSTRUCAO_RURAL("Construção rural"),

    @XmlEnumValue("Consulados e embaixadas")
    VCGE1_CONSULADOS_E_EMBAIXADAS("Consulados e embaixadas"),

    @XmlEnumValue("Consulta comercial")
    VCGE1_CONSULTA_COMERCIAL("Consulta comercial"),

    @XmlEnumValue("Consulta protocolo")
    VCGE1_CONSULTA_PROTOCOLO("Consulta protocolo"),

    @XmlEnumValue("Consulta publica")
    VCGE1_CONSULTA_PUBLICA("Consulta publica"),

    @XmlEnumValue("Consumo sustentável")
    VCGE1_CONSUMO_SUSTENTAVEL("Consumo sustentável"),

    @XmlEnumValue("Consórcios públicos")
    VCGE1_CONSORCIOS_PUBLICOS("Consórcios públicos"),

    @XmlEnumValue("Contaminação de alimentos")
    VCGE1_CONTAMINACAO_DE_ALIMENTOS("Contaminação de alimentos"),

    @XmlEnumValue("Contracheque")
    VCGE1_CONTRACHEQUE("Contracheque"),

    @XmlEnumValue("Contrato de trabalho")
    VCGE1_CONTRATO_DE_TRABALHO("Contrato de trabalho"),

    @XmlEnumValue("Contribuição")
    VCGE1_CONTRIBUICAO("Contribuição"),

    @XmlEnumValue("Contribuição previdenciária")
    VCGE1_CONTRIBUICAO_PREVIDENCIARIA("Contribuição previdenciária"),

    @XmlEnumValue("Controle das espécies exóticas invasoras")
    VCGE1_CONTROLE_DAS_ESPECIES_EXOTICAS_INVASORAS("Controle das espécies exóticas invasoras"),

    @XmlEnumValue("Controle social e prestação de contas")
    VCGE1_CONTROLE_SOCIAL_E_PRESTACAO_DE_CONTAS("Controle social e prestação de contas"),

    @XmlEnumValue("Convenção das nações unidas de combate à desertificação")
    VCGE1_CONVENCAO_DAS_NACOES_UNIDAS_DE_COMBATE_A_DESERTIFICACAO("Convenção das nações unidas de combate à desertificação"),

    @XmlEnumValue("Convenção de Viena e protocolo de Montreal")
    VCGE1_CONVENCAO_DE_VIENA_E_PROTOCOLO_DE_MONTREAL("Convenção de Viena e protocolo de Montreal"),

    @XmlEnumValue("Convenção internacional")
    VCGE1_CONVENCAO_INTERNACIONAL("Convenção internacional"),

    @XmlEnumValue("Convenção sobre diversidade biológica (CDB)")
    VCGE1_CONVENCAO_SOBRE_DIVERSIDADE_BIOLOGICA_CDB("Convenção sobre diversidade biológica (CDB)"),

    @XmlEnumValue("Convenção sobre mudança do clima")
    VCGE1_CONVENCAO_SOBRE_MUDANCA_DO_CLIMA("Convenção sobre mudança do clima"),

    @XmlEnumValue("Conversão de multas de sanções administrativas")
    VCGE1_CONVERSAO_DE_MULTAS_DE_SANCOES_ADMINISTRATIVAS("Conversão de multas de sanções administrativas"),

    @XmlEnumValue("Conversão tecnológica")
    VCGE1_CONVERSAO_TECNOLOGICA("Conversão tecnológica"),

    @XmlEnumValue("Cooperativa agrícola")
    VCGE1_COOPERATIVA_AGRICOLA("Cooperativa agrícola"),

    @XmlEnumValue("Cooperação acadêmica")
    VCGE1_COOPERACAO_ACADEMICA("Cooperação acadêmica"),

    @XmlEnumValue("Cooperação cientifica internacional")
    VCGE1_COOPERACAO_CIENTIFICA_INTERNACIONAL("Cooperação cientifica internacional"),

    @XmlEnumValue("Cooperação jurídica internacional")
    VCGE1_COOPERACAO_JURIDICA_INTERNACIONAL("Cooperação jurídica internacional"),

    @XmlEnumValue("Corpo de bombeiros")
    VCGE1_CORPO_DE_BOMBEIROS("Corpo de bombeiros"),

    @XmlEnumValue("Corredor central da amazônia (CCA)")
    VCGE1_CORREDOR_CENTRAL_DA_AMAZONIA_CCA("Corredor central da amazônia (CCA)"),

    @XmlEnumValue("Corredor central da mata atlântica (CCMA)")
    VCGE1_CORREDOR_CENTRAL_DA_MATA_ATLANTICA_CCMA("Corredor central da mata atlântica (CCMA)"),

    @XmlEnumValue("Corredores ecológicos")
    VCGE1_CORREDORES_ECOLOGICOS("Corredores ecológicos"),

    @XmlEnumValue("Creche")
    VCGE1_CRECHE("Creche"),

    @XmlEnumValue("Credenciamento de cursos")
    VCGE1_CREDENCIAMENTO_DE_CURSOS("Credenciamento de cursos"),

    @XmlEnumValue("Criação de florestas nacionais, estaduais e municipais")
    VCGE1_CRIACAO_DE_FLORESTAS_NACIONAIS_ESTADUAIS_E_MUNICIPAIS("Criação de florestas nacionais, estaduais e municipais"),

    @XmlEnumValue("Criação de unidades de conservação")
    VCGE1_CRIACAO_DE_UNIDADES_DE_CONSERVACAO("Criação de unidades de conservação"),

    @XmlEnumValue("Crimes ambientais")
    VCGE1_CRIMES_AMBIENTAIS("Crimes ambientais"),

    @XmlEnumValue("Crédito agrícola")
    VCGE1_CREDITO_AGRICOLA("Crédito agrícola"),

    @XmlEnumValue("Crédito e financiamento de exportação")
    VCGE1_CREDITO_E_FINANCIAMENTO_DE_EXPORTACAO("Crédito e financiamento de exportação"),

    @XmlEnumValue("Cultivo")
    VCGE1_CULTIVO("Cultivo"),

    @XmlEnumValue("Cultivo de patrimônio genético")
    VCGE1_CULTIVO_DE_PATRIMONIO_GENETICO("Cultivo de patrimônio genético"),

    @XmlEnumValue("Cultura")
    VCGE1_CULTURA("Cultura"),

    @XmlEnumValue("Cultura, Lazer e Esporte")
    VCGE1_CULTURA_LAZER_E_ESPORTE("Cultura, Lazer e Esporte"),

    @XmlEnumValue("Culturas étnicas")
    VCGE1_CULTURAS_ETNICAS("Culturas étnicas"),

    @XmlEnumValue("Curso normal superior")
    VCGE1_CURSO_NORMAL_SUPERIOR("Curso normal superior"),

    @XmlEnumValue("Curso seqüencial")
    VCGE1_CURSO_SEQUEENCIAL("Curso seqüencial"),

    @XmlEnumValue("Curta metragem")
    VCGE1_CURTA_METRAGEM("Curta metragem"),

    @XmlEnumValue("Custos de medicamentos")
    VCGE1_CUSTOS_DE_MEDICAMENTOS("Custos de medicamentos"),

    @XmlEnumValue("Cálculo da compensação ambiental")
    VCGE1_CALCULO_DA_COMPENSACAO_AMBIENTAL("Cálculo da compensação ambiental"),

    @XmlEnumValue("Câmara de compensação ambiental")
    VCGE1_CAMARA_DE_COMPENSACAO_AMBIENTAL("Câmara de compensação ambiental"),

    @XmlEnumValue("Câmbio")
    VCGE1_CAMBIO("Câmbio"),

    @XmlEnumValue("Código de conduta")
    VCGE1_CODIGO_DE_CONDUTA("Código de conduta"),

    @XmlEnumValue("Códigos voluntários")
    VCGE1_CODIGOS_VOLUNTARIOS("Códigos voluntários"),

    @XmlEnumValue("DST")
    VCGE1_DST("DST"),

    @XmlEnumValue("Dança")
    VCGE1_DANCA("Dança"),

    @XmlEnumValue("Declaração de localização de imóveis")
    VCGE1_DECLARACAO_DE_LOCALIZACAO_DE_IMOVEIS("Declaração de localização de imóveis"),

    @XmlEnumValue("Defesa Nacional")
    VCGE1_DEFESA_NACIONAL("Defesa Nacional"),

    @XmlEnumValue("Defesa Vegetal")
    VCGE1_DEFESA_VEGETAL("Defesa Vegetal"),

    @XmlEnumValue("Defesa animal")
    VCGE1_DEFESA_ANIMAL("Defesa animal"),

    @XmlEnumValue("Defesa civil")
    VCGE1_DEFESA_CIVIL("Defesa civil"),

    @XmlEnumValue("Defesa da ordem jurídica")
    VCGE1_DEFESA_DA_ORDEM_JURIDICA("Defesa da ordem jurídica"),

    @XmlEnumValue("Defesa do consumidor")
    VCGE1_DEFESA_DO_CONSUMIDOR("Defesa do consumidor"),

    @XmlEnumValue("Defesa do interesse publico")
    VCGE1_DEFESA_DO_INTERESSE_PUBLICO("Defesa do interesse publico"),

    @XmlEnumValue("Defesa e Segurança")
    VCGE1_DEFESA_E_SEGURANCA("Defesa e Segurança"),

    @XmlEnumValue("Defesa econômica e da concorrência")
    VCGE1_DEFESA_ECONOMICA_E_DA_CONCORRENCIA("Defesa econômica e da concorrência"),

    @XmlEnumValue("Degradação da terra nas áreas semi-áridas e subúmidas secas")
    VCGE1_DEGRADACAO_DA_TERRA_NAS_AREAS_SEMI_ARIDAS_E_SUBUMIDAS_SECAS("Degradação da terra nas áreas semi-áridas e subúmidas secas"),

    @XmlEnumValue("Degradação de habitats")
    VCGE1_DEGRADACAO_DE_HABITATS("Degradação de habitats"),

    @XmlEnumValue("Demanda de emprego")
    VCGE1_DEMANDA_DE_EMPREGO("Demanda de emprego"),

    @XmlEnumValue("Demanda de água")
    VCGE1_DEMANDA_DE_AGUA("Demanda de água"),

    @XmlEnumValue("Demolição")
    VCGE1_DEMOLICAO("Demolição"),

    @XmlEnumValue("Dengue")
    VCGE1_DENGUE("Dengue"),

    @XmlEnumValue("Denúncia")
    VCGE1_DENUNCIA("Denúncia"),

    @XmlEnumValue("Desemprego")
    VCGE1_DESEMPREGO("Desemprego"),

    @XmlEnumValue("Desenho")
    VCGE1_DESENHO("Desenho"),

    @XmlEnumValue("Desenvolvimento agrícola")
    VCGE1_DESENVOLVIMENTO_AGRICOLA("Desenvolvimento agrícola"),

    @XmlEnumValue("Desenvolvimento de bioprodutos")
    VCGE1_DESENVOLVIMENTO_DE_BIOPRODUTOS("Desenvolvimento de bioprodutos"),

    @XmlEnumValue("Desenvolvimento econômico")
    VCGE1_DESENVOLVIMENTO_ECONOMICO("Desenvolvimento econômico"),

    @XmlEnumValue("Desenvolvimento tecnológico")
    VCGE1_DESENVOLVIMENTO_TECNOLOGICO("Desenvolvimento tecnológico"),

    @XmlEnumValue("Desenvolvimento tecnológico de produtos")
    VCGE1_DESENVOLVIMENTO_TECNOLOGICO_DE_PRODUTOS("Desenvolvimento tecnológico de produtos"),

    @XmlEnumValue("Desenvolvimento, produção e uso sustentável")
    VCGE1_DESENVOLVIMENTO_PRODUCAO_E_USO_SUSTENTAVEL("Desenvolvimento, produção e uso sustentável"),

    @XmlEnumValue("Desertificação")
    VCGE1_DESERTIFICACAO("Desertificação"),

    @XmlEnumValue("Desmatamento")
    VCGE1_DESMATAMENTO("Desmatamento"),

    @XmlEnumValue("Desmatamento ilegal")
    VCGE1_DESMATAMENTO_ILEGAL("Desmatamento ilegal"),

    @XmlEnumValue("Desmatamento na Amazônia")
    VCGE1_DESMATAMENTO_NA_AMAZONIA("Desmatamento na Amazônia"),

    @XmlEnumValue("Desnutrição")
    VCGE1_DESNUTRICAO("Desnutrição"),

    @XmlEnumValue("Destinação de florestas públicas às comunidades locais")
    VCGE1_DESTINACAO_DE_FLORESTAS_PUBLICAS_AS_COMUNIDADES_LOCAIS("Destinação de florestas públicas às comunidades locais"),

    @XmlEnumValue("Detecção da exploração florestal (DETEX)")
    VCGE1_DETECCAO_DA_EXPLORACAO_FLORESTAL_DETEX("Detecção da exploração florestal (DETEX)"),

    @XmlEnumValue("Detecção do desmatamento em tempo real (DETER)")
    VCGE1_DETECCAO_DO_DESMATAMENTO_EM_TEMPO_REAL_DETER("Detecção do desmatamento em tempo real (DETER)"),

    @XmlEnumValue("Detecção do desmatamento em tempo real - DETER")
    VCGE1_DETECCAO_DO_DESMATAMENTO_EM_TEMPO_REAL_DETER_2("Detecção do desmatamento em tempo real - DETER"),

    @XmlEnumValue("Diploma educação superior")
    VCGE1_DIPLOMA_EDUCACAO_SUPERIOR("Diploma educação superior"),

    @XmlEnumValue("Direito autoral")
    VCGE1_DIREITO_AUTORAL("Direito autoral"),

    @XmlEnumValue("Direito dos usuários dos serviços de saúde")
    VCGE1_DIREITO_DOS_USUARIOS_DOS_SERVICOS_DE_SAUDE("Direito dos usuários dos serviços de saúde"),

    @XmlEnumValue("Direitos das comunidades tradicionais associado ao patrimônio genético")
    VCGE1_DIREITOS_DAS_COMUNIDADES_TRADICIONAIS_ASSOCIADO_AO_PATRIMONIO_GENETICO("Direitos das comunidades tradicionais associado ao patrimônio genético"),

    @XmlEnumValue("Direitos de acesso às florestas públicas e aos benefícios decorrentes")
    VCGE1_DIREITOS_DE_ACESSO_AS_FLORESTAS_PUBLICAS_E_AOS_BENEFICIOS_DECORRENTES("Direitos de acesso às florestas públicas e aos benefícios decorrentes"),

    @XmlEnumValue("Direitos e deveres individuais")
    VCGE1_DIREITOS_E_DEVERES_INDIVIDUAIS("Direitos e deveres individuais"),

    @XmlEnumValue("Direitos humanos")
    VCGE1_DIREITOS_HUMANOS("Direitos humanos"),

    @XmlEnumValue("Dispersão")
    VCGE1_DISPERSAO("Dispersão"),

    @XmlEnumValue("Disponibilidade de água")
    VCGE1_DISPONIBILIDADE_DE_AGUA("Disponibilidade de água"),

    @XmlEnumValue("Distúrbio vegetal")
    VCGE1_DISTURBIO_VEGETAL("Distúrbio vegetal"),

    @XmlEnumValue("Distúrbios de animais")
    VCGE1_DISTURBIOS_DE_ANIMAIS("Distúrbios de animais"),

    @XmlEnumValue("Divida externa")
    VCGE1_DIVIDA_EXTERNA("Divida externa"),

    @XmlEnumValue("Divida interna")
    VCGE1_DIVIDA_INTERNA("Divida interna"),

    @XmlEnumValue("Documentos de publicidade ao ar livre")
    VCGE1_DOCUMENTOS_DE_PUBLICIDADE_AO_AR_LIVRE("Documentos de publicidade ao ar livre"),

    @XmlEnumValue("Documentos e vistos")
    VCGE1_DOCUMENTOS_E_VISTOS("Documentos e vistos"),

    @XmlEnumValue("Documentário")
    VCGE1_DOCUMENTARIO("Documentário"),

    @XmlEnumValue("Doença animal")
    VCGE1_DOENCA_ANIMAL("Doença animal"),

    @XmlEnumValue("Doença de planta")
    VCGE1_DOENCA_DE_PLANTA("Doença de planta"),

    @XmlEnumValue("Doenças de veiculação hídrica")
    VCGE1_DOENCAS_DE_VEICULACAO_HIDRICA("Doenças de veiculação hídrica"),

    @XmlEnumValue("Doutorado")
    VCGE1_DOUTORADO("Doutorado"),

    @XmlEnumValue("Drenagem pluvial")
    VCGE1_DRENAGEM_PLUVIAL("Drenagem pluvial"),

    @XmlEnumValue("Ecomercados e negócios sustentáveis")
    VCGE1_ECOMERCADOS_E_NEGOCIOS_SUSTENTAVEIS("Ecomercados e negócios sustentáveis"),

    @XmlEnumValue("Economia")
    VCGE1_ECONOMIA("Economia"),

    @XmlEnumValue("Economia agrícola")
    VCGE1_ECONOMIA_AGRICOLA("Economia agrícola"),

    @XmlEnumValue("Economia e Finanças")
    VCGE1_ECONOMIA_E_FINANCAS("Economia e Finanças"),

    @XmlEnumValue("Economia e meio ambiente")
    VCGE1_ECONOMIA_E_MEIO_AMBIENTE("Economia e meio ambiente"),

    @XmlEnumValue("Economia pesqueira")
    VCGE1_ECONOMIA_PESQUEIRA("Economia pesqueira"),

    @XmlEnumValue("Ecossistemas aquáticos")
    VCGE1_ECOSSISTEMAS_AQUATICOS("Ecossistemas aquáticos"),

    @XmlEnumValue("Ecossistemas aquáticos degradados")
    VCGE1_ECOSSISTEMAS_AQUATICOS_DEGRADADOS("Ecossistemas aquáticos degradados"),

    @XmlEnumValue("Ecossistemas aquáticos e terrestres")
    VCGE1_ECOSSISTEMAS_AQUATICOS_E_TERRESTRES("Ecossistemas aquáticos e terrestres"),

    @XmlEnumValue("Ecossistemas costeiros")
    VCGE1_ECOSSISTEMAS_COSTEIROS("Ecossistemas costeiros"),

    @XmlEnumValue("Ecossistemas terrestres")
    VCGE1_ECOSSISTEMAS_TERRESTRES("Ecossistemas terrestres"),

    @XmlEnumValue("Ecoturismo")
    VCGE1_ECOTURISMO("Ecoturismo"),

    @XmlEnumValue("Educação")
    VCGE1_EDUCACAO("Educação"),

    @XmlEnumValue("Educação ambiental")
    VCGE1_EDUCACAO_AMBIENTAL("Educação ambiental"),

    @XmlEnumValue("Educação básica")
    VCGE1_EDUCACAO_BASICA("Educação básica"),

    @XmlEnumValue("Educação continuada")
    VCGE1_EDUCACAO_CONTINUADA("Educação continuada"),

    @XmlEnumValue("Educação de jovens e adultos")
    VCGE1_EDUCACAO_DE_JOVENS_E_ADULTOS("Educação de jovens e adultos"),

    @XmlEnumValue("Educação do campo")
    VCGE1_EDUCACAO_DO_CAMPO("Educação do campo"),

    @XmlEnumValue("Educação do preso")
    VCGE1_EDUCACAO_DO_PRESO("Educação do preso"),

    @XmlEnumValue("Educação em reciclagem")
    VCGE1_EDUCACAO_EM_RECICLAGEM("Educação em reciclagem"),

    @XmlEnumValue("Educação indígena")
    VCGE1_EDUCACAO_INDIGENA("Educação indígena"),

    @XmlEnumValue("Educação infantil")
    VCGE1_EDUCACAO_INFANTIL("Educação infantil"),

    @XmlEnumValue("Educação no trânsito")
    VCGE1_EDUCACAO_NO_TRANSITO("Educação no trânsito"),

    @XmlEnumValue("Educação para a saúde")
    VCGE1_EDUCACAO_PARA_A_SAUDE("Educação para a saúde"),

    @XmlEnumValue("Educação para quilombolas")
    VCGE1_EDUCACAO_PARA_QUILOMBOLAS("Educação para quilombolas"),

    @XmlEnumValue("Educação profissional e tecnológica")
    VCGE1_EDUCACAO_PROFISSIONAL_E_TECNOLOGICA("Educação profissional e tecnológica"),

    @XmlEnumValue("Educação profissional tecnológica de graduação e pós-graduação")
    VCGE1_EDUCACAO_PROFISSIONAL_TECNOLOGICA_DE_GRADUACAO_E_POS_GRADUACAO("Educação profissional tecnológica de graduação e pós-graduação"),

    @XmlEnumValue("Educação profissional técnica de nível médio")
    VCGE1_EDUCACAO_PROFISSIONAL_TECNICA_DE_NIVEL_MEDIO("Educação profissional técnica de nível médio"),

    @XmlEnumValue("Educação superior")
    VCGE1_EDUCACAO_SUPERIOR("Educação superior"),

    @XmlEnumValue("Educação à distância")
    VCGE1_EDUCACAO_A_DISTANCIA("Educação à distância"),

    @XmlEnumValue("Efeito estufa")
    VCGE1_EFEITO_ESTUFA("Efeito estufa"),

    @XmlEnumValue("Eficiência energética")
    VCGE1_EFICIENCIA_ENERGETICA("Eficiência energética"),

    @XmlEnumValue("Eletrificação rural")
    VCGE1_ELETRIFICACAO_RURAL("Eletrificação rural"),

    @XmlEnumValue("Embalagem de alimentos")
    VCGE1_EMBALAGEM_DE_ALIMENTOS("Embalagem de alimentos"),

    @XmlEnumValue("Emergências ambientais")
    VCGE1_EMERGENCIAS_AMBIENTAIS("Emergências ambientais"),

    @XmlEnumValue("Emergências ambientais urbanas")
    VCGE1_EMERGENCIAS_AMBIENTAIS_URBANAS("Emergências ambientais urbanas"),

    @XmlEnumValue("Emergências e Urgências")
    VCGE1_EMERGENCIAS_E_URGENCIAS("Emergências e Urgências"),

    @XmlEnumValue("Empreendimentos hidrelétricos")
    VCGE1_EMPREENDIMENTOS_HIDRELETRICOS("Empreendimentos hidrelétricos"),

    @XmlEnumValue("Empreendimentos potencialmente causadores de impacto ambiental")
    VCGE1_EMPREENDIMENTOS_POTENCIALMENTE_CAUSADORES_DE_IMPACTO_AMBIENTAL("Empreendimentos potencialmente causadores de impacto ambiental"),

    @XmlEnumValue("Emprego")
    VCGE1_EMPREGO("Emprego"),

    @XmlEnumValue("Empresas")
    VCGE1_EMPRESAS("Empresas"),

    @XmlEnumValue("Encargos financeiros")
    VCGE1_ENCARGOS_FINANCEIROS("Encargos financeiros"),

    @XmlEnumValue("Encargos financeiros especiais")
    VCGE1_ENCARGOS_FINANCEIROS_ESPECIAIS("Encargos financeiros especiais"),

    @XmlEnumValue("Energia e meio ambiente")
    VCGE1_ENERGIA_E_MEIO_AMBIENTE("Energia e meio ambiente"),

    @XmlEnumValue("Energia elétrica")
    VCGE1_ENERGIA_ELETRICA("Energia elétrica"),

    @XmlEnumValue("Energia nuclear")
    VCGE1_ENERGIA_NUCLEAR("Energia nuclear"),

    @XmlEnumValue("Energia não renováveis")
    VCGE1_ENERGIA_NAO_RENOVAVEIS("Energia não renováveis"),

    @XmlEnumValue("Energias renováveis")
    VCGE1_ENERGIAS_RENOVAVEIS("Energias renováveis"),

    @XmlEnumValue("Energio nuclear")
    VCGE1_ENERGIO_NUCLEAR("Energio nuclear"),

    @XmlEnumValue("Engenharia")
    VCGE1_ENGENHARIA("Engenharia"),

    @XmlEnumValue("Engenharia agrícola")
    VCGE1_ENGENHARIA_AGRICOLA("Engenharia agrícola"),

    @XmlEnumValue("Enquadramento dos corpos de água em classes de uso")
    VCGE1_ENQUADRAMENTO_DOS_CORPOS_DE_AGUA_EM_CLASSES_DE_USO("Enquadramento dos corpos de água em classes de uso"),

    @XmlEnumValue("Ensino fundamental")
    VCGE1_ENSINO_FUNDAMENTAL("Ensino fundamental"),

    @XmlEnumValue("Ensino fundamental de 8 anos")
    VCGE1_ENSINO_FUNDAMENTAL_DE_8_ANOS("Ensino fundamental de 8 anos"),

    @XmlEnumValue("Ensino fundamental de 9 anos")
    VCGE1_ENSINO_FUNDAMENTAL_DE_9_ANOS("Ensino fundamental de 9 anos"),

    @XmlEnumValue("Ensino médio")
    VCGE1_ENSINO_MEDIO("Ensino médio"),

    @XmlEnumValue("Entidade representativa de classe")
    VCGE1_ENTIDADE_REPRESENTATIVA_DE_CLASSE("Entidade representativa de classe"),

    @XmlEnumValue("Entidades ambientalistas")
    VCGE1_ENTIDADES_AMBIENTALISTAS("Entidades ambientalistas"),

    @XmlEnumValue("Entidades de assistência")
    VCGE1_ENTIDADES_DE_ASSISTENCIA("Entidades de assistência"),

    @XmlEnumValue("Entidades de interesse publico")
    VCGE1_ENTIDADES_DE_INTERESSE_PUBLICO("Entidades de interesse publico"),

    @XmlEnumValue("Entidades representativas do comércio e serviços")
    VCGE1_ENTIDADES_REPRESENTATIVAS_DO_COMERCIO_E_SERVICOS("Entidades representativas do comércio e serviços"),

    @XmlEnumValue("Entorno das unidades de conservação")
    VCGE1_ENTORNO_DAS_UNIDADES_DE_CONSERVACAO("Entorno das unidades de conservação"),

    @XmlEnumValue("Equideocultura")
    VCGE1_EQUIDEOCULTURA("Equideocultura"),

    @XmlEnumValue("Equipamento agrícola")
    VCGE1_EQUIPAMENTO_AGRICOLA("Equipamento agrícola"),

    @XmlEnumValue("Equipamentos de pesca")
    VCGE1_EQUIPAMENTOS_DE_PESCA("Equipamentos de pesca"),

    @XmlEnumValue("Equipamentos médico-hospitalares")
    VCGE1_EQUIPAMENTOS_MEDICO_HOSPITALARES("Equipamentos médico-hospitalares"),

    @XmlEnumValue("Erosão do solo")
    VCGE1_EROSAO_DO_SOLO("Erosão do solo"),

    @XmlEnumValue("Erva daninha")
    VCGE1_ERVA_DANINHA("Erva daninha"),

    @XmlEnumValue("Escultura")
    VCGE1_ESCULTURA("Escultura"),

    @XmlEnumValue("Esgoto sanitário")
    VCGE1_ESGOTO_SANITARIO("Esgoto sanitário"),

    @XmlEnumValue("Espaços litorâneos")
    VCGE1_ESPACOS_LITORANEOS("Espaços litorâneos"),

    @XmlEnumValue("Especialista em educação")
    VCGE1_ESPECIALISTA_EM_EDUCACAO("Especialista em educação"),

    @XmlEnumValue("Especialização")
    VCGE1_ESPECIALIZACAO("Especialização"),

    @XmlEnumValue("Especificação do projeto de publicidade")
    VCGE1_ESPECIFICACAO_DO_PROJETO_DE_PUBLICIDADE("Especificação do projeto de publicidade"),

    @XmlEnumValue("Esporte")
    VCGE1_ESPORTE("Esporte"),

    @XmlEnumValue("Esporte comunitário")
    VCGE1_ESPORTE_COMUNITARIO("Esporte comunitário"),

    @XmlEnumValue("Esporte de rendimento")
    VCGE1_ESPORTE_DE_RENDIMENTO("Esporte de rendimento"),

    @XmlEnumValue("Espécies ameaçadas")
    VCGE1_ESPECIES_AMEACADAS("Espécies ameaçadas"),

    @XmlEnumValue("Espécies ameaçadas de extinção")
    VCGE1_ESPECIES_AMEACADAS_DE_EXTINCAO("Espécies ameaçadas de extinção"),

    @XmlEnumValue("Espécies de intercâmbio facilitado")
    VCGE1_ESPECIES_DE_INTERCAMBIO_FACILITADO("Espécies de intercâmbio facilitado"),

    @XmlEnumValue("Espécies de valor econômico atual ou potencial")
    VCGE1_ESPECIES_DE_VALOR_ECONOMICO_ATUAL_OU_POTENCIAL("Espécies de valor econômico atual ou potencial"),

    @XmlEnumValue("Espécies dependentes de conservação")
    VCGE1_ESPECIES_DEPENDENTES_DE_CONSERVACAO("Espécies dependentes de conservação"),

    @XmlEnumValue("Espécies endêmicas")
    VCGE1_ESPECIES_ENDEMICAS("Espécies endêmicas"),

    @XmlEnumValue("Espécies exóticas invasoras")
    VCGE1_ESPECIES_EXOTICAS_INVASORAS("Espécies exóticas invasoras"),

    @XmlEnumValue("Espécies vulneráveis")
    VCGE1_ESPECIES_VULNERAVEIS("Espécies vulneráveis"),

    @XmlEnumValue("Estagio")
    VCGE1_ESTAGIO("Estagio"),

    @XmlEnumValue("Estatuto da criança e do adolescente")
    VCGE1_ESTATUTO_DA_CRIANCA_E_DO_ADOLESCENTE("Estatuto da criança e do adolescente"),

    @XmlEnumValue("Estatuto do idoso")
    VCGE1_ESTATUTO_DO_IDOSO("Estatuto do idoso"),

    @XmlEnumValue("Estatuto do índio")
    VCGE1_ESTATUTO_DO_INDIO("Estatuto do índio"),

    @XmlEnumValue("Estatísticas de trânsito")
    VCGE1_ESTATISTICAS_DE_TRANSITO("Estatísticas de trânsito"),

    @XmlEnumValue("Estatísticas educacionais")
    VCGE1_ESTATISTICAS_EDUCACIONAIS("Estatísticas educacionais"),

    @XmlEnumValue("Estatísticas em saúde")
    VCGE1_ESTATISTICAS_EM_SAUDE("Estatísticas em saúde"),

    @XmlEnumValue("Estações ecológicas")
    VCGE1_ESTACOES_ECOLOGICAS("Estações ecológicas"),

    @XmlEnumValue("Estocagem")
    VCGE1_ESTOCAGEM("Estocagem"),

    @XmlEnumValue("Estoque regulador")
    VCGE1_ESTOQUE_REGULADOR("Estoque regulador"),

    @XmlEnumValue("Estradas")
    VCGE1_ESTRADAS("Estradas"),

    @XmlEnumValue("Estrangeiro")
    VCGE1_ESTRANGEIRO("Estrangeiro"),

    @XmlEnumValue("Estrutiocultura")
    VCGE1_ESTRUTIOCULTURA("Estrutiocultura"),

    @XmlEnumValue("Estrutura agrária")
    VCGE1_ESTRUTURA_AGRARIA("Estrutura agrária"),

    @XmlEnumValue("Estrutura animal")
    VCGE1_ESTRUTURA_ANIMAL("Estrutura animal"),

    @XmlEnumValue("Estrutura vegetal")
    VCGE1_ESTRUTURA_VEGETAL("Estrutura vegetal"),

    @XmlEnumValue("Estruturas educadoras integradas")
    VCGE1_ESTRUTURAS_EDUCADORAS_INTEGRADAS("Estruturas educadoras integradas"),

    @XmlEnumValue("Estudo de impacto ambiental - EIA")
    VCGE1_ESTUDO_DE_IMPACTO_AMBIENTAL_EIA("Estudo de impacto ambiental - EIA"),

    @XmlEnumValue("Estudos hidrometeorológicos")
    VCGE1_ESTUDOS_HIDROMETEOROLOGICOS("Estudos hidrometeorológicos"),

    @XmlEnumValue("Estímulo à investimentos de longo prazo em florestas públicas")
    VCGE1_ESTIMULO_A_INVESTIMENTOS_DE_LONGO_PRAZO_EM_FLORESTAS_PUBLICAS("Estímulo à investimentos de longo prazo em florestas públicas"),

    @XmlEnumValue("Eventos críticos (secas e enchentes)")
    VCGE1_EVENTOS_CRITICOS_SECAS_E_ENCHENTES("Eventos críticos (secas e enchentes)"),

    @XmlEnumValue("Eventos e negócios")
    VCGE1_EVENTOS_E_NEGOCIOS("Eventos e negócios"),

    @XmlEnumValue("Execução das ações de caráter permanente")
    VCGE1_EXECUCAO_DAS_ACOES_DE_CARATER_PERMANENTE("Execução das ações de caráter permanente"),

    @XmlEnumValue("Execução orçamentária e financeira")
    VCGE1_EXECUCAO_ORCAMENTARIA_E_FINANCEIRA("Execução orçamentária e financeira"),

    @XmlEnumValue("Execução penal")
    VCGE1_EXECUCAO_PENAL("Execução penal"),

    @XmlEnumValue("Exploração de florestas e formações sucessoras")
    VCGE1_EXPLORACAO_DE_FLORESTAS_E_FORMACOES_SUCESSORAS("Exploração de florestas e formações sucessoras"),

    @XmlEnumValue("Exploração de satélite")
    VCGE1_EXPLORACAO_DE_SATELITE("Exploração de satélite"),

    @XmlEnumValue("Exploração de serviços florestais")
    VCGE1_EXPLORACAO_DE_SERVICOS_FLORESTAIS("Exploração de serviços florestais"),

    @XmlEnumValue("Exploração econômica de recursos naturais")
    VCGE1_EXPLORACAO_ECONOMICA_DE_RECURSOS_NATURAIS("Exploração econômica de recursos naturais"),

    @XmlEnumValue("Exploração espacial")
    VCGE1_EXPLORACAO_ESPACIAL("Exploração espacial"),

    @XmlEnumValue("Exploração ilegal de madeira")
    VCGE1_EXPLORACAO_ILEGAL_DE_MADEIRA("Exploração ilegal de madeira"),

    @XmlEnumValue("Exportação")
    VCGE1_EXPORTACAO("Exportação"),

    @XmlEnumValue("Exportação de serviços")
    VCGE1_EXPORTACAO_DE_SERVICOS("Exportação de serviços"),

    @XmlEnumValue("Exposição")
    VCGE1_EXPOSICAO("Exposição"),

    @XmlEnumValue("Extensão rural")
    VCGE1_EXTENSAO_RURAL("Extensão rural"),

    @XmlEnumValue("Extradição")
    VCGE1_EXTRADICAO("Extradição"),

    @XmlEnumValue("Extrativismo")
    VCGE1_EXTRATIVISMO("Extrativismo"),

    @XmlEnumValue("Extrativismo animal")
    VCGE1_EXTRATIVISMO_ANIMAL("Extrativismo animal"),

    @XmlEnumValue("Extrativismo vegetal")
    VCGE1_EXTRATIVISMO_VEGETAL("Extrativismo vegetal"),

    @XmlEnumValue("Exército")
    VCGE1_EXERCITO("Exército"),

    @XmlEnumValue("Eólica")
    VCGE1_EOLICA("Eólica"),

    @XmlEnumValue("Facultativa")
    VCGE1_FACULTATIVA("Facultativa"),

    @XmlEnumValue("Família")
    VCGE1_FAMILIA("Família"),

    @XmlEnumValue("Farmácia")
    VCGE1_FARMACIA("Farmácia"),

    @XmlEnumValue("Farmácia básica")
    VCGE1_FARMACIA_BASICA("Farmácia básica"),

    @XmlEnumValue("Farmácia hospitalar")
    VCGE1_FARMACIA_HOSPITALAR("Farmácia hospitalar"),

    @XmlEnumValue("Farmácia popular")
    VCGE1_FARMACIA_POPULAR("Farmácia popular"),

    @XmlEnumValue("Fauna ameçada de extinção")
    VCGE1_FAUNA_AMECADA_DE_EXTINCAO("Fauna ameçada de extinção"),

    @XmlEnumValue("Feira")
    VCGE1_FEIRA("Feira"),

    @XmlEnumValue("Feiras de negócios")
    VCGE1_FEIRAS_DE_NEGOCIOS("Feiras de negócios"),

    @XmlEnumValue("Feiras iivres")
    VCGE1_FEIRAS_IIVRES("Feiras iivres"),

    @XmlEnumValue("Feriados")
    VCGE1_FERIADOS("Feriados"),

    @XmlEnumValue("Fertilizantes e agrotóxicos")
    VCGE1_FERTILIZANTES_E_AGROTOXICOS("Fertilizantes e agrotóxicos"),

    @XmlEnumValue("Fibra vegetal")
    VCGE1_FIBRA_VEGETAL("Fibra vegetal"),

    @XmlEnumValue("Financiamento da educação")
    VCGE1_FINANCIAMENTO_DA_EDUCACAO("Financiamento da educação"),

    @XmlEnumValue("Financiamento estudantil")
    VCGE1_FINANCIAMENTO_ESTUDANTIL("Financiamento estudantil"),

    @XmlEnumValue("Financiamento habitacional")
    VCGE1_FINANCIAMENTO_HABITACIONAL("Financiamento habitacional"),

    @XmlEnumValue("Financiamento publico e privado")
    VCGE1_FINANCIAMENTO_PUBLICO_E_PRIVADO("Financiamento publico e privado"),

    @XmlEnumValue("Finanças")
    VCGE1_FINANCAS("Finanças"),

    @XmlEnumValue("Finanças pessoais")
    VCGE1_FINANCAS_PESSOAIS("Finanças pessoais"),

    @XmlEnumValue("Finanças públicas")
    VCGE1_FINANCAS_PUBLICAS("Finanças públicas"),

    @XmlEnumValue("Fiscalização (audiovisual)")
    VCGE1_FISCALIZACAO_AUDIOVISUAL("Fiscalização (audiovisual)"),

    @XmlEnumValue("Fiscalização animal")
    VCGE1_FISCALIZACAO_ANIMAL("Fiscalização animal"),

    @XmlEnumValue("Fiscalização de transporte aquaviário")
    VCGE1_FISCALIZACAO_DE_TRANSPORTE_AQUAVIARIO("Fiscalização de transporte aquaviário"),

    @XmlEnumValue("Fiscalização de transporte ferroviário")
    VCGE1_FISCALIZACAO_DE_TRANSPORTE_FERROVIARIO("Fiscalização de transporte ferroviário"),

    @XmlEnumValue("Fiscalização de transporte rodoviário")
    VCGE1_FISCALIZACAO_DE_TRANSPORTE_RODOVIARIO("Fiscalização de transporte rodoviário"),

    @XmlEnumValue("Fiscalização do estado")
    VCGE1_FISCALIZACAO_DO_ESTADO("Fiscalização do estado"),

    @XmlEnumValue("Fiscalização do trabalho")
    VCGE1_FISCALIZACAO_DO_TRABALHO("Fiscalização do trabalho"),

    @XmlEnumValue("Fiscalização vegetal")
    VCGE1_FISCALIZACAO_VEGETAL("Fiscalização vegetal"),

    @XmlEnumValue("Fisiologia animal")
    VCGE1_FISIOLOGIA_ANIMAL("Fisiologia animal"),

    @XmlEnumValue("Fisiologia vegetal")
    VCGE1_FISIOLOGIA_VEGETAL("Fisiologia vegetal"),

    @XmlEnumValue("Fitoterapia")
    VCGE1_FITOTERAPIA("Fitoterapia"),

    @XmlEnumValue("Flora ameçada de extinção")
    VCGE1_FLORA_AMECADA_DE_EXTINCAO("Flora ameçada de extinção"),

    @XmlEnumValue("Floresta amazônica")
    VCGE1_FLORESTA_AMAZONICA("Floresta amazônica"),

    @XmlEnumValue("Florestas")
    VCGE1_FLORESTAS("Florestas"),

    @XmlEnumValue("Florestas nacionais")
    VCGE1_FLORESTAS_NACIONAIS("Florestas nacionais"),

    @XmlEnumValue("Florestas nativas")
    VCGE1_FLORESTAS_NATIVAS("Florestas nativas"),

    @XmlEnumValue("Florestas naturais")
    VCGE1_FLORESTAS_NATURAIS("Florestas naturais"),

    @XmlEnumValue("Florestas plantadas")
    VCGE1_FLORESTAS_PLANTADAS("Florestas plantadas"),

    @XmlEnumValue("Florestas públicas")
    VCGE1_FLORESTAS_PUBLICAS("Florestas públicas"),

    @XmlEnumValue("Floricultura")
    VCGE1_FLORICULTURA("Floricultura"),

    @XmlEnumValue("Folclore e cultura popular")
    VCGE1_FOLCLORE_E_CULTURA_POPULAR("Folclore e cultura popular"),

    @XmlEnumValue("Fomento")
    VCGE1_FOMENTO("Fomento"),

    @XmlEnumValue("Fomento (audiovisual)")
    VCGE1_FOMENTO_AUDIOVISUAL("Fomento (audiovisual)"),

    @XmlEnumValue("Fomento a pós-graduação")
    VCGE1_FOMENTO_A_POS_GRADUACAO("Fomento a pós-graduação"),

    @XmlEnumValue("Fomento ao desenvolvimento sustentável")
    VCGE1_FOMENTO_AO_DESENVOLVIMENTO_SUSTENTAVEL("Fomento ao desenvolvimento sustentável"),

    @XmlEnumValue("Fomento à geração do conhecimento")
    VCGE1_FOMENTO_A_GERACAO_DO_CONHECIMENTO("Fomento à geração do conhecimento"),

    @XmlEnumValue("Fomento à produção sustentável")
    VCGE1_FOMENTO_A_PRODUCAO_SUSTENTAVEL("Fomento à produção sustentável"),

    @XmlEnumValue("Formas de união")
    VCGE1_FORMAS_DE_UNIAO("Formas de união"),

    @XmlEnumValue("Formação cientifica")
    VCGE1_FORMACAO_CIENTIFICA("Formação cientifica"),

    @XmlEnumValue("Formação de pessoal penal e penitenciário")
    VCGE1_FORMACAO_DE_PESSOAL_PENAL_E_PENITENCIARIO("Formação de pessoal penal e penitenciário"),

    @XmlEnumValue("Formação de recursos humanos em segurança publica")
    VCGE1_FORMACAO_DE_RECURSOS_HUMANOS_EM_SEGURANCA_PUBLICA("Formação de recursos humanos em segurança publica"),

    @XmlEnumValue("Formação inicial e continuada de trabalhadores")
    VCGE1_FORMACAO_INICIAL_E_CONTINUADA_DE_TRABALHADORES("Formação inicial e continuada de trabalhadores"),

    @XmlEnumValue("Forragem")
    VCGE1_FORRAGEM("Forragem"),

    @XmlEnumValue("Fotografia")
    VCGE1_FOTOGRAFIA("Fotografia"),

    @XmlEnumValue("Fragmentação")
    VCGE1_FRAGMENTACAO("Fragmentação"),

    @XmlEnumValue("Fragmentação de florestas")
    VCGE1_FRAGMENTACAO_DE_FLORESTAS("Fragmentação de florestas"),

    @XmlEnumValue("Fruticultura")
    VCGE1_FRUTICULTURA("Fruticultura"),

    @XmlEnumValue("Fundação")
    VCGE1_FUNDACAO("Fundação"),

    @XmlEnumValue("Fundação de apoio - pós-graduação")
    VCGE1_FUNDACAO_DE_APOIO_POS_GRADUACAO("Fundação de apoio - pós-graduação"),

    @XmlEnumValue("Fórum intergovernamental")
    VCGE1_FORUM_INTERGOVERNAMENTAL("Fórum intergovernamental"),

    @XmlEnumValue("Gasodutos")
    VCGE1_GASODUTOS("Gasodutos"),

    @XmlEnumValue("Geotérmica")
    VCGE1_GEOTERMICA("Geotérmica"),

    @XmlEnumValue("Geração de energia hidrelétrica")
    VCGE1_GERACAO_DE_ENERGIA_HIDRELETRICA("Geração de energia hidrelétrica"),

    @XmlEnumValue("Geração de renda nas unidades de conservação")
    VCGE1_GERACAO_DE_RENDA_NAS_UNIDADES_DE_CONSERVACAO("Geração de renda nas unidades de conservação"),

    @XmlEnumValue("Gerenciamento costeiro e marinho")
    VCGE1_GERENCIAMENTO_COSTEIRO_E_MARINHO("Gerenciamento costeiro e marinho"),

    @XmlEnumValue("Gerenciamento de substancias e resíduos")
    VCGE1_GERENCIAMENTO_DE_SUBSTANCIAS_E_RESIDUOS("Gerenciamento de substancias e resíduos"),

    @XmlEnumValue("Gerenciamento de áreas contaminadas")
    VCGE1_GERENCIAMENTO_DE_AREAS_CONTAMINADAS("Gerenciamento de áreas contaminadas"),

    @XmlEnumValue("Gerenciamento do passivo das substâncias destruidoras da camada de ozônio")
    VCGE1_GERENCIAMENTO_DO_PASSIVO_DAS_SUBSTANCIAS_DESTRUIDORAS_DA_CAMADA_DE_OZONIO("Gerenciamento do passivo das substâncias destruidoras da camada de ozônio"),

    @XmlEnumValue("Gestão ambiental compartilhada")
    VCGE1_GESTAO_AMBIENTAL_COMPARTILHADA("Gestão ambiental compartilhada"),

    @XmlEnumValue("Gestão da educação em saúde")
    VCGE1_GESTAO_DA_EDUCACAO_EM_SAUDE("Gestão da educação em saúde"),

    @XmlEnumValue("Gestão da produção agrária")
    VCGE1_GESTAO_DA_PRODUCAO_AGRARIA("Gestão da produção agrária"),

    @XmlEnumValue("Gestão da água")
    VCGE1_GESTAO_DA_AGUA("Gestão da água"),

    @XmlEnumValue("Gestão das unidades de conservação")
    VCGE1_GESTAO_DAS_UNIDADES_DE_CONSERVACAO("Gestão das unidades de conservação"),

    @XmlEnumValue("Gestão de conflitos da atividade minerária")
    VCGE1_GESTAO_DE_CONFLITOS_DA_ATIVIDADE_MINERARIA("Gestão de conflitos da atividade minerária"),

    @XmlEnumValue("Gestão de florestas públicas")
    VCGE1_GESTAO_DE_FLORESTAS_PUBLICAS("Gestão de florestas públicas"),

    @XmlEnumValue("Gestão de meio ambiente")
    VCGE1_GESTAO_DE_MEIO_AMBIENTE("Gestão de meio ambiente"),

    @XmlEnumValue("Gestão de resíduos sólidos")
    VCGE1_GESTAO_DE_RESIDUOS_SOLIDOS("Gestão de resíduos sólidos"),

    @XmlEnumValue("Gestão de áreas protegidas")
    VCGE1_GESTAO_DE_AREAS_PROTEGIDAS("Gestão de áreas protegidas"),

    @XmlEnumValue("Gestão do trabalho em saúde")
    VCGE1_GESTAO_DO_TRABALHO_EM_SAUDE("Gestão do trabalho em saúde"),

    @XmlEnumValue("Gestão dos recursos hídricos")
    VCGE1_GESTAO_DOS_RECURSOS_HIDRICOS("Gestão dos recursos hídricos"),

    @XmlEnumValue("Gestão dos recursos pesqueiros")
    VCGE1_GESTAO_DOS_RECURSOS_PESQUEIROS("Gestão dos recursos pesqueiros"),

    @XmlEnumValue("Gestão escolar")
    VCGE1_GESTAO_ESCOLAR("Gestão escolar"),

    @XmlEnumValue("Gestão florestal compartilhada")
    VCGE1_GESTAO_FLORESTAL_COMPARTILHADA("Gestão florestal compartilhada"),

    @XmlEnumValue("Gestão integrada de resíduos sólidos urbanos")
    VCGE1_GESTAO_INTEGRADA_DE_RESIDUOS_SOLIDOS_URBANOS("Gestão integrada de resíduos sólidos urbanos"),

    @XmlEnumValue("Gestão internacional das substâncias químicas - SAICM")
    VCGE1_GESTAO_INTERNACIONAL_DAS_SUBSTANCIAS_QUIMICAS_SAICM("Gestão internacional das substâncias químicas - SAICM"),

    @XmlEnumValue("Goma")
    VCGE1_GOMA("Goma"),

    @XmlEnumValue("Governo e Política")
    VCGE1_GOVERNO_E_POLITICA("Governo e Política"),

    @XmlEnumValue("Governo eletrônico")
    VCGE1_GOVERNO_ELETRONICO("Governo eletrônico"),

    @XmlEnumValue("Governo estadual")
    VCGE1_GOVERNO_ESTADUAL("Governo estadual"),

    @XmlEnumValue("Governo federal")
    VCGE1_GOVERNO_FEDERAL("Governo federal"),

    @XmlEnumValue("Governo municipal")
    VCGE1_GOVERNO_MUNICIPAL("Governo municipal"),

    @XmlEnumValue("Governo móvel")
    VCGE1_GOVERNO_MOVEL("Governo móvel"),

    @XmlEnumValue("Graduação")
    VCGE1_GRADUACAO("Graduação"),

    @XmlEnumValue("Gravura")
    VCGE1_GRAVURA("Gravura"),

    @XmlEnumValue("Gripe")
    VCGE1_GRIPE("Gripe"),

    @XmlEnumValue("Grupos e organismos políticos internacionais")
    VCGE1_GRUPOS_E_ORGANISMOS_POLITICOS_INTERNACIONAIS("Grupos e organismos políticos internacionais"),

    @XmlEnumValue("Gás")
    VCGE1_GAS("Gás"),

    @XmlEnumValue("Gás natural")
    VCGE1_GAS_NATURAL("Gás natural"),

    @XmlEnumValue("Habitats naturais")
    VCGE1_HABITATS_NATURAIS("Habitats naturais"),

    @XmlEnumValue("Habitação")
    VCGE1_HABITACAO("Habitação"),

    @XmlEnumValue("Habitação, Saneamento e Urbanismo")
    VCGE1_HABITACAO_SANEAMENTO_E_URBANISMO("Habitação, Saneamento e Urbanismo"),

    @XmlEnumValue("Hardware")
    VCGE1_HARDWARE("Hardware"),

    @XmlEnumValue("Herbários")
    VCGE1_HERBARIOS("Herbários"),

    @XmlEnumValue("Hidrovias interiores")
    VCGE1_HIDROVIAS_INTERIORES("Hidrovias interiores"),

    @XmlEnumValue("Higiene veterinária")
    VCGE1_HIGIENE_VETERINARIA("Higiene veterinária"),

    @XmlEnumValue("Homeopatia")
    VCGE1_HOMEOPATIA("Homeopatia"),

    @XmlEnumValue("Horticultura")
    VCGE1_HORTICULTURA("Horticultura"),

    @XmlEnumValue("Horário ônibus")
    VCGE1_HORARIO_ONIBUS("Horário ônibus"),

    @XmlEnumValue("Hospedagem")
    VCGE1_HOSPEDAGEM("Hospedagem"),

    @XmlEnumValue("Hospitais universitários")
    VCGE1_HOSPITAIS_UNIVERSITARIOS("Hospitais universitários"),

    @XmlEnumValue("Hospital")
    VCGE1_HOSPITAL("Hospital"),

    @XmlEnumValue("Hospital de custódia")
    VCGE1_HOSPITAL_DE_CUSTODIA("Hospital de custódia"),

    @XmlEnumValue("Humanização na saúde")
    VCGE1_HUMANIZACAO_NA_SAUDE("Humanização na saúde"),

    @XmlEnumValue("Hídrica")
    VCGE1_HIDRICA("Hídrica"),

    @XmlEnumValue("Identificação criminal")
    VCGE1_IDENTIFICACAO_CRIMINAL("Identificação criminal"),

    @XmlEnumValue("Identificação pessoal")
    VCGE1_IDENTIFICACAO_PESSOAL("Identificação pessoal"),

    @XmlEnumValue("Identificação taxonômica de patrimônio genético")
    VCGE1_IDENTIFICACAO_TAXONOMICA_DE_PATRIMONIO_GENETICO("Identificação taxonômica de patrimônio genético"),

    @XmlEnumValue("Igualdade de direitos do estrangeiro")
    VCGE1_IGUALDADE_DE_DIREITOS_DO_ESTRANGEIRO("Igualdade de direitos do estrangeiro"),

    @XmlEnumValue("Ilhas, parceis e atóis")
    VCGE1_ILHAS_PARCEIS_E_ATOIS("Ilhas, parceis e atóis"),

    @XmlEnumValue("Iluminação pública")
    VCGE1_ILUMINACAO_PUBLICA("Iluminação pública"),

    @XmlEnumValue("Ilícitos associados")
    VCGE1_ILICITOS_ASSOCIADOS("Ilícitos associados"),

    @XmlEnumValue("Imigrantes")
    VCGE1_IMIGRANTES("Imigrantes"),

    @XmlEnumValue("Implementação da agenda 21")
    VCGE1_IMPLEMENTACAO_DA_AGENDA_21("Implementação da agenda 21"),

    @XmlEnumValue("Importação")
    VCGE1_IMPORTACAO("Importação"),

    @XmlEnumValue("Importação de serviços")
    VCGE1_IMPORTACAO_DE_SERVICOS("Importação de serviços"),

    @XmlEnumValue("Imposto")
    VCGE1_IMPOSTO("Imposto"),

    @XmlEnumValue("Imprensa")
    VCGE1_IMPRENSA("Imprensa"),

    @XmlEnumValue("Imóveis")
    VCGE1_IMOVEIS("Imóveis"),

    @XmlEnumValue("Incentivos e promoções do turismo")
    VCGE1_INCENTIVOS_E_PROMOCOES_DO_TURISMO("Incentivos e promoções do turismo"),

    @XmlEnumValue("Incentivos fiscais, tributários e creditícios")
    VCGE1_INCENTIVOS_FISCAIS_TRIBUTARIOS_E_CREDITICIOS("Incentivos fiscais, tributários e creditícios"),

    @XmlEnumValue("Incentivos à produção sustentável")
    VCGE1_INCENTIVOS_A_PRODUCAO_SUSTENTAVEL("Incentivos à produção sustentável"),

    @XmlEnumValue("Indicadores")
    VCGE1_INDICADORES("Indicadores"),

    @XmlEnumValue("Indicadores ambientais")
    VCGE1_INDICADORES_AMBIENTAIS("Indicadores ambientais"),

    @XmlEnumValue("Indicadores e estatísticas em comércio e serviços")
    VCGE1_INDICADORES_E_ESTATISTICAS_EM_COMERCIO_E_SERVICOS("Indicadores e estatísticas em comércio e serviços"),

    @XmlEnumValue("Indicadores educacionais")
    VCGE1_INDICADORES_EDUCACIONAIS("Indicadores educacionais"),

    @XmlEnumValue("Indígena")
    VCGE1_INDIGENA("Indígena"),

    @XmlEnumValue("Indústria")
    VCGE1_INDUSTRIA("Indústria"),

    @XmlEnumValue("Indústria alimentar")
    VCGE1_INDUSTRIA_ALIMENTAR("Indústria alimentar"),

    @XmlEnumValue("Indústria automobilística")
    VCGE1_INDUSTRIA_AUTOMOBILISTICA("Indústria automobilística"),

    @XmlEnumValue("Indústria cerâmica")
    VCGE1_INDUSTRIA_CERAMICA("Indústria cerâmica"),

    @XmlEnumValue("Indústria couro-calçadista")
    VCGE1_INDUSTRIA_COURO_CALCADISTA("Indústria couro-calçadista"),

    @XmlEnumValue("Indústria cultural")
    VCGE1_INDUSTRIA_CULTURAL("Indústria cultural"),

    @XmlEnumValue("Indústria de arma e munição")
    VCGE1_INDUSTRIA_DE_ARMA_E_MUNICAO("Indústria de arma e munição"),

    @XmlEnumValue("Indústria de celulose e papel")
    VCGE1_INDUSTRIA_DE_CELULOSE_E_PAPEL("Indústria de celulose e papel"),

    @XmlEnumValue("Indústria de equipamentos médico-hospitalares")
    VCGE1_INDUSTRIA_DE_EQUIPAMENTOS_MEDICO_HOSPITALARES("Indústria de equipamentos médico-hospitalares"),

    @XmlEnumValue("Indústria eletro-eletrônica")
    VCGE1_INDUSTRIA_ELETRO_ELETRONICA("Indústria eletro-eletrônica"),

    @XmlEnumValue("Indústria extrativa")
    VCGE1_INDUSTRIA_EXTRATIVA("Indústria extrativa"),

    @XmlEnumValue("Indústria farmacêutica")
    VCGE1_INDUSTRIA_FARMACEUTICA("Indústria farmacêutica"),

    @XmlEnumValue("Indústria madeireira e movelaria")
    VCGE1_INDUSTRIA_MADEIREIRA_E_MOVELARIA("Indústria madeireira e movelaria"),

    @XmlEnumValue("Indústria mecânica e metalúrgica")
    VCGE1_INDUSTRIA_MECANICA_E_METALURGICA("Indústria mecânica e metalúrgica"),

    @XmlEnumValue("Indústria naval")
    VCGE1_INDUSTRIA_NAVAL("Indústria naval"),

    @XmlEnumValue("Indústria pesqueira")
    VCGE1_INDUSTRIA_PESQUEIRA("Indústria pesqueira"),

    @XmlEnumValue("Indústria química e petroquímica")
    VCGE1_INDUSTRIA_QUIMICA_E_PETROQUIMICA("Indústria química e petroquímica"),

    @XmlEnumValue("Indústria sucroalcooleira")
    VCGE1_INDUSTRIA_SUCROALCOOLEIRA("Indústria sucroalcooleira"),

    @XmlEnumValue("Indústria têxteis")
    VCGE1_INDUSTRIA_TEXTEIS("Indústria têxteis"),

    @XmlEnumValue("Infecção hospitalar")
    VCGE1_INFECCAO_HOSPITALAR("Infecção hospitalar"),

    @XmlEnumValue("Informação - Gestão, preservação e acesso")
    VCGE1_INFORMACAO_GESTAO_PRESERVACAO_E_ACESSO("Informação - Gestão, preservação e acesso"),

    @XmlEnumValue("Informações estatísticas")
    VCGE1_INFORMACOES_ESTATISTICAS("Informações estatísticas"),

    @XmlEnumValue("Informações geográficas")
    VCGE1_INFORMACOES_GEOGRAFICAS("Informações geográficas"),

    @XmlEnumValue("Informações sobre o país")
    VCGE1_INFORMACOES_SOBRE_O_PAIS("Informações sobre o país"),

    @XmlEnumValue("Informações sociais")
    VCGE1_INFORMACOES_SOCIAIS("Informações sociais"),

    @XmlEnumValue("Infraestrutura de transporte aquaviário")
    VCGE1_INFRAESTRUTURA_DE_TRANSPORTE_AQUAVIARIO("Infraestrutura de transporte aquaviário"),

    @XmlEnumValue("Infraestrutura de transporte ferroviário")
    VCGE1_INFRAESTRUTURA_DE_TRANSPORTE_FERROVIARIO("Infraestrutura de transporte ferroviário"),

    @XmlEnumValue("Infraestrutura de transporte rodoviário")
    VCGE1_INFRAESTRUTURA_DE_TRANSPORTE_RODOVIARIO("Infraestrutura de transporte rodoviário"),

    @XmlEnumValue("Infraestrutura de turismo")
    VCGE1_INFRAESTRUTURA_DE_TURISMO("Infraestrutura de turismo"),

    @XmlEnumValue("Infraestrutura material e ambiental")
    VCGE1_INFRAESTRUTURA_MATERIAL_E_AMBIENTAL("Infraestrutura material e ambiental"),

    @XmlEnumValue("Infraestrutura para conservação e preservação de biomas")
    VCGE1_INFRAESTRUTURA_PARA_CONSERVACAO_E_PRESERVACAO_DE_BIOMAS("Infraestrutura para conservação e preservação de biomas"),

    @XmlEnumValue("Infrações ambientais")
    VCGE1_INFRACOES_AMBIENTAIS("Infrações ambientais"),

    @XmlEnumValue("Ingresso no ensino superior")
    VCGE1_INGRESSO_NO_ENSINO_SUPERIOR("Ingresso no ensino superior"),

    @XmlEnumValue("Inovação tecnológica")
    VCGE1_INOVACAO_TECNOLOGICA("Inovação tecnológica"),

    @XmlEnumValue("Inseminação artificial")
    VCGE1_INSEMINACAO_ARTIFICIAL("Inseminação artificial"),

    @XmlEnumValue("Instituições federais de ensino superior")
    VCGE1_INSTITUICOES_FEDERAIS_DE_ENSINO_SUPERIOR("Instituições federais de ensino superior"),

    @XmlEnumValue("Instituições fiéis depositárias")
    VCGE1_INSTITUICOES_FIEIS_DEPOSITARIAS("Instituições fiéis depositárias"),

    @XmlEnumValue("Instrumentos de planejamento e gestão ambiental")
    VCGE1_INSTRUMENTOS_DE_PLANEJAMENTO_E_GESTAO_AMBIENTAL("Instrumentos de planejamento e gestão ambiental"),

    @XmlEnumValue("Instrumentos econômicos")
    VCGE1_INSTRUMENTOS_ECONOMICOS("Instrumentos econômicos"),

    @XmlEnumValue("Instrumentos para gestão de recursos hídricos")
    VCGE1_INSTRUMENTOS_PARA_GESTAO_DE_RECURSOS_HIDRICOS("Instrumentos para gestão de recursos hídricos"),

    @XmlEnumValue("Insumos agrícolas")
    VCGE1_INSUMOS_AGRICOLAS("Insumos agrícolas"),

    @XmlEnumValue("Integração do meio ambiente e produção")
    VCGE1_INTEGRACAO_DO_MEIO_AMBIENTE_E_PRODUCAO("Integração do meio ambiente e produção"),

    @XmlEnumValue("Inteligência artificial")
    VCGE1_INTELIGENCIA_ARTIFICIAL("Inteligência artificial"),

    @XmlEnumValue("Internet")
    VCGE1_INTERNET("Internet"),

    @XmlEnumValue("Invasões biológicas")
    VCGE1_INVASOES_BIOLOGICAS("Invasões biológicas"),

    @XmlEnumValue("Inventário floresta nacional")
    VCGE1_INVENTARIO_FLORESTA_NACIONAL("Inventário floresta nacional"),

    @XmlEnumValue("Investidores interessados na área ambiental")
    VCGE1_INVESTIDORES_INTERESSADOS_NA_AREA_AMBIENTAL("Investidores interessados na área ambiental"),

    @XmlEnumValue("Investimento")
    VCGE1_INVESTIMENTO("Investimento"),

    @XmlEnumValue("Irrigação e drenagem")
    VCGE1_IRRIGACAO_E_DRENAGEM("Irrigação e drenagem"),

    @XmlEnumValue("Isenção tarifária")
    VCGE1_ISENCAO_TARIFARIA("Isenção tarifária"),

    @XmlEnumValue("Itinerários transportes coletivos")
    VCGE1_ITINERARIOS_TRANSPORTES_COLETIVOS("Itinerários transportes coletivos"),

    @XmlEnumValue("Jardins botânicos")
    VCGE1_JARDINS_BOTANICOS("Jardins botânicos"),

    @XmlEnumValue("Jogos e loterias")
    VCGE1_JOGOS_E_LOTERIAS("Jogos e loterias"),

    @XmlEnumValue("Jurisprudência")
    VCGE1_JURISPRUDENCIA("Jurisprudência"),

    @XmlEnumValue("Justiça")
    VCGE1_JUSTICA("Justiça"),

    @XmlEnumValue("Justiça e Legislação")
    VCGE1_JUSTICA_E_LEGISLACAO("Justiça e Legislação"),

    @XmlEnumValue("Latu sensu")
    VCGE1_LATU_SENSU("Latu sensu"),

    @XmlEnumValue("Lazer")
    VCGE1_LAZER("Lazer"),

    @XmlEnumValue("Legislação")
    VCGE1_LEGISLACAO("Legislação"),

    @XmlEnumValue("Legislação (cultura)")
    VCGE1_LEGISLACAO_CULTURA("Legislação (cultura)"),

    @XmlEnumValue("Legislação de publicidade ao ar livre")
    VCGE1_LEGISLACAO_DE_PUBLICIDADE_AO_AR_LIVRE("Legislação de publicidade ao ar livre"),

    @XmlEnumValue("Legislação de saúde")
    VCGE1_LEGISLACAO_DE_SAUDE("Legislação de saúde"),

    @XmlEnumValue("Legislação e jurisprudência")
    VCGE1_LEGISLACAO_E_JURISPRUDENCIA("Legislação e jurisprudência"),

    @XmlEnumValue("Legislação e normas")
    VCGE1_LEGISLACAO_E_NORMAS("Legislação e normas"),

    @XmlEnumValue("Legislação educacional")
    VCGE1_LEGISLACAO_EDUCACIONAL("Legislação educacional"),

    @XmlEnumValue("Legislação estadual")
    VCGE1_LEGISLACAO_ESTADUAL("Legislação estadual"),

    @XmlEnumValue("Legislação federal")
    VCGE1_LEGISLACAO_FEDERAL("Legislação federal"),

    @XmlEnumValue("Legislação internacional")
    VCGE1_LEGISLACAO_INTERNACIONAL("Legislação internacional"),

    @XmlEnumValue("Legislação municipal")
    VCGE1_LEGISLACAO_MUNICIPAL("Legislação municipal"),

    @XmlEnumValue("Legislação trabalhista")
    VCGE1_LEGISLACAO_TRABALHISTA("Legislação trabalhista"),

    @XmlEnumValue("Leis de diretrizes e bases da educação nacional")
    VCGE1_LEIS_DE_DIRETRIZES_E_BASES_DA_EDUCACAO_NACIONAL("Leis de diretrizes e bases da educação nacional"),

    @XmlEnumValue("Licenciamento ambiental")
    VCGE1_LICENCIAMENTO_AMBIENTAL("Licenciamento ambiental"),

    @XmlEnumValue("Licenciamentos especiais")
    VCGE1_LICENCIAMENTOS_ESPECIAIS("Licenciamentos especiais"),

    @XmlEnumValue("Licenciatura")
    VCGE1_LICENCIATURA("Licenciatura"),

    @XmlEnumValue("Licenciatura plena")
    VCGE1_LICENCIATURA_PLENA("Licenciatura plena"),

    @XmlEnumValue("Licença de instalação")
    VCGE1_LICENCA_DE_INSTALACAO("Licença de instalação"),

    @XmlEnumValue("Licença de operação")
    VCGE1_LICENCA_DE_OPERACAO("Licença de operação"),

    @XmlEnumValue("Licença prévia")
    VCGE1_LICENCA_PREVIA("Licença prévia"),

    @XmlEnumValue("Licenças ambientais")
    VCGE1_LICENCAS_AMBIENTAIS("Licenças ambientais"),

    @XmlEnumValue("Licitação")
    VCGE1_LICITACAO("Licitação"),

    @XmlEnumValue("Licitações públicas sustentáveis")
    VCGE1_LICITACOES_PUBLICAS_SUSTENTAVEIS("Licitações públicas sustentáveis"),

    @XmlEnumValue("Limpeza urbana")
    VCGE1_LIMPEZA_URBANA("Limpeza urbana"),

    @XmlEnumValue("Literatura")
    VCGE1_LITERATURA("Literatura"),

    @XmlEnumValue("Livro didático")
    VCGE1_LIVRO_DIDATICO("Livro didático"),

    @XmlEnumValue("Lixo tóxico")
    VCGE1_LIXO_TOXICO("Lixo tóxico"),

    @XmlEnumValue("Logística e transporte de exportação")
    VCGE1_LOGISTICA_E_TRANSPORTE_DE_EXPORTACAO("Logística e transporte de exportação"),

    @XmlEnumValue("Longa metragem")
    VCGE1_LONGA_METRAGEM("Longa metragem"),

    @XmlEnumValue("Luz")
    VCGE1_LUZ("Luz"),

    @XmlEnumValue("Látex")
    VCGE1_LATEX("Látex"),

    @XmlEnumValue("Maciços florestais urbanos")
    VCGE1_MACICOS_FLORESTAIS_URBANOS("Maciços florestais urbanos"),

    @XmlEnumValue("Madeira")
    VCGE1_MADEIRA("Madeira"),

    @XmlEnumValue("Magistério")
    VCGE1_MAGISTERIO("Magistério"),

    @XmlEnumValue("Mananciais")
    VCGE1_MANANCIAIS("Mananciais"),

    @XmlEnumValue("Manejo alternativo de animais crioulos e silvestres")
    VCGE1_MANEJO_ALTERNATIVO_DE_ANIMAIS_CRIOULOS_E_SILVESTRES("Manejo alternativo de animais crioulos e silvestres"),

    @XmlEnumValue("Manejo da agrobiodiversidade")
    VCGE1_MANEJO_DA_AGROBIODIVERSIDADE("Manejo da agrobiodiversidade"),

    @XmlEnumValue("Manejo da biodiversidade")
    VCGE1_MANEJO_DA_BIODIVERSIDADE("Manejo da biodiversidade"),

    @XmlEnumValue("Manejo de espécies ameçadas de extinção")
    VCGE1_MANEJO_DE_ESPECIES_AMECADAS_DE_EXTINCAO("Manejo de espécies ameçadas de extinção"),

    @XmlEnumValue("Manejo de florestas comunitárias")
    VCGE1_MANEJO_DE_FLORESTAS_COMUNITARIAS("Manejo de florestas comunitárias"),

    @XmlEnumValue("Manejo e conservação")
    VCGE1_MANEJO_E_CONSERVACAO("Manejo e conservação"),

    @XmlEnumValue("Manejo florestal sustentável")
    VCGE1_MANEJO_FLORESTAL_SUSTENTAVEL("Manejo florestal sustentável"),

    @XmlEnumValue("Manejo nas unidades de conservação")
    VCGE1_MANEJO_NAS_UNIDADES_DE_CONSERVACAO("Manejo nas unidades de conservação"),

    @XmlEnumValue("Manejo sustentável de animais silvestres")
    VCGE1_MANEJO_SUSTENTAVEL_DE_ANIMAIS_SILVESTRES("Manejo sustentável de animais silvestres"),

    @XmlEnumValue("Manejo sustentável de espécies da flora")
    VCGE1_MANEJO_SUSTENTAVEL_DE_ESPECIES_DA_FLORA("Manejo sustentável de espécies da flora"),

    @XmlEnumValue("Mangue")
    VCGE1_MANGUE("Mangue"),

    @XmlEnumValue("Mapas")
    VCGE1_MAPAS("Mapas"),

    @XmlEnumValue("Mapas georreferenciados")
    VCGE1_MAPAS_GEORREFERENCIADOS("Mapas georreferenciados"),

    @XmlEnumValue("Maremotriz")
    VCGE1_MAREMOTRIZ("Maremotriz"),

    @XmlEnumValue("Marinha")
    VCGE1_MARINHA("Marinha"),

    @XmlEnumValue("Marinha mercante")
    VCGE1_MARINHA_MERCANTE("Marinha mercante"),

    @XmlEnumValue("Master business administration")
    VCGE1_MASTER_BUSINESS_ADMINISTRATION("Master business administration"),

    @XmlEnumValue("Mata atlântica")
    VCGE1_MATA_ATLANTICA("Mata atlântica"),

    @XmlEnumValue("Materiais perigosos")
    VCGE1_MATERIAIS_PERIGOSOS("Materiais perigosos"),

    @XmlEnumValue("Material didático")
    VCGE1_MATERIAL_DIDATICO("Material didático"),

    @XmlEnumValue("Matriz energética ambientalmente adequada")
    VCGE1_MATRIZ_ENERGETICA_AMBIENTALMENTE_ADEQUADA("Matriz energética ambientalmente adequada"),

    @XmlEnumValue("Mecanismo de desenvolvimento limpo (MDL)")
    VCGE1_MECANISMO_DE_DESENVOLVIMENTO_LIMPO_MDL("Mecanismo de desenvolvimento limpo (MDL)"),

    @XmlEnumValue("Mecanismo de desenvolvimento limpo - MDL")
    VCGE1_MECANISMO_DE_DESENVOLVIMENTO_LIMPO_MDL_2("Mecanismo de desenvolvimento limpo - MDL"),

    @XmlEnumValue("Mecanização agrícola")
    VCGE1_MECANIZACAO_AGRICOLA("Mecanização agrícola"),

    @XmlEnumValue("Medicamentos")
    VCGE1_MEDICAMENTOS("Medicamentos"),

    @XmlEnumValue("Medicamentos essenciais")
    VCGE1_MEDICAMENTOS_ESSENCIAIS("Medicamentos essenciais"),

    @XmlEnumValue("Medicamentos fitoterápicos")
    VCGE1_MEDICAMENTOS_FITOTERAPICOS("Medicamentos fitoterápicos"),

    @XmlEnumValue("Medicamentos fracionados")
    VCGE1_MEDICAMENTOS_FRACIONADOS("Medicamentos fracionados"),

    @XmlEnumValue("Medicamentos genéricos")
    VCGE1_MEDICAMENTOS_GENERICOS("Medicamentos genéricos"),

    @XmlEnumValue("Medicamentos proibidos")
    VCGE1_MEDICAMENTOS_PROIBIDOS("Medicamentos proibidos"),

    @XmlEnumValue("Medicamentos similares")
    VCGE1_MEDICAMENTOS_SIMILARES("Medicamentos similares"),

    @XmlEnumValue("Medicina legal")
    VCGE1_MEDICINA_LEGAL("Medicina legal"),

    @XmlEnumValue("Medicina natural")
    VCGE1_MEDICINA_NATURAL("Medicina natural"),

    @XmlEnumValue("Medidas preventivas, mitigadoras e compensatórias")
    VCGE1_MEDIDAS_PREVENTIVAS_MITIGADORAS_E_COMPENSATORIAS("Medidas preventivas, mitigadoras e compensatórias"),

    @XmlEnumValue("Meio ambiente")
    VCGE1_MEIO_AMBIENTE("Meio ambiente"),

    @XmlEnumValue("Meios de acesso")
    VCGE1_MEIOS_DE_ACESSO("Meios de acesso"),

    @XmlEnumValue("Meios de ensino-aprendizagem")
    VCGE1_MEIOS_DE_ENSINO_APRENDIZAGEM("Meios de ensino-aprendizagem"),

    @XmlEnumValue("Melhoramento genético animal")
    VCGE1_MELHORAMENTO_GENETICO_ANIMAL("Melhoramento genético animal"),

    @XmlEnumValue("Melhoramento genético vegetal")
    VCGE1_MELHORAMENTO_GENETICO_VEGETAL("Melhoramento genético vegetal"),

    @XmlEnumValue("Mercado de capitais")
    VCGE1_MERCADO_DE_CAPITAIS("Mercado de capitais"),

    @XmlEnumValue("Mercado de carbono")
    VCGE1_MERCADO_DE_CARBONO("Mercado de carbono"),

    @XmlEnumValue("Mercado de trabalho")
    VCGE1_MERCADO_DE_TRABALHO("Mercado de trabalho"),

    @XmlEnumValue("Mercado municipal")
    VCGE1_MERCADO_MUNICIPAL("Mercado municipal"),

    @XmlEnumValue("Mercosul e integração regional")
    VCGE1_MERCOSUL_E_INTEGRACAO_REGIONAL("Mercosul e integração regional"),

    @XmlEnumValue("Merenda escolar")
    VCGE1_MERENDA_ESCOLAR("Merenda escolar"),

    @XmlEnumValue("Mestrado")
    VCGE1_MESTRADO("Mestrado"),

    @XmlEnumValue("Mestrado acadêmico")
    VCGE1_MESTRADO_ACADEMICO("Mestrado acadêmico"),

    @XmlEnumValue("Mestrado profissional")
    VCGE1_MESTRADO_PROFISSIONAL("Mestrado profissional"),

    @XmlEnumValue("Metrô")
    VCGE1_METRO("Metrô"),

    @XmlEnumValue("Minerodutos")
    VCGE1_MINERODUTOS("Minerodutos"),

    @XmlEnumValue("Minimização de rejeitos")
    VCGE1_MINIMIZACAO_DE_REJEITOS("Minimização de rejeitos"),

    @XmlEnumValue("Minimização dos efeitos de secas e inundações")
    VCGE1_MINIMIZACAO_DOS_EFEITOS_DE_SECAS_E_INUNDACOES("Minimização dos efeitos de secas e inundações"),

    @XmlEnumValue("Minimização dos impactos gerados por resíduos perigosos")
    VCGE1_MINIMIZACAO_DOS_IMPACTOS_GERADOS_POR_RESIDUOS_PERIGOSOS("Minimização dos impactos gerados por resíduos perigosos"),

    @XmlEnumValue("Minissérie")
    VCGE1_MINISSERIE("Minissérie"),

    @XmlEnumValue("Ministério público")
    VCGE1_MINISTERIO_PUBLICO("Ministério público"),

    @XmlEnumValue("Minorias")
    VCGE1_MINORIAS("Minorias"),

    @XmlEnumValue("Mitigação da mudança do clima")
    VCGE1_MITIGACAO_DA_MUDANCA_DO_CLIMA("Mitigação da mudança do clima"),

    @XmlEnumValue("Mitigação dos efeitos das secas")
    VCGE1_MITIGACAO_DOS_EFEITOS_DAS_SECAS("Mitigação dos efeitos das secas"),

    @XmlEnumValue("Modernização dos órgãos de justiça e estabelecimentos penais")
    VCGE1_MODERNIZACAO_DOS_ORGAOS_DE_JUSTICA_E_ESTABELECIMENTOS_PENAIS("Modernização dos órgãos de justiça e estabelecimentos penais"),

    @XmlEnumValue("Modernização e aparelhamento dos órgãos de segurança pública")
    VCGE1_MODERNIZACAO_E_APARELHAMENTO_DOS_ORGAOS_DE_SEGURANCA_PUBLICA("Modernização e aparelhamento dos órgãos de segurança pública"),

    @XmlEnumValue("Moeda")
    VCGE1_MOEDA("Moeda"),

    @XmlEnumValue("Moedas e cambio")
    VCGE1_MOEDAS_E_CAMBIO("Moedas e cambio"),

    @XmlEnumValue("Monitoramento")
    VCGE1_MONITORAMENTO("Monitoramento"),

    @XmlEnumValue("Monitoramento da cobertura vegetal")
    VCGE1_MONITORAMENTO_DA_COBERTURA_VEGETAL("Monitoramento da cobertura vegetal"),

    @XmlEnumValue("Monitoramento do meio ambiente")
    VCGE1_MONITORAMENTO_DO_MEIO_AMBIENTE("Monitoramento do meio ambiente"),

    @XmlEnumValue("Monitoramento e proteção das florestas")
    VCGE1_MONITORAMENTO_E_PROTECAO_DAS_FLORESTAS("Monitoramento e proteção das florestas"),

    @XmlEnumValue("Monopólios e fusões")
    VCGE1_MONOPOLIOS_E_FUSOES("Monopólios e fusões"),

    @XmlEnumValue("Monumentos naturais")
    VCGE1_MONUMENTOS_NATURAIS("Monumentos naturais"),

    @XmlEnumValue("Mortalidade infantil")
    VCGE1_MORTALIDADE_INFANTIL("Mortalidade infantil"),

    @XmlEnumValue("Mortalidade materna")
    VCGE1_MORTALIDADE_MATERNA("Mortalidade materna"),

    @XmlEnumValue("Moto frete")
    VCGE1_MOTO_FRETE("Moto frete"),

    @XmlEnumValue("Movimento transfronteiriços")
    VCGE1_MOVIMENTO_TRANSFRONTEIRICOS("Movimento transfronteiriços"),

    @XmlEnumValue("Mudanças climáticas")
    VCGE1_MUDANCAS_CLIMATICAS("Mudanças climáticas"),

    @XmlEnumValue("Multas / infrações")
    VCGE1_MULTAS_INFRACOES("Multas / infrações"),

    @XmlEnumValue("Multimeios didáticos")
    VCGE1_MULTIMEIOS_DIDATICOS("Multimeios didáticos"),

    @XmlEnumValue("Multimídia")
    VCGE1_MULTIMIDIA("Multimídia"),

    @XmlEnumValue("Museu do meio ambiente")
    VCGE1_MUSEU_DO_MEIO_AMBIENTE("Museu do meio ambiente"),

    @XmlEnumValue("Museus")
    VCGE1_MUSEUS("Museus"),

    @XmlEnumValue("Média metragem")
    VCGE1_MEDIA_METRAGEM("Média metragem"),

    @XmlEnumValue("Métodos de aprendizagem")
    VCGE1_METODOS_DE_APRENDIZAGEM("Métodos de aprendizagem"),

    @XmlEnumValue("Métodos de ensino")
    VCGE1_METODOS_DE_ENSINO("Métodos de ensino"),

    @XmlEnumValue("Métodos e meios de ensino e aprendizagem")
    VCGE1_METODOS_E_MEIOS_DE_ENSINO_E_APRENDIZAGEM("Métodos e meios de ensino e aprendizagem"),

    @XmlEnumValue("Móvel aeronáutico")
    VCGE1_MOVEL_AERONAUTICO("Móvel aeronáutico"),

    @XmlEnumValue("Móvel celular")
    VCGE1_MOVEL_CELULAR("Móvel celular"),

    @XmlEnumValue("Móvel especial de radiochamada")
    VCGE1_MOVEL_ESPECIAL_DE_RADIOCHAMADA("Móvel especial de radiochamada"),

    @XmlEnumValue("Móvel especializado")
    VCGE1_MOVEL_ESPECIALIZADO("Móvel especializado"),

    @XmlEnumValue("Móvel marítimo")
    VCGE1_MOVEL_MARITIMO("Móvel marítimo"),

    @XmlEnumValue("Música")
    VCGE1_MUSICA("Música"),

    @XmlEnumValue("Nacionalização/privatização")
    VCGE1_NACIONALIZACAO_PRIVATIZACAO("Nacionalização/privatização"),

    @XmlEnumValue("Nanociência")
    VCGE1_NANOCIENCIA("Nanociência"),

    @XmlEnumValue("Nanomaterial")
    VCGE1_NANOMATERIAL("Nanomaterial"),

    @XmlEnumValue("Nanomedicina")
    VCGE1_NANOMEDICINA("Nanomedicina"),

    @XmlEnumValue("Nanotecnologia")
    VCGE1_NANOTECNOLOGIA("Nanotecnologia"),

    @XmlEnumValue("Naturalização")
    VCGE1_NATURALIZACAO("Naturalização"),

    @XmlEnumValue("Negociação internacional")
    VCGE1_NEGOCIACAO_INTERNACIONAL("Negociação internacional"),

    @XmlEnumValue("Normalização e qualidade")
    VCGE1_NORMALIZACAO_E_QUALIDADE("Normalização e qualidade"),

    @XmlEnumValue("Novela")
    VCGE1_NOVELA("Novela"),

    @XmlEnumValue("Nutrição animal")
    VCGE1_NUTRICAO_ANIMAL("Nutrição animal"),

    @XmlEnumValue("Não nativas")
    VCGE1_NAO_NATIVAS("Não nativas"),

    @XmlEnumValue("Obesidade")
    VCGE1_OBESIDADE("Obesidade"),

    @XmlEnumValue("Obra audiovisual")
    VCGE1_OBRA_AUDIOVISUAL("Obra audiovisual"),

    @XmlEnumValue("Obra audiovisual publicitária")
    VCGE1_OBRA_AUDIOVISUAL_PUBLICITARIA("Obra audiovisual publicitária"),

    @XmlEnumValue("Obras")
    VCGE1_OBRAS("Obras"),

    @XmlEnumValue("Ocupação e uso do solo")
    VCGE1_OCUPACAO_E_USO_DO_SOLO("Ocupação e uso do solo"),

    @XmlEnumValue("Oferta de água")
    VCGE1_OFERTA_DE_AGUA("Oferta de água"),

    @XmlEnumValue("Oleodutos")
    VCGE1_OLEODUTOS("Oleodutos"),

    @XmlEnumValue("Operador de transporte multimodal")
    VCGE1_OPERADOR_DE_TRANSPORTE_MULTIMODAL("Operador de transporte multimodal"),

    @XmlEnumValue("Oportunidades de emprego")
    VCGE1_OPORTUNIDADES_DE_EMPREGO("Oportunidades de emprego"),

    @XmlEnumValue("Ordenamento do uso dos recurso pesqueiros")
    VCGE1_ORDENAMENTO_DO_USO_DOS_RECURSO_PESQUEIROS("Ordenamento do uso dos recurso pesqueiros"),

    @XmlEnumValue("Ordenamento territorial")
    VCGE1_ORDENAMENTO_TERRITORIAL("Ordenamento territorial"),

    @XmlEnumValue("Organismos geneticamente modificados (OGM)")
    VCGE1_ORGANISMOS_GENETICAMENTE_MODIFICADOS_OGM("Organismos geneticamente modificados (OGM)"),

    @XmlEnumValue("Organização agrária")
    VCGE1_ORGANIZACAO_AGRARIA("Organização agrária"),

    @XmlEnumValue("Organização da produção")
    VCGE1_ORGANIZACAO_DA_PRODUCAO("Organização da produção"),

    @XmlEnumValue("Organização da sociedade civil de interesse publico")
    VCGE1_ORGANIZACAO_DA_SOCIEDADE_CIVIL_DE_INTERESSE_PUBLICO("Organização da sociedade civil de interesse publico"),

    @XmlEnumValue("Organização do estado")
    VCGE1_ORGANIZACAO_DO_ESTADO("Organização do estado"),

    @XmlEnumValue("Organização política")
    VCGE1_ORGANIZACAO_POLITICA("Organização política"),

    @XmlEnumValue("Organização religiosa")
    VCGE1_ORGANIZACAO_RELIGIOSA("Organização religiosa"),

    @XmlEnumValue("Organização territorial")
    VCGE1_ORGANIZACAO_TERRITORIAL("Organização territorial"),

    @XmlEnumValue("Organizações internacionais")
    VCGE1_ORGANIZACOES_INTERNACIONAIS("Organizações internacionais"),

    @XmlEnumValue("Orçamento de saúde")
    VCGE1_ORCAMENTO_DE_SAUDE("Orçamento de saúde"),

    @XmlEnumValue("Orçamento do estado")
    VCGE1_ORCAMENTO_DO_ESTADO("Orçamento do estado"),

    @XmlEnumValue("Orçamento participativo")
    VCGE1_ORCAMENTO_PARTICIPATIVO("Orçamento participativo"),

    @XmlEnumValue("Ostreicultura")
    VCGE1_OSTREICULTURA("Ostreicultura"),

    @XmlEnumValue("Outorga dos direitos de uso de recursos hídricos")
    VCGE1_OUTORGA_DOS_DIREITOS_DE_USO_DE_RECURSOS_HIDRICOS("Outorga dos direitos de uso de recursos hídricos"),

    @XmlEnumValue("Ouvidoria")
    VCGE1_OUVIDORIA("Ouvidoria"),

    @XmlEnumValue("Ovinocultura")
    VCGE1_OVINOCULTURA("Ovinocultura"),

    @XmlEnumValue("Pampa")
    VCGE1_PAMPA("Pampa"),

    @XmlEnumValue("Pantanal")
    VCGE1_PANTANAL("Pantanal"),

    @XmlEnumValue("Paraguai")
    VCGE1_PARAGUAI("Paraguai"),

    @XmlEnumValue("Paralisia infantil")
    VCGE1_PARALISIA_INFANTIL("Paralisia infantil"),

    @XmlEnumValue("Paraná")
    VCGE1_PARANA("Paraná"),

    @XmlEnumValue("Paraíba do Sul")
    VCGE1_PARAIBA_DO_SUL("Paraíba do Sul"),

    @XmlEnumValue("Parcelamento de multas")
    VCGE1_PARCELAMENTO_DE_MULTAS("Parcelamento de multas"),

    @XmlEnumValue("Parcelamento de solo")
    VCGE1_PARCELAMENTO_DE_SOLO("Parcelamento de solo"),

    @XmlEnumValue("Parceria")
    VCGE1_PARCERIA("Parceria"),

    @XmlEnumValue("Pareceres")
    VCGE1_PARECERES("Pareceres"),

    @XmlEnumValue("Parnaíba")
    VCGE1_PARNAIBA("Parnaíba"),

    @XmlEnumValue("Parques Nacionais")
    VCGE1_PARQUES_NACIONAIS("Parques Nacionais"),

    @XmlEnumValue("Parques e jardins")
    VCGE1_PARQUES_E_JARDINS("Parques e jardins"),

    @XmlEnumValue("Participação e controle")
    VCGE1_PARTICIPACAO_E_CONTROLE("Participação e controle"),

    @XmlEnumValue("Participação e controle social em saúde")
    VCGE1_PARTICIPACAO_E_CONTROLE_SOCIAL_EM_SAUDE("Participação e controle social em saúde"),

    @XmlEnumValue("Partido político")
    VCGE1_PARTIDO_POLITICO("Partido político"),

    @XmlEnumValue("Partidos políticos")
    VCGE1_PARTIDOS_POLITICOS("Partidos políticos"),

    @XmlEnumValue("Parâmetros e diretrizes curriculares nacionais")
    VCGE1_PARAMETROS_E_DIRETRIZES_CURRICULARES_NACIONAIS("Parâmetros e diretrizes curriculares nacionais"),

    @XmlEnumValue("Passaporte")
    VCGE1_PASSAPORTE("Passaporte"),

    @XmlEnumValue("Passe escolar")
    VCGE1_PASSE_ESCOLAR("Passe escolar"),

    @XmlEnumValue("Pastagem")
    VCGE1_PASTAGEM("Pastagem"),

    @XmlEnumValue("Patrimônio da união")
    VCGE1_PATRIMONIO_DA_UNIAO("Patrimônio da união"),

    @XmlEnumValue("Patrimônio espeleológico")
    VCGE1_PATRIMONIO_ESPELEOLOGICO("Patrimônio espeleológico"),

    @XmlEnumValue("Patrimônio genético ou recursos genéticos")
    VCGE1_PATRIMONIO_GENETICO_OU_RECURSOS_GENETICOS("Patrimônio genético ou recursos genéticos"),

    @XmlEnumValue("Patrimônio histórico, artístico e arqueológico")
    VCGE1_PATRIMONIO_HISTORICO_ARTISTICO_E_ARQUEOLOGICO("Patrimônio histórico, artístico e arqueológico"),

    @XmlEnumValue("Pecuária")
    VCGE1_PECUARIA("Pecuária"),

    @XmlEnumValue("Peixe de água doce")
    VCGE1_PEIXE_DE_AGUA_DOCE("Peixe de água doce"),

    @XmlEnumValue("Peixe marinho")
    VCGE1_PEIXE_MARINHO("Peixe marinho"),

    @XmlEnumValue("Pele")
    VCGE1_PELE("Pele"),

    @XmlEnumValue("Penas alternativas")
    VCGE1_PENAS_ALTERNATIVAS("Penas alternativas"),

    @XmlEnumValue("Penitenciarias")
    VCGE1_PENITENCIARIAS("Penitenciarias"),

    @XmlEnumValue("Pensão alimentícia e alimentos")
    VCGE1_PENSAO_ALIMENTICIA_E_ALIMENTOS("Pensão alimentícia e alimentos"),

    @XmlEnumValue("Pensão por morte")
    VCGE1_PENSAO_POR_MORTE("Pensão por morte"),

    @XmlEnumValue("Pensões")
    VCGE1_PENSOES("Pensões"),

    @XmlEnumValue("Perda de habitats")
    VCGE1_PERDA_DE_HABITATS("Perda de habitats"),

    @XmlEnumValue("Permanência do estrangeiro")
    VCGE1_PERMANENCIA_DO_ESTRANGEIRO("Permanência do estrangeiro"),

    @XmlEnumValue("Perícia técnica")
    VCGE1_PERICIA_TECNICA("Perícia técnica"),

    @XmlEnumValue("Pesca")
    VCGE1_PESCA("Pesca"),

    @XmlEnumValue("Pesca amadora")
    VCGE1_PESCA_AMADORA("Pesca amadora"),

    @XmlEnumValue("Pesca artesanal")
    VCGE1_PESCA_ARTESANAL("Pesca artesanal"),

    @XmlEnumValue("Pesca continental")
    VCGE1_PESCA_CONTINENTAL("Pesca continental"),

    @XmlEnumValue("Pesca extensiva")
    VCGE1_PESCA_EXTENSIVA("Pesca extensiva"),

    @XmlEnumValue("Pesca fluvial")
    VCGE1_PESCA_FLUVIAL("Pesca fluvial"),

    @XmlEnumValue("Pesca industrial")
    VCGE1_PESCA_INDUSTRIAL("Pesca industrial"),

    @XmlEnumValue("Pesca intensiva")
    VCGE1_PESCA_INTENSIVA("Pesca intensiva"),

    @XmlEnumValue("Pesca predatória")
    VCGE1_PESCA_PREDATORIA("Pesca predatória"),

    @XmlEnumValue("Pesquisa científica de patrimônio genético")
    VCGE1_PESQUISA_CIENTIFICA_DE_PATRIMONIO_GENETICO("Pesquisa científica de patrimônio genético"),

    @XmlEnumValue("Pesquisa científica e tecnologia")
    VCGE1_PESQUISA_CIENTIFICA_E_TECNOLOGIA("Pesquisa científica e tecnologia"),

    @XmlEnumValue("Pesquisa comportamental no transporte coletivo")
    VCGE1_PESQUISA_COMPORTAMENTAL_NO_TRANSPORTE_COLETIVO("Pesquisa comportamental no transporte coletivo"),

    @XmlEnumValue("Pesquisa e coleta de material biológico")
    VCGE1_PESQUISA_E_COLETA_DE_MATERIAL_BIOLOGICO("Pesquisa e coleta de material biológico"),

    @XmlEnumValue("Pesquisa em saúde")
    VCGE1_PESQUISA_EM_SAUDE("Pesquisa em saúde"),

    @XmlEnumValue("Pesquisa florestal")
    VCGE1_PESQUISA_FLORESTAL("Pesquisa florestal"),

    @XmlEnumValue("Pesquisas para conservação de espécies")
    VCGE1_PESQUISAS_PARA_CONSERVACAO_DE_ESPECIES("Pesquisas para conservação de espécies"),

    @XmlEnumValue("Pessoa")
    VCGE1_PESSOA("Pessoa"),

    @XmlEnumValue("Pessoa, família e sociedade")
    VCGE1_PESSOA_FAMILIA_E_SOCIEDADE("Pessoa, família e sociedade"),

    @XmlEnumValue("Petróleo")
    VCGE1_PETROLEO("Petróleo"),

    @XmlEnumValue("Pintura")
    VCGE1_PINTURA("Pintura"),

    @XmlEnumValue("Piscicultura")
    VCGE1_PISCICULTURA("Piscicultura"),

    @XmlEnumValue("Planejamento ambiental")
    VCGE1_PLANEJAMENTO_AMBIENTAL("Planejamento ambiental"),

    @XmlEnumValue("Planejamento ambiental da aquicultura")
    VCGE1_PLANEJAMENTO_AMBIENTAL_DA_AQUICULTURA("Planejamento ambiental da aquicultura"),

    @XmlEnumValue("Planejamento e administração em saúde pública")
    VCGE1_PLANEJAMENTO_E_ADMINISTRACAO_EM_SAUDE_PUBLICA("Planejamento e administração em saúde pública"),

    @XmlEnumValue("Planejamento e orçamento")
    VCGE1_PLANEJAMENTO_E_ORCAMENTO("Planejamento e orçamento"),

    @XmlEnumValue("Planejamento estratégico")
    VCGE1_PLANEJAMENTO_ESTRATEGICO("Planejamento estratégico"),

    @XmlEnumValue("Planejamento familiar")
    VCGE1_PLANEJAMENTO_FAMILIAR("Planejamento familiar"),

    @XmlEnumValue("Planejamento florestal")
    VCGE1_PLANEJAMENTO_FLORESTAL("Planejamento florestal"),

    @XmlEnumValue("Planejamento urbano")
    VCGE1_PLANEJAMENTO_URBANO("Planejamento urbano"),

    @XmlEnumValue("Plano agrícola e pecuário")
    VCGE1_PLANO_AGRICOLA_E_PECUARIO("Plano agrícola e pecuário"),

    @XmlEnumValue("Plano nacional de educação")
    VCGE1_PLANO_NACIONAL_DE_EDUCACAO("Plano nacional de educação"),

    @XmlEnumValue("Plano plurianual")
    VCGE1_PLANO_PLURIANUAL("Plano plurianual"),

    @XmlEnumValue("Planos de recursos hídricos")
    VCGE1_PLANOS_DE_RECURSOS_HIDRICOS("Planos de recursos hídricos"),

    @XmlEnumValue("Planos de saúde")
    VCGE1_PLANOS_DE_SAUDE("Planos de saúde"),

    @XmlEnumValue("Plantas de imóveis")
    VCGE1_PLANTAS_DE_IMOVEIS("Plantas de imóveis"),

    @XmlEnumValue("Plantas medicinais")
    VCGE1_PLANTAS_MEDICINAIS("Plantas medicinais"),

    @XmlEnumValue("Plantio")
    VCGE1_PLANTIO("Plantio"),

    @XmlEnumValue("Plantio convencional")
    VCGE1_PLANTIO_CONVENCIONAL("Plantio convencional"),

    @XmlEnumValue("Plantio direto")
    VCGE1_PLANTIO_DIRETO("Plantio direto"),

    @XmlEnumValue("Plantio e recomposição florestal")
    VCGE1_PLANTIO_E_RECOMPOSICAO_FLORESTAL("Plantio e recomposição florestal"),

    @XmlEnumValue("Pneus")
    VCGE1_PNEUS("Pneus"),

    @XmlEnumValue("Poda")
    VCGE1_PODA("Poda"),

    @XmlEnumValue("Poder executivo")
    VCGE1_PODER_EXECUTIVO("Poder executivo"),

    @XmlEnumValue("Poder familiar")
    VCGE1_PODER_FAMILIAR("Poder familiar"),

    @XmlEnumValue("Poder judiciário")
    VCGE1_PODER_JUDICIARIO("Poder judiciário"),

    @XmlEnumValue("Poder legislativo")
    VCGE1_PODER_LEGISLATIVO("Poder legislativo"),

    @XmlEnumValue("Poderes do estado")
    VCGE1_PODERES_DO_ESTADO("Poderes do estado"),

    @XmlEnumValue("Policlínica")
    VCGE1_POLICLINICA("Policlínica"),

    @XmlEnumValue("Poluentes atmosféricos")
    VCGE1_POLUENTES_ATMOSFERICOS("Poluentes atmosféricos"),

    @XmlEnumValue("Poluentes orgânicos persistentes")
    VCGE1_POLUENTES_ORGANICOS_PERSISTENTES("Poluentes orgânicos persistentes"),

    @XmlEnumValue("Poluição sonora")
    VCGE1_POLUICAO_SONORA("Poluição sonora"),

    @XmlEnumValue("Polícia")
    VCGE1_POLICIA("Polícia"),

    @XmlEnumValue("Polícia ambiental")
    VCGE1_POLICIA_AMBIENTAL("Polícia ambiental"),

    @XmlEnumValue("Polícia civil")
    VCGE1_POLICIA_CIVIL("Polícia civil"),

    @XmlEnumValue("Polícia federal")
    VCGE1_POLICIA_FEDERAL("Polícia federal"),

    @XmlEnumValue("Polícia militar")
    VCGE1_POLICIA_MILITAR("Polícia militar"),

    @XmlEnumValue("Polícia municipal")
    VCGE1_POLICIA_MUNICIPAL("Polícia municipal"),

    @XmlEnumValue("Polícia rodoviária")
    VCGE1_POLICIA_RODOVIARIA("Polícia rodoviária"),

    @XmlEnumValue("Política")
    VCGE1_POLITICA("Política"),

    @XmlEnumValue("Política agrícola")
    VCGE1_POLITICA_AGRICOLA("Política agrícola"),

    @XmlEnumValue("Política cultural")
    VCGE1_POLITICA_CULTURAL("Política cultural"),

    @XmlEnumValue("Política de incentivo de comércio e serviços")
    VCGE1_POLITICA_DE_INCENTIVO_DE_COMERCIO_E_SERVICOS("Política de incentivo de comércio e serviços"),

    @XmlEnumValue("Política de preço")
    VCGE1_POLITICA_DE_PRECO("Política de preço"),

    @XmlEnumValue("Política e gestão industrial")
    VCGE1_POLITICA_E_GESTAO_INDUSTRIAL("Política e gestão industrial"),

    @XmlEnumValue("Política econômica")
    VCGE1_POLITICA_ECONOMICA("Política econômica"),

    @XmlEnumValue("Política externa")
    VCGE1_POLITICA_EXTERNA("Política externa"),

    @XmlEnumValue("Política nacional de justiça")
    VCGE1_POLITICA_NACIONAL_DE_JUSTICA("Política nacional de justiça"),

    @XmlEnumValue("Política trabalhista")
    VCGE1_POLITICA_TRABALHISTA("Política trabalhista"),

    @XmlEnumValue("Políticas e diretrizes para o SUS")
    VCGE1_POLITICAS_E_DIRETRIZES_PARA_O_SUS("Políticas e diretrizes para o SUS"),

    @XmlEnumValue("Por amparo legal")
    VCGE1_POR_AMPARO_LEGAL("Por amparo legal"),

    @XmlEnumValue("Porte de arma")
    VCGE1_PORTE_DE_ARMA("Porte de arma"),

    @XmlEnumValue("Portos")
    VCGE1_PORTOS("Portos"),

    @XmlEnumValue("Posto de saúde")
    VCGE1_POSTO_DE_SAUDE("Posto de saúde"),

    @XmlEnumValue("Potencial construtivo")
    VCGE1_POTENCIAL_CONSTRUTIVO("Potencial construtivo"),

    @XmlEnumValue("Povos indígenas")
    VCGE1_POVOS_INDIGENAS("Povos indígenas"),

    @XmlEnumValue("Praga de animal")
    VCGE1_PRAGA_DE_ANIMAL("Praga de animal"),

    @XmlEnumValue("Praga de planta")
    VCGE1_PRAGA_DE_PLANTA("Praga de planta"),

    @XmlEnumValue("Precatórios")
    VCGE1_PRECATORIOS("Precatórios"),

    @XmlEnumValue("Pregão")
    VCGE1_PREGAO("Pregão"),

    @XmlEnumValue("Preparo de solo")
    VCGE1_PREPARO_DE_SOLO("Preparo de solo"),

    @XmlEnumValue("Preservação de alimentos")
    VCGE1_PRESERVACAO_DE_ALIMENTOS("Preservação de alimentos"),

    @XmlEnumValue("Preservação de bosques e imóveis")
    VCGE1_PRESERVACAO_DE_BOSQUES_E_IMOVEIS("Preservação de bosques e imóveis"),

    @XmlEnumValue("Preservação de dados e informações")
    VCGE1_PRESERVACAO_DE_DADOS_E_INFORMACOES("Preservação de dados e informações"),

    @XmlEnumValue("Prestação de contas")
    VCGE1_PRESTACAO_DE_CONTAS("Prestação de contas"),

    @XmlEnumValue("Presídios")
    VCGE1_PRESIDIOS("Presídios"),

    @XmlEnumValue("Prevenção de acidentes de trânsito")
    VCGE1_PREVENCAO_DE_ACIDENTES_DE_TRANSITO("Prevenção de acidentes de trânsito"),

    @XmlEnumValue("Prevenção e atendimento a situação de emergência ambiental")
    VCGE1_PREVENCAO_E_ATENDIMENTO_A_SITUACAO_DE_EMERGENCIA_AMBIENTAL("Prevenção e atendimento a situação de emergência ambiental"),

    @XmlEnumValue("Prevenção e combate à poluição")
    VCGE1_PREVENCAO_E_COMBATE_A_POLUICAO("Prevenção e combate à poluição"),

    @XmlEnumValue("Prevenção e controle do meio ambiente")
    VCGE1_PREVENCAO_E_CONTROLE_DO_MEIO_AMBIENTE("Prevenção e controle do meio ambiente"),

    @XmlEnumValue("Previdência básica")
    VCGE1_PREVIDENCIA_BASICA("Previdência básica"),

    @XmlEnumValue("Previdência complementar")
    VCGE1_PREVIDENCIA_COMPLEMENTAR("Previdência complementar"),

    @XmlEnumValue("Previdência do regime estatutário")
    VCGE1_PREVIDENCIA_DO_REGIME_ESTATUTARIO("Previdência do regime estatutário"),

    @XmlEnumValue("Previdência do servidor")
    VCGE1_PREVIDENCIA_DO_SERVIDOR("Previdência do servidor"),

    @XmlEnumValue("Previdência social")
    VCGE1_PREVIDENCIA_SOCIAL("Previdência social"),

    @XmlEnumValue("Previsão de safra")
    VCGE1_PREVISAO_DE_SAFRA("Previsão de safra"),

    @XmlEnumValue("Preço mínimo")
    VCGE1_PRECO_MINIMO("Preço mínimo"),

    @XmlEnumValue("Primeiro ciclo")
    VCGE1_PRIMEIRO_CICLO("Primeiro ciclo"),

    @XmlEnumValue("Primeiros socorros")
    VCGE1_PRIMEIROS_SOCORROS("Primeiros socorros"),

    @XmlEnumValue("Privacidade da informação")
    VCGE1_PRIVACIDADE_DA_INFORMACAO("Privacidade da informação"),

    @XmlEnumValue("Procedimento administrativo disciplinar")
    VCGE1_PROCEDIMENTO_ADMINISTRATIVO_DISCIPLINAR("Procedimento administrativo disciplinar"),

    @XmlEnumValue("Procedimentos para publicidade ao ar livre")
    VCGE1_PROCEDIMENTOS_PARA_PUBLICIDADE_AO_AR_LIVRE("Procedimentos para publicidade ao ar livre"),

    @XmlEnumValue("Procedimentos técnicos e administrativos")
    VCGE1_PROCEDIMENTOS_TECNICOS_E_ADMINISTRATIVOS("Procedimentos técnicos e administrativos"),

    @XmlEnumValue("Processamento de produtos florestais")
    VCGE1_PROCESSAMENTO_DE_PRODUTOS_FLORESTAIS("Processamento de produtos florestais"),

    @XmlEnumValue("Processo licitatório de concessão de uso florestas públicas")
    VCGE1_PROCESSO_LICITATORIO_DE_CONCESSAO_DE_USO_FLORESTAS_PUBLICAS("Processo licitatório de concessão de uso florestas públicas"),

    @XmlEnumValue("Produto animal")
    VCGE1_PRODUTO_ANIMAL("Produto animal"),

    @XmlEnumValue("Produto vegetal")
    VCGE1_PRODUTO_VEGETAL("Produto vegetal"),

    @XmlEnumValue("Produtor de obra audiovisual")
    VCGE1_PRODUTOR_DE_OBRA_AUDIOVISUAL("Produtor de obra audiovisual"),

    @XmlEnumValue("Produtora de obra audiovisual")
    VCGE1_PRODUTORA_DE_OBRA_AUDIOVISUAL("Produtora de obra audiovisual"),

    @XmlEnumValue("Produtos do extrativismo")
    VCGE1_PRODUTOS_DO_EXTRATIVISMO("Produtos do extrativismo"),

    @XmlEnumValue("Produtos e serviços gerados pelas florestas")
    VCGE1_PRODUTOS_E_SERVICOS_GERADOS_PELAS_FLORESTAS("Produtos e serviços gerados pelas florestas"),

    @XmlEnumValue("Produtos e subprodutos da flora e da fauna")
    VCGE1_PRODUTOS_E_SUBPRODUTOS_DA_FLORA_E_DA_FAUNA("Produtos e subprodutos da flora e da fauna"),

    @XmlEnumValue("Produtos florestais madeireiros")
    VCGE1_PRODUTOS_FLORESTAIS_MADEIREIROS("Produtos florestais madeireiros"),

    @XmlEnumValue("Produtos florestais não-madeireiros")
    VCGE1_PRODUTOS_FLORESTAIS_NAO_MADEIREIROS("Produtos florestais não-madeireiros"),

    @XmlEnumValue("Produção Industrial")
    VCGE1_PRODUCAO_INDUSTRIAL("Produção Industrial"),

    @XmlEnumValue("Produção animal")
    VCGE1_PRODUCAO_ANIMAL("Produção animal"),

    @XmlEnumValue("Produção de sementes e mudas")
    VCGE1_PRODUCAO_DE_SEMENTES_E_MUDAS("Produção de sementes e mudas"),

    @XmlEnumValue("Produção florestal sustentável")
    VCGE1_PRODUCAO_FLORESTAL_SUSTENTAVEL("Produção florestal sustentável"),

    @XmlEnumValue("Produção mais limpa (P+L)")
    VCGE1_PRODUCAO_MAIS_LIMPA_P_L("Produção mais limpa (P+L)"),

    @XmlEnumValue("Produção pesqueira")
    VCGE1_PRODUCAO_PESQUEIRA("Produção pesqueira"),

    @XmlEnumValue("Produção vegetal")
    VCGE1_PRODUCAO_VEGETAL("Produção vegetal"),

    @XmlEnumValue("Professor")
    VCGE1_PROFESSOR("Professor"),

    @XmlEnumValue("Profissionais da educação")
    VCGE1_PROFISSIONAIS_DA_EDUCACAO("Profissionais da educação"),

    @XmlEnumValue("Profissões e ocupações")
    VCGE1_PROFISSOES_E_OCUPACOES("Profissões e ocupações"),

    @XmlEnumValue("Programadora (audiovisual)")
    VCGE1_PROGRAMADORA_AUDIOVISUAL("Programadora (audiovisual)"),

    @XmlEnumValue("Programas de governo - agricultura")
    VCGE1_PROGRAMAS_DE_GOVERNO_AGRICULTURA("Programas de governo - agricultura"),

    @XmlEnumValue("Programas de transferência de renda")
    VCGE1_PROGRAMAS_DE_TRANSFERENCIA_DE_RENDA("Programas de transferência de renda"),

    @XmlEnumValue("Programas recreacionais em unidades de conservação")
    VCGE1_PROGRAMAS_RECREACIONAIS_EM_UNIDADES_DE_CONSERVACAO("Programas recreacionais em unidades de conservação"),

    @XmlEnumValue("Projeto básico ambiental")
    VCGE1_PROJETO_BASICO_AMBIENTAL("Projeto básico ambiental"),

    @XmlEnumValue("Projeto curricular")
    VCGE1_PROJETO_CURRICULAR("Projeto curricular"),

    @XmlEnumValue("Projeto de lei estadual")
    VCGE1_PROJETO_DE_LEI_ESTADUAL("Projeto de lei estadual"),

    @XmlEnumValue("Projeto de lei federal")
    VCGE1_PROJETO_DE_LEI_FEDERAL("Projeto de lei federal"),

    @XmlEnumValue("Projeto de lei municipal")
    VCGE1_PROJETO_DE_LEI_MUNICIPAL("Projeto de lei municipal"),

    @XmlEnumValue("Projeto pedagógico")
    VCGE1_PROJETO_PEDAGOGICO("Projeto pedagógico"),

    @XmlEnumValue("Projetos agroextrativistas de florestas públicas")
    VCGE1_PROJETOS_AGROEXTRATIVISTAS_DE_FLORESTAS_PUBLICAS("Projetos agroextrativistas de florestas públicas"),

    @XmlEnumValue("Projetos de assentamento florestal")
    VCGE1_PROJETOS_DE_ASSENTAMENTO_FLORESTAL("Projetos de assentamento florestal"),

    @XmlEnumValue("Projetos de desenvolvimento sustentável")
    VCGE1_PROJETOS_DE_DESENVOLVIMENTO_SUSTENTAVEL("Projetos de desenvolvimento sustentável"),

    @XmlEnumValue("Projetos de lei")
    VCGE1_PROJETOS_DE_LEI("Projetos de lei"),

    @XmlEnumValue("Promoção comercial")
    VCGE1_PROMOCAO_COMERCIAL("Promoção comercial"),

    @XmlEnumValue("Promoção comercial internacional")
    VCGE1_PROMOCAO_COMERCIAL_INTERNACIONAL("Promoção comercial internacional"),

    @XmlEnumValue("Promoção do processamento local em florestas públicas")
    VCGE1_PROMOCAO_DO_PROCESSAMENTO_LOCAL_EM_FLORESTAS_PUBLICAS("Promoção do processamento local em florestas públicas"),

    @XmlEnumValue("Promoção industrial")
    VCGE1_PROMOCAO_INDUSTRIAL("Promoção industrial"),

    @XmlEnumValue("Pronto socorro")
    VCGE1_PRONTO_SOCORRO("Pronto socorro"),

    @XmlEnumValue("Propriedade industrial")
    VCGE1_PROPRIEDADE_INDUSTRIAL("Propriedade industrial"),

    @XmlEnumValue("Proteção ao trabalhador")
    VCGE1_PROTECAO_AO_TRABALHADOR("Proteção ao trabalhador"),

    @XmlEnumValue("Proteção dos ecossistemas")
    VCGE1_PROTECAO_DOS_ECOSSISTEMAS("Proteção dos ecossistemas"),

    @XmlEnumValue("Proteção e conservação de água")
    VCGE1_PROTECAO_E_CONSERVACAO_DE_AGUA("Proteção e conservação de água"),

    @XmlEnumValue("Proteção e defesa do cidadão")
    VCGE1_PROTECAO_E_DEFESA_DO_CIDADAO("Proteção e defesa do cidadão"),

    @XmlEnumValue("Proteção e recuperação de espécies ameçadas de extinção")
    VCGE1_PROTECAO_E_RECUPERACAO_DE_ESPECIES_AMECADAS_DE_EXTINCAO("Proteção e recuperação de espécies ameçadas de extinção"),

    @XmlEnumValue("Proteção integral")
    VCGE1_PROTECAO_INTEGRAL("Proteção integral"),

    @XmlEnumValue("Proteção social")
    VCGE1_PROTECAO_SOCIAL("Proteção social"),

    @XmlEnumValue("Proteção social básica")
    VCGE1_PROTECAO_SOCIAL_BASICA("Proteção social básica"),

    @XmlEnumValue("Proteção social especial à pessoa em situação de risco")
    VCGE1_PROTECAO_SOCIAL_ESPECIAL_A_PESSOA_EM_SITUACAO_DE_RISCO("Proteção social especial à pessoa em situação de risco"),

    @XmlEnumValue("Protocolo")
    VCGE1_PROTOCOLO("Protocolo"),

    @XmlEnumValue("Protocolo de Quioto")
    VCGE1_PROTOCOLO_DE_QUIOTO("Protocolo de Quioto"),

    @XmlEnumValue("Pré-escola")
    VCGE1_PRE_ESCOLA("Pré-escola"),

    @XmlEnumValue("Publicações oficiais")
    VCGE1_PUBLICACOES_OFICIAIS("Publicações oficiais"),

    @XmlEnumValue("Publicidade ao ar livre")
    VCGE1_PUBLICIDADE_AO_AR_LIVRE("Publicidade ao ar livre"),

    @XmlEnumValue("Pós-graduação")
    VCGE1_POS_GRADUACAO("Pós-graduação"),

    @XmlEnumValue("Qualidade ambiental")
    VCGE1_QUALIDADE_AMBIENTAL("Qualidade ambiental"),

    @XmlEnumValue("Qualidade de agrotóxicos")
    VCGE1_QUALIDADE_DE_AGROTOXICOS("Qualidade de agrotóxicos"),

    @XmlEnumValue("Qualidade de alimentos")
    VCGE1_QUALIDADE_DE_ALIMENTOS("Qualidade de alimentos"),

    @XmlEnumValue("Qualidade de cosméticos")
    VCGE1_QUALIDADE_DE_COSMETICOS("Qualidade de cosméticos"),

    @XmlEnumValue("Qualidade de derivados do tabaco")
    VCGE1_QUALIDADE_DE_DERIVADOS_DO_TABACO("Qualidade de derivados do tabaco"),

    @XmlEnumValue("Qualidade de medicamentos")
    VCGE1_QUALIDADE_DE_MEDICAMENTOS("Qualidade de medicamentos"),

    @XmlEnumValue("Qualidade de produtos para o consumidor")
    VCGE1_QUALIDADE_DE_PRODUTOS_PARA_O_CONSUMIDOR("Qualidade de produtos para o consumidor"),

    @XmlEnumValue("Qualidade de saneantes")
    VCGE1_QUALIDADE_DE_SANEANTES("Qualidade de saneantes"),

    @XmlEnumValue("Qualidade de software")
    VCGE1_QUALIDADE_DE_SOFTWARE("Qualidade de software"),

    @XmlEnumValue("Qualidade de água")
    VCGE1_QUALIDADE_DE_AGUA("Qualidade de água"),

    @XmlEnumValue("Qualidade do ar")
    VCGE1_QUALIDADE_DO_AR("Qualidade do ar"),

    @XmlEnumValue("Qualidade dos medicamento")
    VCGE1_QUALIDADE_DOS_MEDICAMENTO("Qualidade dos medicamento"),

    @XmlEnumValue("Qualificação e aprendizagem profissional")
    VCGE1_QUALIFICACAO_E_APRENDIZAGEM_PROFISSIONAL("Qualificação e aprendizagem profissional"),

    @XmlEnumValue("Quantidade de água")
    VCGE1_QUANTIDADE_DE_AGUA("Quantidade de água"),

    @XmlEnumValue("Quarentena animal")
    VCGE1_QUARENTENA_ANIMAL("Quarentena animal"),

    @XmlEnumValue("Quarentena vegetal")
    VCGE1_QUARENTENA_VEGETAL("Quarentena vegetal"),

    @XmlEnumValue("Queimadas e incêndios")
    VCGE1_QUEIMADAS_E_INCENDIOS("Queimadas e incêndios"),

    @XmlEnumValue("Racionalização do uso dos recursos e sua destinação")
    VCGE1_RACIONALIZACAO_DO_USO_DOS_RECURSOS_E_SUA_DESTINACAO("Racionalização do uso dos recursos e sua destinação"),

    @XmlEnumValue("Radares")
    VCGE1_RADARES("Radares"),

    @XmlEnumValue("Radioamador")
    VCGE1_RADIOAMADOR("Radioamador"),

    @XmlEnumValue("Radiodifusão")
    VCGE1_RADIODIFUSAO("Radiodifusão"),

    @XmlEnumValue("Rastreamento e certificação da produção agrícola")
    VCGE1_RASTREAMENTO_E_CERTIFICACAO_DA_PRODUCAO_AGRICOLA("Rastreamento e certificação da produção agrícola"),

    @XmlEnumValue("Ração")
    VCGE1_RACAO("Ração"),

    @XmlEnumValue("Reabilitação profissional")
    VCGE1_REABILITACAO_PROFISSIONAL("Reabilitação profissional"),

    @XmlEnumValue("Realização orçamentária e financeira")
    VCGE1_REALIZACAO_ORCAMENTARIA_E_FINANCEIRA("Realização orçamentária e financeira"),

    @XmlEnumValue("Reciclagem")
    VCGE1_RECICLAGEM("Reciclagem"),

    @XmlEnumValue("Recifes de coral")
    VCGE1_RECIFES_DE_CORAL("Recifes de coral"),

    @XmlEnumValue("Reconhecimento de cursos")
    VCGE1_RECONHECIMENTO_DE_CURSOS("Reconhecimento de cursos"),

    @XmlEnumValue("Reconhecimento de filho")
    VCGE1_RECONHECIMENTO_DE_FILHO("Reconhecimento de filho"),

    @XmlEnumValue("Recreação")
    VCGE1_RECREACAO("Recreação"),

    @XmlEnumValue("Recuperação de ativos")
    VCGE1_RECUPERACAO_DE_ATIVOS("Recuperação de ativos"),

    @XmlEnumValue("Recuperação de recursos pesqueiros sobreexplotados")
    VCGE1_RECUPERACAO_DE_RECURSOS_PESQUEIROS_SOBREEXPLOTADOS("Recuperação de recursos pesqueiros sobreexplotados"),

    @XmlEnumValue("Recuperação de áreas degradadas")
    VCGE1_RECUPERACAO_DE_AREAS_DEGRADADAS("Recuperação de áreas degradadas"),

    @XmlEnumValue("Recuperação e manejo sustentável")
    VCGE1_RECUPERACAO_E_MANEJO_SUSTENTAVEL("Recuperação e manejo sustentável"),

    @XmlEnumValue("Recuperação e restauração de ecossistemas")
    VCGE1_RECUPERACAO_E_RESTAURACAO_DE_ECOSSISTEMAS("Recuperação e restauração de ecossistemas"),

    @XmlEnumValue("Recurso ao conselho municipal de urbanismo")
    VCGE1_RECURSO_AO_CONSELHO_MUNICIPAL_DE_URBANISMO("Recurso ao conselho municipal de urbanismo"),

    @XmlEnumValue("Recursos energéticos")
    VCGE1_RECURSOS_ENERGETICOS("Recursos energéticos"),

    @XmlEnumValue("Recursos genéticos naturais sujeitos a restrições legais")
    VCGE1_RECURSOS_GENETICOS_NATURAIS_SUJEITOS_A_RESTRICOES_LEGAIS("Recursos genéticos naturais sujeitos a restrições legais"),

    @XmlEnumValue("Recursos humanos")
    VCGE1_RECURSOS_HUMANOS("Recursos humanos"),

    @XmlEnumValue("Recursos humanos em saúde")
    VCGE1_RECURSOS_HUMANOS_EM_SAUDE("Recursos humanos em saúde"),

    @XmlEnumValue("Recursos hídricos na agropecuária")
    VCGE1_RECURSOS_HIDRICOS_NA_AGROPECUARIA("Recursos hídricos na agropecuária"),

    @XmlEnumValue("Recursos hídricos na indústria")
    VCGE1_RECURSOS_HIDRICOS_NA_INDUSTRIA("Recursos hídricos na indústria"),

    @XmlEnumValue("Recursos hídricos transfronteiriços")
    VCGE1_RECURSOS_HIDRICOS_TRANSFRONTEIRICOS("Recursos hídricos transfronteiriços"),

    @XmlEnumValue("Recursos logísticos")
    VCGE1_RECURSOS_LOGISTICOS("Recursos logísticos"),

    @XmlEnumValue("Recursos pesqueiros")
    VCGE1_RECURSOS_PESQUEIROS("Recursos pesqueiros"),

    @XmlEnumValue("Rede brasileira de fundos socioambientais")
    VCGE1_REDE_BRASILEIRA_DE_FUNDOS_SOCIOAMBIENTAIS("Rede brasileira de fundos socioambientais"),

    @XmlEnumValue("Rede de unidades de conservação particulares")
    VCGE1_REDE_DE_UNIDADES_DE_CONSERVACAO_PARTICULARES("Rede de unidades de conservação particulares"),

    @XmlEnumValue("Rede laboratorial de sementes")
    VCGE1_REDE_LABORATORIAL_DE_SEMENTES("Rede laboratorial de sementes"),

    @XmlEnumValue("Redes de comunicação")
    VCGE1_REDES_DE_COMUNICACAO("Redes de comunicação"),

    @XmlEnumValue("Refinanciamento da divida externa")
    VCGE1_REFINANCIAMENTO_DA_DIVIDA_EXTERNA("Refinanciamento da divida externa"),

    @XmlEnumValue("Refinanciamento da divida interna")
    VCGE1_REFINANCIAMENTO_DA_DIVIDA_INTERNA("Refinanciamento da divida interna"),

    @XmlEnumValue("Reforma agrária")
    VCGE1_REFORMA_AGRARIA("Reforma agrária"),

    @XmlEnumValue("Reforma de imóveis")
    VCGE1_REFORMA_DE_IMOVEIS("Reforma de imóveis"),

    @XmlEnumValue("Reforma do judiciário")
    VCGE1_REFORMA_DO_JUDICIARIO("Reforma do judiciário"),

    @XmlEnumValue("Refúgio da vida silvestre")
    VCGE1_REFUGIO_DA_VIDA_SILVESTRE("Refúgio da vida silvestre"),

    @XmlEnumValue("Regima aberto")
    VCGE1_REGIMA_ABERTO("Regima aberto"),

    @XmlEnumValue("Regime de execução de pena")
    VCGE1_REGIME_DE_EXECUCAO_DE_PENA("Regime de execução de pena"),

    @XmlEnumValue("Regime de quarentena")
    VCGE1_REGIME_DE_QUARENTENA("Regime de quarentena"),

    @XmlEnumValue("Regime fechado")
    VCGE1_REGIME_FECHADO("Regime fechado"),

    @XmlEnumValue("Regime político")
    VCGE1_REGIME_POLITICO("Regime político"),

    @XmlEnumValue("Regime semi-aberto")
    VCGE1_REGIME_SEMI_ABERTO("Regime semi-aberto"),

    @XmlEnumValue("Registro comercial")
    VCGE1_REGISTRO_COMERCIAL("Registro comercial"),

    @XmlEnumValue("Registro de emissão e transferências de poluentes")
    VCGE1_REGISTRO_DE_EMISSAO_E_TRANSFERENCIAS_DE_POLUENTES("Registro de emissão e transferências de poluentes"),

    @XmlEnumValue("Registro de nascimento")
    VCGE1_REGISTRO_DE_NASCIMENTO("Registro de nascimento"),

    @XmlEnumValue("Registro de obra audiovisual")
    VCGE1_REGISTRO_DE_OBRA_AUDIOVISUAL("Registro de obra audiovisual"),

    @XmlEnumValue("Regiões com balanço deficitário de recursos hídricos")
    VCGE1_REGIOES_COM_BALANCO_DEFICITARIO_DE_RECURSOS_HIDRICOS("Regiões com balanço deficitário de recursos hídricos"),

    @XmlEnumValue("Regiões e estados")
    VCGE1_REGIOES_E_ESTADOS("Regiões e estados"),

    @XmlEnumValue("Regiões hidrográficas")
    VCGE1_REGIOES_HIDROGRAFICAS("Regiões hidrográficas"),

    @XmlEnumValue("Regulamentação comercial")
    VCGE1_REGULAMENTACAO_COMERCIAL("Regulamentação comercial"),

    @XmlEnumValue("Regulamentação industrial")
    VCGE1_REGULAMENTACAO_INDUSTRIAL("Regulamentação industrial"),

    @XmlEnumValue("Regulamentação profissional")
    VCGE1_REGULAMENTACAO_PROFISSIONAL("Regulamentação profissional"),

    @XmlEnumValue("Regularização fundiária")
    VCGE1_REGULARIZACAO_FUNDIARIA("Regularização fundiária"),

    @XmlEnumValue("Relacionados à unidades de conservação e zonas de amortecimento")
    VCGE1_RELACIONADOS_A_UNIDADES_DE_CONSERVACAO_E_ZONAS_DE_AMORTECIMENTO("Relacionados à unidades de conservação e zonas de amortecimento"),

    @XmlEnumValue("Relatório de impacto ambiental - RIMA")
    VCGE1_RELATORIO_DE_IMPACTO_AMBIENTAL_RIMA("Relatório de impacto ambiental - RIMA"),

    @XmlEnumValue("Relações bilaterais")
    VCGE1_RELACOES_BILATERAIS("Relações bilaterais"),

    @XmlEnumValue("Relações de trabalho")
    VCGE1_RELACOES_DE_TRABALHO("Relações de trabalho"),

    @XmlEnumValue("Relações diplomáticas")
    VCGE1_RELACOES_DIPLOMATICAS("Relações diplomáticas"),

    @XmlEnumValue("Relações internacionais")
    VCGE1_RELACOES_INTERNACIONAIS("Relações internacionais"),

    @XmlEnumValue("Remessa de patrimônio genético")
    VCGE1_REMESSA_DE_PATRIMONIO_GENETICO("Remessa de patrimônio genético"),

    @XmlEnumValue("Remuneração")
    VCGE1_REMUNERACAO("Remuneração"),

    @XmlEnumValue("Remuneração dos serviços ambientais")
    VCGE1_REMUNERACAO_DOS_SERVICOS_AMBIENTAIS("Remuneração dos serviços ambientais"),

    @XmlEnumValue("Repartição de benefícios do patrimônio genético")
    VCGE1_REPARTICAO_DE_BENEFICIOS_DO_PATRIMONIO_GENETICO("Repartição de benefícios do patrimônio genético"),

    @XmlEnumValue("Repartição de competências")
    VCGE1_REPARTICAO_DE_COMPETENCIAS("Repartição de competências"),

    @XmlEnumValue("Reposição florestal")
    VCGE1_REPOSICAO_FLORESTAL("Reposição florestal"),

    @XmlEnumValue("Representação judicial e extrajudicial")
    VCGE1_REPRESENTACAO_JUDICIAL_E_EXTRAJUDICIAL("Representação judicial e extrajudicial"),

    @XmlEnumValue("Representações brasileira no exterior")
    VCGE1_REPRESENTACOES_BRASILEIRA_NO_EXTERIOR("Representações brasileira no exterior"),

    @XmlEnumValue("Representações estrangeira no Brasil")
    VCGE1_REPRESENTACOES_ESTRANGEIRA_NO_BRASIL("Representações estrangeira no Brasil"),

    @XmlEnumValue("Requerimento de publicidade ao ar livre")
    VCGE1_REQUERIMENTO_DE_PUBLICIDADE_AO_AR_LIVRE("Requerimento de publicidade ao ar livre"),

    @XmlEnumValue("Reserva da fauna")
    VCGE1_RESERVA_DA_FAUNA("Reserva da fauna"),

    @XmlEnumValue("Reserva legal")
    VCGE1_RESERVA_LEGAL("Reserva legal"),

    @XmlEnumValue("Reserva particular do patrimônio natural")
    VCGE1_RESERVA_PARTICULAR_DO_PATRIMONIO_NATURAL("Reserva particular do patrimônio natural"),

    @XmlEnumValue("Reservas biológicas")
    VCGE1_RESERVAS_BIOLOGICAS("Reservas biológicas"),

    @XmlEnumValue("Reservas de desenvolvimento sustentável")
    VCGE1_RESERVAS_DE_DESENVOLVIMENTO_SUSTENTAVEL("Reservas de desenvolvimento sustentável"),

    @XmlEnumValue("Reservas extrativistas")
    VCGE1_RESERVAS_EXTRATIVISTAS("Reservas extrativistas"),

    @XmlEnumValue("Residência medica")
    VCGE1_RESIDENCIA_MEDICA("Residência medica"),

    @XmlEnumValue("Resoluções")
    VCGE1_RESOLUCOES("Resoluções"),

    @XmlEnumValue("Ressocialização do egresso")
    VCGE1_RESSOCIALIZACAO_DO_EGRESSO("Ressocialização do egresso"),

    @XmlEnumValue("Restaurantes")
    VCGE1_RESTAURANTES("Restaurantes"),

    @XmlEnumValue("Restauração e preservação")
    VCGE1_RESTAURACAO_E_PRESERVACAO("Restauração e preservação"),

    @XmlEnumValue("Resíduos agropecuários")
    VCGE1_RESIDUOS_AGROPECUARIOS("Resíduos agropecuários"),

    @XmlEnumValue("Resíduos comerciais")
    VCGE1_RESIDUOS_COMERCIAIS("Resíduos comerciais"),

    @XmlEnumValue("Resíduos da construção civil e demolição")
    VCGE1_RESIDUOS_DA_CONSTRUCAO_CIVIL_E_DEMOLICAO("Resíduos da construção civil e demolição"),

    @XmlEnumValue("Resíduos de serviços de saúde")
    VCGE1_RESIDUOS_DE_SERVICOS_DE_SAUDE("Resíduos de serviços de saúde"),

    @XmlEnumValue("Resíduos domiciliares")
    VCGE1_RESIDUOS_DOMICILIARES("Resíduos domiciliares"),

    @XmlEnumValue("Resíduos e rejeitos")
    VCGE1_RESIDUOS_E_REJEITOS("Resíduos e rejeitos"),

    @XmlEnumValue("Resíduos florestais")
    VCGE1_RESIDUOS_FLORESTAIS("Resíduos florestais"),

    @XmlEnumValue("Resíduos hospitalares")
    VCGE1_RESIDUOS_HOSPITALARES("Resíduos hospitalares"),

    @XmlEnumValue("Resíduos industriais")
    VCGE1_RESIDUOS_INDUSTRIAIS("Resíduos industriais"),

    @XmlEnumValue("Resíduos perigosos")
    VCGE1_RESIDUOS_PERIGOSOS("Resíduos perigosos"),

    @XmlEnumValue("Resíduos químicos")
    VCGE1_RESIDUOS_QUIMICOS("Resíduos químicos"),

    @XmlEnumValue("Resíduos radiotivos")
    VCGE1_RESIDUOS_RADIOTIVOS("Resíduos radiotivos"),

    @XmlEnumValue("Resíduos rurais")
    VCGE1_RESIDUOS_RURAIS("Resíduos rurais"),

    @XmlEnumValue("Resíduos sólidos urbanos")
    VCGE1_RESIDUOS_SOLIDOS_URBANOS("Resíduos sólidos urbanos"),

    @XmlEnumValue("Resíduos tóxicos")
    VCGE1_RESIDUOS_TOXICOS("Resíduos tóxicos"),

    @XmlEnumValue("Reuso e reaproveitamento de água")
    VCGE1_REUSO_E_REAPROVEITAMENTO_DE_AGUA("Reuso e reaproveitamento de água"),

    @XmlEnumValue("Revalidação diploma curso superior")
    VCGE1_REVALIDACAO_DIPLOMA_CURSO_SUPERIOR("Revalidação diploma curso superior"),

    @XmlEnumValue("Revitalização de bacias hidrográficas")
    VCGE1_REVITALIZACAO_DE_BACIAS_HIDROGRAFICAS("Revitalização de bacias hidrográficas"),

    @XmlEnumValue("Riscos associados a novas tecnologias")
    VCGE1_RISCOS_ASSOCIADOS_A_NOVAS_TECNOLOGIAS("Riscos associados a novas tecnologias"),

    @XmlEnumValue("Robótica")
    VCGE1_ROBOTICA("Robótica"),

    @XmlEnumValue("Rodoviárias")
    VCGE1_RODOVIARIAS("Rodoviárias"),

    @XmlEnumValue("Rodoviário")
    VCGE1_RODOVIARIO("Rodoviário"),

    @XmlEnumValue("Roteiros e atrações turísticas")
    VCGE1_ROTEIROS_E_ATRACOES_TURISTICAS("Roteiros e atrações turísticas"),

    @XmlEnumValue("Roteiros metodológicos de proteção integral de UCS")
    VCGE1_ROTEIROS_METODOLOGICOS_DE_PROTECAO_INTEGRAL_DE_UCS("Roteiros metodológicos de proteção integral de UCS"),

    @XmlEnumValue("Rádio")
    VCGE1_RADIO("Rádio"),

    @XmlEnumValue("SAMU 192")
    VCGE1_SAMU_192("SAMU 192"),

    @XmlEnumValue("Safra")
    VCGE1_SAFRA("Safra"),

    @XmlEnumValue("Sala de exibição")
    VCGE1_SALA_DE_EXIBICAO("Sala de exibição"),

    @XmlEnumValue("Salas verdes")
    VCGE1_SALAS_VERDES("Salas verdes"),

    @XmlEnumValue("Salas verdes x pontos de cultura")
    VCGE1_SALAS_VERDES_X_PONTOS_DE_CULTURA("Salas verdes x pontos de cultura"),

    @XmlEnumValue("Salário família")
    VCGE1_SALARIO_FAMILIA("Salário família"),

    @XmlEnumValue("Salário maternidade")
    VCGE1_SALARIO_MATERNIDADE("Salário maternidade"),

    @XmlEnumValue("Saneamento")
    VCGE1_SANEAMENTO("Saneamento"),

    @XmlEnumValue("Saneamento ambiental urbano")
    VCGE1_SANEAMENTO_AMBIENTAL_URBANO("Saneamento ambiental urbano"),

    @XmlEnumValue("Saneamento urbano")
    VCGE1_SANEAMENTO_URBANO("Saneamento urbano"),

    @XmlEnumValue("Sangue e hemoderivados")
    VCGE1_SANGUE_E_HEMODERIVADOS("Sangue e hemoderivados"),

    @XmlEnumValue("Sanidade animal")
    VCGE1_SANIDADE_ANIMAL("Sanidade animal"),

    @XmlEnumValue("Sanções")
    VCGE1_SANCOES("Sanções"),

    @XmlEnumValue("Sanções administrativas")
    VCGE1_SANCOES_ADMINISTRATIVAS("Sanções administrativas"),

    @XmlEnumValue("Sanções administrativas compensatórias")
    VCGE1_SANCOES_ADMINISTRATIVAS_COMPENSATORIAS("Sanções administrativas compensatórias"),

    @XmlEnumValue("Sanções criminais")
    VCGE1_SANCOES_CRIMINAIS("Sanções criminais"),

    @XmlEnumValue("Sanções cíveis")
    VCGE1_SANCOES_CIVEIS("Sanções cíveis"),

    @XmlEnumValue("Sanções da compensação ambiental")
    VCGE1_SANCOES_DA_COMPENSACAO_AMBIENTAL("Sanções da compensação ambiental"),

    @XmlEnumValue("Saúde")
    VCGE1_SAUDE("Saúde"),

    @XmlEnumValue("Saúde bucal")
    VCGE1_SAUDE_BUCAL("Saúde bucal"),

    @XmlEnumValue("Saúde da criança")
    VCGE1_SAUDE_DA_CRIANCA("Saúde da criança"),

    @XmlEnumValue("Saúde da família")
    VCGE1_SAUDE_DA_FAMILIA("Saúde da família"),

    @XmlEnumValue("Saúde da mulher")
    VCGE1_SAUDE_DA_MULHER("Saúde da mulher"),

    @XmlEnumValue("Saúde da população negra")
    VCGE1_SAUDE_DA_POPULACAO_NEGRA("Saúde da população negra"),

    @XmlEnumValue("Saúde do adolescente e do jovem")
    VCGE1_SAUDE_DO_ADOLESCENTE_E_DO_JOVEM("Saúde do adolescente e do jovem"),

    @XmlEnumValue("Saúde do homem")
    VCGE1_SAUDE_DO_HOMEM("Saúde do homem"),

    @XmlEnumValue("Saúde do idoso")
    VCGE1_SAUDE_DO_IDOSO("Saúde do idoso"),

    @XmlEnumValue("Saúde do indígena")
    VCGE1_SAUDE_DO_INDIGENA("Saúde do indígena"),

    @XmlEnumValue("Saúde do preso")
    VCGE1_SAUDE_DO_PRESO("Saúde do preso"),

    @XmlEnumValue("Saúde do trabalhador")
    VCGE1_SAUDE_DO_TRABALHADOR("Saúde do trabalhador"),

    @XmlEnumValue("Saúde do viajante")
    VCGE1_SAUDE_DO_VIAJANTE("Saúde do viajante"),

    @XmlEnumValue("Saúde dos portadores de deficiências")
    VCGE1_SAUDE_DOS_PORTADORES_DE_DEFICIENCIAS("Saúde dos portadores de deficiências"),

    @XmlEnumValue("Saúde e seguro")
    VCGE1_SAUDE_E_SEGURO("Saúde e seguro"),

    @XmlEnumValue("Saúde mental")
    VCGE1_SAUDE_MENTAL("Saúde mental"),

    @XmlEnumValue("Saúde no sistema penitenciário")
    VCGE1_SAUDE_NO_SISTEMA_PENITENCIARIO("Saúde no sistema penitenciário"),

    @XmlEnumValue("Saúde ocular")
    VCGE1_SAUDE_OCULAR("Saúde ocular"),

    @XmlEnumValue("Saúde reprodutiva")
    VCGE1_SAUDE_REPRODUTIVA("Saúde reprodutiva"),

    @XmlEnumValue("Secretaria escolar")
    VCGE1_SECRETARIA_ESCOLAR("Secretaria escolar"),

    @XmlEnumValue("Segundo ciclo")
    VCGE1_SEGUNDO_CICLO("Segundo ciclo"),

    @XmlEnumValue("Segurança Nacional")
    VCGE1_SEGURANCA_NACIONAL("Segurança Nacional"),

    @XmlEnumValue("Segurança alimentar")
    VCGE1_SEGURANCA_ALIMENTAR("Segurança alimentar"),

    @XmlEnumValue("Segurança alimentar e nutricional")
    VCGE1_SEGURANCA_ALIMENTAR_E_NUTRICIONAL("Segurança alimentar e nutricional"),

    @XmlEnumValue("Segurança de dados e informações")
    VCGE1_SEGURANCA_DE_DADOS_E_INFORMACOES("Segurança de dados e informações"),

    @XmlEnumValue("Segurança hídrica")
    VCGE1_SEGURANCA_HIDRICA("Segurança hídrica"),

    @XmlEnumValue("Segurança no trabalho")
    VCGE1_SEGURANCA_NO_TRABALHO("Segurança no trabalho"),

    @XmlEnumValue("Segurança pública")
    VCGE1_SEGURANCA_PUBLICA("Segurança pública"),

    @XmlEnumValue("Segurança química")
    VCGE1_SEGURANCA_QUIMICA("Segurança química"),

    @XmlEnumValue("Seguro desemprego")
    VCGE1_SEGURO_DESEMPREGO("Seguro desemprego"),

    @XmlEnumValue("Seguro habitacional")
    VCGE1_SEGURO_HABITACIONAL("Seguro habitacional"),

    @XmlEnumValue("Seguros")
    VCGE1_SEGUROS("Seguros"),

    @XmlEnumValue("Sentença judicial")
    VCGE1_SENTENCA_JUDICIAL("Sentença judicial"),

    @XmlEnumValue("Separação e divorcio")
    VCGE1_SEPARACAO_E_DIVORCIO("Separação e divorcio"),

    @XmlEnumValue("Sericicultura")
    VCGE1_SERICICULTURA("Sericicultura"),

    @XmlEnumValue("Servidão ambiental")
    VCGE1_SERVIDAO_AMBIENTAL("Servidão ambiental"),

    @XmlEnumValue("Serviço da divida interna")
    VCGE1_SERVICO_DA_DIVIDA_INTERNA("Serviço da divida interna"),

    @XmlEnumValue("Serviço da dívida externa")
    VCGE1_SERVICO_DA_DIVIDA_EXTERNA("Serviço da dívida externa"),

    @XmlEnumValue("Serviço de Inteligência")
    VCGE1_SERVICO_DE_INTELIGENCIA("Serviço de Inteligência"),

    @XmlEnumValue("Serviço militar")
    VCGE1_SERVICO_MILITAR("Serviço militar"),

    @XmlEnumValue("Serviço móvel por satélite")
    VCGE1_SERVICO_MOVEL_POR_SATELITE("Serviço móvel por satélite"),

    @XmlEnumValue("Serviços")
    VCGE1_SERVICOS("Serviços"),

    @XmlEnumValue("Serviços Urbanos")
    VCGE1_SERVICOS_URBANOS("Serviços Urbanos"),

    @XmlEnumValue("Serviços ambientais")
    VCGE1_SERVICOS_AMBIENTAIS("Serviços ambientais"),

    @XmlEnumValue("Serviços consulares")
    VCGE1_SERVICOS_CONSULARES("Serviços consulares"),

    @XmlEnumValue("Serviços de coleta, unitização, desunitização, movimentação, armazenagem e entrega de cargas ao dest")
    VCGE1_SERVICOS_DE_COLETA_UNITIZACAO_DESUNITIZACAO_MOVIMENTACAO_ARMAZENAGEM_E_ENTREGA_DE_CARGAS_AO_DEST("Serviços de coleta, unitização, desunitização, movimentação, armazenagem e entrega de cargas ao dest"),

    @XmlEnumValue("Serviços postais")
    VCGE1_SERVICOS_POSTAIS("Serviços postais"),

    @XmlEnumValue("Silvicultura com espécies nativas")
    VCGE1_SILVICULTURA_COM_ESPECIES_NATIVAS("Silvicultura com espécies nativas"),

    @XmlEnumValue("Silvicultura em espécies nativas")
    VCGE1_SILVICULTURA_EM_ESPECIES_NATIVAS("Silvicultura em espécies nativas"),

    @XmlEnumValue("Sindicato")
    VCGE1_SINDICATO("Sindicato"),

    @XmlEnumValue("Sistema de informação em saúde")
    VCGE1_SISTEMA_DE_INFORMACAO_EM_SAUDE("Sistema de informação em saúde"),

    @XmlEnumValue("Sistema de informações sobre recursos hídricos")
    VCGE1_SISTEMA_DE_INFORMACOES_SOBRE_RECURSOS_HIDRICOS("Sistema de informações sobre recursos hídricos"),

    @XmlEnumValue("Sistema de mosaicos de áreas protegidas")
    VCGE1_SISTEMA_DE_MOSAICOS_DE_AREAS_PROTEGIDAS("Sistema de mosaicos de áreas protegidas"),

    @XmlEnumValue("Sistema de pagamentos brasileiro")
    VCGE1_SISTEMA_DE_PAGAMENTOS_BRASILEIRO("Sistema de pagamentos brasileiro"),

    @XmlEnumValue("Sistema de produção")
    VCGE1_SISTEMA_DE_PRODUCAO("Sistema de produção"),

    @XmlEnumValue("Sistema de reserva de vagas")
    VCGE1_SISTEMA_DE_RESERVA_DE_VAGAS("Sistema de reserva de vagas"),

    @XmlEnumValue("Sistema de saúde")
    VCGE1_SISTEMA_DE_SAUDE("Sistema de saúde"),

    @XmlEnumValue("Sistema de saúde suplementar")
    VCGE1_SISTEMA_DE_SAUDE_SUPLEMENTAR("Sistema de saúde suplementar"),

    @XmlEnumValue("Sistema educacional - Avaliação")
    VCGE1_SISTEMA_EDUCACIONAL_AVALIACAO("Sistema educacional - Avaliação"),

    @XmlEnumValue("Sistema eleitoral")
    VCGE1_SISTEMA_ELEITORAL("Sistema eleitoral"),

    @XmlEnumValue("Sistema financeiro")
    VCGE1_SISTEMA_FINANCEIRO("Sistema financeiro"),

    @XmlEnumValue("Sistema judicial")
    VCGE1_SISTEMA_JUDICIAL("Sistema judicial"),

    @XmlEnumValue("Sistema nacional de gerenciamento de recursos hídricos")
    VCGE1_SISTEMA_NACIONAL_DE_GERENCIAMENTO_DE_RECURSOS_HIDRICOS("Sistema nacional de gerenciamento de recursos hídricos"),

    @XmlEnumValue("Sistema nacional de informação sobre o meio ambiente - SINIMA")
    VCGE1_SISTEMA_NACIONAL_DE_INFORMACAO_SOBRE_O_MEIO_AMBIENTE_SINIMA("Sistema nacional de informação sobre o meio ambiente - SINIMA"),

    @XmlEnumValue("Sistema nacional de meio ambiente - SISNAMA")
    VCGE1_SISTEMA_NACIONAL_DE_MEIO_AMBIENTE_SISNAMA("Sistema nacional de meio ambiente - SISNAMA"),

    @XmlEnumValue("Sistema político")
    VCGE1_SISTEMA_POLITICO("Sistema político"),

    @XmlEnumValue("Sistema Único de Saúde (SUS)")
    VCGE1_SISTEMA_UNICO_DE_SAUDE_SUS("Sistema Único de Saúde (SUS)"),

    @XmlEnumValue("Sistemas agroflorestais (SAFS)")
    VCGE1_SISTEMAS_AGROFLORESTAIS_SAFS("Sistemas agroflorestais (SAFS)"),

    @XmlEnumValue("Sistemas agroflorestais em bases sustentáveis")
    VCGE1_SISTEMAS_AGROFLORESTAIS_EM_BASES_SUSTENTAVEIS("Sistemas agroflorestais em bases sustentáveis"),

    @XmlEnumValue("Sistemas de certificação e rastreabilidade socioambiental")
    VCGE1_SISTEMAS_DE_CERTIFICACAO_E_RASTREABILIDADE_SOCIOAMBIENTAL("Sistemas de certificação e rastreabilidade socioambiental"),

    @XmlEnumValue("Sistemas de informação")
    VCGE1_SISTEMAS_DE_INFORMACAO("Sistemas de informação"),

    @XmlEnumValue("Sistemas de informação científica do patrimônio genético")
    VCGE1_SISTEMAS_DE_INFORMACAO_CIENTIFICA_DO_PATRIMONIO_GENETICO("Sistemas de informação científica do patrimônio genético"),

    @XmlEnumValue("Sistemas georreferenciados")
    VCGE1_SISTEMAS_GEORREFERENCIADOS("Sistemas georreferenciados"),

    @XmlEnumValue("Sobreexplotação")
    VCGE1_SOBREEXPLOTACAO("Sobreexplotação"),

    @XmlEnumValue("Sobrepesca")
    VCGE1_SOBREPESCA("Sobrepesca"),

    @XmlEnumValue("Sociedade Civil - Organização e participação")
    VCGE1_SOCIEDADE_CIVIL_ORGANIZACAO_E_PARTICIPACAO("Sociedade Civil - Organização e participação"),

    @XmlEnumValue("Software")
    VCGE1_SOFTWARE("Software"),

    @XmlEnumValue("Software livre")
    VCGE1_SOFTWARE_LIVRE("Software livre"),

    @XmlEnumValue("Software proprietário")
    VCGE1_SOFTWARE_PROPRIETARIO("Software proprietário"),

    @XmlEnumValue("Solar")
    VCGE1_SOLAR("Solar"),

    @XmlEnumValue("Solos")
    VCGE1_SOLOS("Solos"),

    @XmlEnumValue("Stricto sensu")
    VCGE1_STRICTO_SENSU("Stricto sensu"),

    @XmlEnumValue("Substâncias que destroem a camada de ozônio")
    VCGE1_SUBSTANCIAS_QUE_DESTROEM_A_CAMADA_DE_OZONIO("Substâncias que destroem a camada de ozônio"),

    @XmlEnumValue("Substâncias químicas")
    VCGE1_SUBSTANCIAS_QUIMICAS("Substâncias químicas"),

    @XmlEnumValue("Sucessão")
    VCGE1_SUCESSAO("Sucessão"),

    @XmlEnumValue("Suinocultura")
    VCGE1_SUINOCULTURA("Suinocultura"),

    @XmlEnumValue("Supletivo")
    VCGE1_SUPLETIVO("Supletivo"),

    @XmlEnumValue("Suprimento de alimento")
    VCGE1_SUPRIMENTO_DE_ALIMENTO("Suprimento de alimento"),

    @XmlEnumValue("Sustentabilidade ambiental")
    VCGE1_SUSTENTABILIDADE_AMBIENTAL("Sustentabilidade ambiental"),

    @XmlEnumValue("Sustentabilidade ambiental da atividade turística")
    VCGE1_SUSTENTABILIDADE_AMBIENTAL_DA_ATIVIDADE_TURISTICA("Sustentabilidade ambiental da atividade turística"),

    @XmlEnumValue("Sustentabilidade dos recursos pesqueiros")
    VCGE1_SUSTENTABILIDADE_DOS_RECURSOS_PESQUEIROS("Sustentabilidade dos recursos pesqueiros"),

    @XmlEnumValue("São Francisco")
    VCGE1_SAO_FRANCISCO("São Francisco"),

    @XmlEnumValue("TV a Cabo")
    VCGE1_TV_A_CABO("TV a Cabo"),

    @XmlEnumValue("TV via satélite")
    VCGE1_TV_VIA_SATELITE("TV via satélite"),

    @XmlEnumValue("Taxa")
    VCGE1_TAXA("Taxa"),

    @XmlEnumValue("Teatro")
    VCGE1_TEATRO("Teatro"),

    @XmlEnumValue("Tecnologia ambientalmente adequadas")
    VCGE1_TECNOLOGIA_AMBIENTALMENTE_ADEQUADAS("Tecnologia ambientalmente adequadas"),

    @XmlEnumValue("Tecnologia da informação e comunicação")
    VCGE1_TECNOLOGIA_DA_INFORMACAO_E_COMUNICACAO("Tecnologia da informação e comunicação"),

    @XmlEnumValue("Tecnologia de alimentos")
    VCGE1_TECNOLOGIA_DE_ALIMENTOS("Tecnologia de alimentos"),

    @XmlEnumValue("Tecnologia de segurança de dados e informação")
    VCGE1_TECNOLOGIA_DE_SEGURANCA_DE_DADOS_E_INFORMACAO("Tecnologia de segurança de dados e informação"),

    @XmlEnumValue("Tecnologia educacional")
    VCGE1_TECNOLOGIA_EDUCACIONAL("Tecnologia educacional"),

    @XmlEnumValue("Tecnologia em saúde")
    VCGE1_TECNOLOGIA_EM_SAUDE("Tecnologia em saúde"),

    @XmlEnumValue("Tecnologia espacial")
    VCGE1_TECNOLOGIA_ESPACIAL("Tecnologia espacial"),

    @XmlEnumValue("Tecnologia industrial")
    VCGE1_TECNOLOGIA_INDUSTRIAL("Tecnologia industrial"),

    @XmlEnumValue("Tecnólogo")
    VCGE1_TECNOLOGO("Tecnólogo"),

    @XmlEnumValue("Telecomunicações")
    VCGE1_TELECOMUNICACOES("Telecomunicações"),

    @XmlEnumValue("Teleconferência")
    VCGE1_TELECONFERENCIA("Teleconferência"),

    @XmlEnumValue("Telefonia")
    VCGE1_TELEFONIA("Telefonia"),

    @XmlEnumValue("Telefonia fixa")
    VCGE1_TELEFONIA_FIXA("Telefonia fixa"),

    @XmlEnumValue("Televisão")
    VCGE1_TELEVISAO("Televisão"),

    @XmlEnumValue("Televisão digital")
    VCGE1_TELEVISAO_DIGITAL("Televisão digital"),

    @XmlEnumValue("Televisão terrestre")
    VCGE1_TELEVISAO_TERRESTRE("Televisão terrestre"),

    @XmlEnumValue("Terminais privativos")
    VCGE1_TERMINAIS_PRIVATIVOS("Terminais privativos"),

    @XmlEnumValue("Termo de anuência prévia (TAP)")
    VCGE1_TERMO_DE_ANUENCIA_PREVIA_TAP("Termo de anuência prévia (TAP)"),

    @XmlEnumValue("Termo de transferência de material (TTM)")
    VCGE1_TERMO_DE_TRANSFERENCIA_DE_MATERIAL_TTM("Termo de transferência de material (TTM)"),

    @XmlEnumValue("Tocantins-Araguaia")
    VCGE1_TOCANTINS_ARAGUAIA("Tocantins-Araguaia"),

    @XmlEnumValue("Tombamento histórico")
    VCGE1_TOMBAMENTO_HISTORICO("Tombamento histórico"),

    @XmlEnumValue("Trabalho")
    VCGE1_TRABALHO("Trabalho"),

    @XmlEnumValue("Trabalho do preso")
    VCGE1_TRABALHO_DO_PRESO("Trabalho do preso"),

    @XmlEnumValue("Trabalho estrangeiro")
    VCGE1_TRABALHO_ESTRANGEIRO("Trabalho estrangeiro"),

    @XmlEnumValue("Trabalho social voluntário")
    VCGE1_TRABALHO_SOCIAL_VOLUNTARIO("Trabalho social voluntário"),

    @XmlEnumValue("Transferência")
    VCGE1_TRANSFERENCIA("Transferência"),

    @XmlEnumValue("Transferência de imóvel")
    VCGE1_TRANSFERENCIA_DE_IMOVEL("Transferência de imóvel"),

    @XmlEnumValue("Transferências financeiras")
    VCGE1_TRANSFERENCIAS_FINANCEIRAS("Transferências financeiras"),

    @XmlEnumValue("Transgenia e sequenciamento")
    VCGE1_TRANSGENIA_E_SEQUENCIAMENTO("Transgenia e sequenciamento"),

    @XmlEnumValue("Transgênicos")
    VCGE1_TRANSGENICOS("Transgênicos"),

    @XmlEnumValue("Transição agroecológica de sistemas de produção")
    VCGE1_TRANSICAO_AGROECOLOGICA_DE_SISTEMAS_DE_PRODUCAO("Transição agroecológica de sistemas de produção"),

    @XmlEnumValue("Transplantes de órgãos e tecidos")
    VCGE1_TRANSPLANTES_DE_ORGAOS_E_TECIDOS("Transplantes de órgãos e tecidos"),

    @XmlEnumValue("Transporte")
    VCGE1_TRANSPORTE("Transporte"),

    @XmlEnumValue("Transporte aquaviário")
    VCGE1_TRANSPORTE_AQUAVIARIO("Transporte aquaviário"),

    @XmlEnumValue("Transporte aquaviário de cargas")
    VCGE1_TRANSPORTE_AQUAVIARIO_DE_CARGAS("Transporte aquaviário de cargas"),

    @XmlEnumValue("Transporte aquaviário de passageiros")
    VCGE1_TRANSPORTE_AQUAVIARIO_DE_PASSAGEIROS("Transporte aquaviário de passageiros"),

    @XmlEnumValue("Transporte aéreo")
    VCGE1_TRANSPORTE_AEREO("Transporte aéreo"),

    @XmlEnumValue("Transporte de patrimônio genético")
    VCGE1_TRANSPORTE_DE_PATRIMONIO_GENETICO("Transporte de patrimônio genético"),

    @XmlEnumValue("Transporte do trabalhador")
    VCGE1_TRANSPORTE_DO_TRABALHADOR("Transporte do trabalhador"),

    @XmlEnumValue("Transporte dutoviário")
    VCGE1_TRANSPORTE_DUTOVIARIO("Transporte dutoviário"),

    @XmlEnumValue("Transporte escolar")
    VCGE1_TRANSPORTE_ESCOLAR("Transporte escolar"),

    @XmlEnumValue("Transporte especial")
    VCGE1_TRANSPORTE_ESPECIAL("Transporte especial"),

    @XmlEnumValue("Transporte ferroviário")
    VCGE1_TRANSPORTE_FERROVIARIO("Transporte ferroviário"),

    @XmlEnumValue("Transporte ferroviário de cargas")
    VCGE1_TRANSPORTE_FERROVIARIO_DE_CARGAS("Transporte ferroviário de cargas"),

    @XmlEnumValue("Transporte ferroviário de passageiros")
    VCGE1_TRANSPORTE_FERROVIARIO_DE_PASSAGEIROS("Transporte ferroviário de passageiros"),

    @XmlEnumValue("Transporte internacional")
    VCGE1_TRANSPORTE_INTERNACIONAL("Transporte internacional"),

    @XmlEnumValue("Transporte internacional de cargas")
    VCGE1_TRANSPORTE_INTERNACIONAL_DE_CARGAS("Transporte internacional de cargas"),

    @XmlEnumValue("Transporte internacional de passageiros")
    VCGE1_TRANSPORTE_INTERNACIONAL_DE_PASSAGEIROS("Transporte internacional de passageiros"),

    @XmlEnumValue("Transporte multimodal")
    VCGE1_TRANSPORTE_MULTIMODAL("Transporte multimodal"),

    @XmlEnumValue("Transporte rodoviário")
    VCGE1_TRANSPORTE_RODOVIARIO("Transporte rodoviário"),

    @XmlEnumValue("Transporte rodoviário de cargas")
    VCGE1_TRANSPORTE_RODOVIARIO_DE_CARGAS("Transporte rodoviário de cargas"),

    @XmlEnumValue("Transporte rodoviário de passageiros")
    VCGE1_TRANSPORTE_RODOVIARIO_DE_PASSAGEIROS("Transporte rodoviário de passageiros"),

    @XmlEnumValue("Transportes coletivos")
    VCGE1_TRANSPORTES_COLETIVOS("Transportes coletivos"),

    @XmlEnumValue("Transportes e trânsito")
    VCGE1_TRANSPORTES_E_TRANSITO("Transportes e trânsito"),

    @XmlEnumValue("Transtornos alimentares")
    VCGE1_TRANSTORNOS_ALIMENTARES("Transtornos alimentares"),

    @XmlEnumValue("Tratado internacional")
    VCGE1_TRATADO_INTERNACIONAL("Tratado internacional"),

    @XmlEnumValue("Tratamento de esgoto")
    VCGE1_TRATAMENTO_DE_ESGOTO("Tratamento de esgoto"),

    @XmlEnumValue("Tratamento de resíduos sólidos")
    VCGE1_TRATAMENTO_DE_RESIDUOS_SOLIDOS("Tratamento de resíduos sólidos"),

    @XmlEnumValue("Tratamento de água")
    VCGE1_TRATAMENTO_DE_AGUA("Tratamento de água"),

    @XmlEnumValue("Tratamento e disposição de esgoto")
    VCGE1_TRATAMENTO_E_DISPOSICAO_DE_ESGOTO("Tratamento e disposição de esgoto"),

    @XmlEnumValue("Tratos culturais")
    VCGE1_TRATOS_CULTURAIS("Tratos culturais"),

    @XmlEnumValue("Trem metropolitano")
    VCGE1_TREM_METROPOLITANO("Trem metropolitano"),

    @XmlEnumValue("Tribunais de alçada")
    VCGE1_TRIBUNAIS_DE_ALCADA("Tribunais de alçada"),

    @XmlEnumValue("Tribunais de justiça")
    VCGE1_TRIBUNAIS_DE_JUSTICA("Tribunais de justiça"),

    @XmlEnumValue("Tribunais estaduais")
    VCGE1_TRIBUNAIS_ESTADUAIS("Tribunais estaduais"),

    @XmlEnumValue("Tribunais federais")
    VCGE1_TRIBUNAIS_FEDERAIS("Tribunais federais"),

    @XmlEnumValue("Tribunais superiores")
    VCGE1_TRIBUNAIS_SUPERIORES("Tribunais superiores"),

    @XmlEnumValue("Tributo")
    VCGE1_TRIBUTO("Tributo"),

    @XmlEnumValue("Troca de lâmpadas")
    VCGE1_TROCA_DE_LAMPADAS("Troca de lâmpadas"),

    @XmlEnumValue("Trânsito")
    VCGE1_TRANSITO("Trânsito"),

    @XmlEnumValue("Turismo")
    VCGE1_TURISMO("Turismo"),

    @XmlEnumValue("Turismo cultural")
    VCGE1_TURISMO_CULTURAL("Turismo cultural"),

    @XmlEnumValue("Turismo de aventura")
    VCGE1_TURISMO_DE_AVENTURA("Turismo de aventura"),

    @XmlEnumValue("Turismo gastronômico")
    VCGE1_TURISMO_GASTRONOMICO("Turismo gastronômico"),

    @XmlEnumValue("Turismo internacional")
    VCGE1_TURISMO_INTERNACIONAL("Turismo internacional"),

    @XmlEnumValue("Turismo religioso")
    VCGE1_TURISMO_RELIGIOSO("Turismo religioso"),

    @XmlEnumValue("Turismo rural")
    VCGE1_TURISMO_RURAL("Turismo rural"),

    @XmlEnumValue("Turismo sustentável na amazônia legal")
    VCGE1_TURISMO_SUSTENTAVEL_NA_AMAZONIA_LEGAL("Turismo sustentável na amazônia legal"),

    @XmlEnumValue("Turismo temático")
    VCGE1_TURISMO_TEMATICO("Turismo temático"),

    @XmlEnumValue("Tutela e curatela")
    VCGE1_TUTELA_E_CURATELA("Tutela e curatela"),

    @XmlEnumValue("Título de eleitor")
    VCGE1_TITULO_DE_ELEITOR("Título de eleitor"),

    @XmlEnumValue("UPA 24 horas")
    VCGE1_UPA_24_HORAS("UPA 24 horas"),

    @XmlEnumValue("Unidade básica de saúde")
    VCGE1_UNIDADE_BASICA_DE_SAUDE("Unidade básica de saúde"),

    @XmlEnumValue("Unidades de conservação")
    VCGE1_UNIDADES_DE_CONSERVACAO("Unidades de conservação"),

    @XmlEnumValue("Unidades de conservação (UCS)")
    VCGE1_UNIDADES_DE_CONSERVACAO_UCS("Unidades de conservação (UCS)"),

    @XmlEnumValue("Unidades de manejo em florestas públicas")
    VCGE1_UNIDADES_DE_MANEJO_EM_FLORESTAS_PUBLICAS("Unidades de manejo em florestas públicas"),

    @XmlEnumValue("Unidades de saúde")
    VCGE1_UNIDADES_DE_SAUDE("Unidades de saúde"),

    @XmlEnumValue("União estável")
    VCGE1_UNIAO_ESTAVEL("União estável"),

    @XmlEnumValue("Urbanismo")
    VCGE1_URBANISMO("Urbanismo"),

    @XmlEnumValue("Uruguai")
    VCGE1_URUGUAI("Uruguai"),

    @XmlEnumValue("Uso do solo")
    VCGE1_USO_DO_SOLO("Uso do solo"),

    @XmlEnumValue("Uso e ocupação das florestas")
    VCGE1_USO_E_OCUPACAO_DAS_FLORESTAS("Uso e ocupação das florestas"),

    @XmlEnumValue("Uso eficiente e racional das florestas")
    VCGE1_USO_EFICIENTE_E_RACIONAL_DAS_FLORESTAS("Uso eficiente e racional das florestas"),

    @XmlEnumValue("Uso público das unidades de conservação")
    VCGE1_USO_PUBLICO_DAS_UNIDADES_DE_CONSERVACAO("Uso público das unidades de conservação"),

    @XmlEnumValue("Uso sustentável das unidades de conservação")
    VCGE1_USO_SUSTENTAVEL_DAS_UNIDADES_DE_CONSERVACAO("Uso sustentável das unidades de conservação"),

    @XmlEnumValue("Uso sustentável de recursos pesqueiros")
    VCGE1_USO_SUSTENTAVEL_DE_RECURSOS_PESQUEIROS("Uso sustentável de recursos pesqueiros"),

    @XmlEnumValue("Usos múltiplos de recursos hídricos")
    VCGE1_USOS_MULTIPLOS_DE_RECURSOS_HIDRICOS("Usos múltiplos de recursos hídricos"),

    @XmlEnumValue("Utilização do patrimônio genético")
    VCGE1_UTILIZACAO_DO_PATRIMONIO_GENETICO("Utilização do patrimônio genético"),

    @XmlEnumValue("Vacinas")
    VCGE1_VACINAS("Vacinas"),

    @XmlEnumValue("Vacinação")
    VCGE1_VACINACAO("Vacinação"),

    @XmlEnumValue("Vacinação do viajante")
    VCGE1_VACINACAO_DO_VIAJANTE("Vacinação do viajante"),

    @XmlEnumValue("Validade diploma educação superior")
    VCGE1_VALIDADE_DIPLOMA_EDUCACAO_SUPERIOR("Validade diploma educação superior"),

    @XmlEnumValue("Valoração do patrimônio genético")
    VCGE1_VALORACAO_DO_PATRIMONIO_GENETICO("Valoração do patrimônio genético"),

    @XmlEnumValue("Valoração dos serviços ambientais prestados pelas florestas e demais forma de vegetação nativa")
    VCGE1_VALORACAO_DOS_SERVICOS_AMBIENTAIS_PRESTADOS_PELAS_FLORESTAS_E_DEMAIS_FORMA_DE_VEGETACAO_NATIVA("Valoração dos serviços ambientais prestados pelas florestas e demais forma de vegetação nativa"),

    @XmlEnumValue("Valoração dos serviços ambientais prestados pelas florestas e demais formas de vegetação nativa")
    VCGE1_VALORACAO_DOS_SERVICOS_AMBIENTAIS_PRESTADOS_PELAS_FLORESTAS_E_DEMAIS_FORMAS_DE_VEGETACAO_NATIVA("Valoração dos serviços ambientais prestados pelas florestas e demais formas de vegetação nativa"),

    @XmlEnumValue("Valoração econômica dos recursos naturais")
    VCGE1_VALORACAO_ECONOMICA_DOS_RECURSOS_NATURAIS("Valoração econômica dos recursos naturais"),

    @XmlEnumValue("Valorização de resíduos florestais")
    VCGE1_VALORIZACAO_DE_RESIDUOS_FLORESTAIS("Valorização de resíduos florestais"),

    @XmlEnumValue("Valorização dos resíduos sólidos")
    VCGE1_VALORIZACAO_DOS_RESIDUOS_SOLIDOS("Valorização dos resíduos sólidos"),

    @XmlEnumValue("Variedades crioulas")
    VCGE1_VARIEDADES_CRIOULAS("Variedades crioulas"),

    @XmlEnumValue("Variedades de sementes e mudas")
    VCGE1_VARIEDADES_DE_SEMENTES_E_MUDAS("Variedades de sementes e mudas"),

    @XmlEnumValue("Vestibular")
    VCGE1_VESTIBULAR("Vestibular"),

    @XmlEnumValue("Veterinária")
    VCGE1_VETERINARIA("Veterinária"),

    @XmlEnumValue("Vetores")
    VCGE1_VETORES("Vetores"),

    @XmlEnumValue("Veículos de comunicação")
    VCGE1_VEICULOS_DE_COMUNICACAO("Veículos de comunicação"),

    @XmlEnumValue("Vigilância sanitária")
    VCGE1_VIGILANCIA_SANITARIA("Vigilância sanitária"),

    @XmlEnumValue("Violência")
    VCGE1_VIOLENCIA("Violência"),

    @XmlEnumValue("Violência contra o idoso")
    VCGE1_VIOLENCIA_CONTRA_O_IDOSO("Violência contra o idoso"),

    @XmlEnumValue("Violência contra o menor")
    VCGE1_VIOLENCIA_CONTRA_O_MENOR("Violência contra o menor"),

    @XmlEnumValue("Violência de gênero")
    VCGE1_VIOLENCIA_DE_GENERO("Violência de gênero"),

    @XmlEnumValue("Violência doméstica")
    VCGE1_VIOLENCIA_DOMESTICA("Violência doméstica"),

    @XmlEnumValue("Violência urbana")
    VCGE1_VIOLENCIA_URBANA("Violência urbana"),

    @XmlEnumValue("Visitação de unidades de conservação")
    VCGE1_VISITACAO_DE_UNIDADES_DE_CONSERVACAO("Visitação de unidades de conservação"),

    @XmlEnumValue("Web TV")
    VCGE1_WEB_TV("Web TV"),

    @XmlEnumValue("Xilotecas")
    VCGE1_XILOTECAS("Xilotecas"),

    @XmlEnumValue("Zona costeira e marinha")
    VCGE1_ZONA_COSTEIRA_E_MARINHA("Zona costeira e marinha"),

    @XmlEnumValue("Zona econômica exclusiva")
    VCGE1_ZONA_ECONOMICA_EXCLUSIVA("Zona econômica exclusiva"),

    @XmlEnumValue("Zonas úmidas")
    VCGE1_ZONAS_UMIDAS("Zonas úmidas"),

    @XmlEnumValue("Zoneamento agro-ecológico")
    VCGE1_ZONEAMENTO_AGRO_ECOLOGICO("Zoneamento agro-ecológico"),

    @XmlEnumValue("Zoneamento ecológico e econômico - ZEE")
    VCGE1_ZONEAMENTO_ECOLOGICO_E_ECONOMICO_ZEE("Zoneamento ecológico e econômico - ZEE"),

    @XmlEnumValue("Zootecnia")
    VCGE1_ZOOTECNIA("Zootecnia"),

    @XmlEnumValue("termo_vcge_1")
    VCGE1_TERMO_VCGE_1("termo_vcge_1"),

    @XmlEnumValue("Água")
    VCGE1_AGUA("Água"),

    @XmlEnumValue("Água de lastro")
    VCGE1_AGUA_DE_LASTRO("Água de lastro"),

    @XmlEnumValue("Águas")
    VCGE1_AGUAS("Águas"),

    @XmlEnumValue("Águas de chuva")
    VCGE1_AGUAS_DE_CHUVA("Águas de chuva"),

    @XmlEnumValue("Águas subterrâneas")
    VCGE1_AGUAS_SUBTERRANEAS("Águas subterrâneas"),

    @XmlEnumValue("Águas superficiais")
    VCGE1_AGUAS_SUPERFICIAIS("Águas superficiais"),

    @XmlEnumValue("Águas transfronteiriças")
    VCGE1_AGUAS_TRANSFRONTEIRICAS("Águas transfronteiriças"),

    @XmlEnumValue("Águas transfronteriças")
    VCGE1_AGUAS_TRANSFRONTERICAS("Águas transfronteriças"),

    @XmlEnumValue("Álcool")
    VCGE1_ALCOOL("Álcool"),

    @XmlEnumValue("Área de preservação permanente (APP)")
    VCGE1_AREA_DE_PRESERVACAO_PERMANENTE_APP("Área de preservação permanente (APP)"),

    @XmlEnumValue("Área de relevante interesse ecológico")
    VCGE1_AREA_DE_RELEVANTE_INTERESSE_ECOLOGICO("Área de relevante interesse ecológico"),

    @XmlEnumValue("Áreas ambientalmente sensíveis")
    VCGE1_AREAS_AMBIENTALMENTE_SENSIVEIS("Áreas ambientalmente sensíveis"),

    @XmlEnumValue("Áreas de proteção ambiental")
    VCGE1_AREAS_DE_PROTECAO_AMBIENTAL("Áreas de proteção ambiental"),

    @XmlEnumValue("Áreas degradadas")
    VCGE1_AREAS_DEGRADADAS("Áreas degradadas"),

    @XmlEnumValue("Áreas desmatadas")
    VCGE1_AREAS_DESMATADAS("Áreas desmatadas"),

    @XmlEnumValue("Áreas embargadas pelo IBAMA")
    VCGE1_AREAS_EMBARGADAS_PELO_IBAMA("Áreas embargadas pelo IBAMA"),

    @XmlEnumValue("Áreas prioritárias para a biodiversidade")
    VCGE1_AREAS_PRIORITARIAS_PARA_A_BIODIVERSIDADE("Áreas prioritárias para a biodiversidade"),

    @XmlEnumValue("Áreas protegidas")
    VCGE1_AREAS_PROTEGIDAS("Áreas protegidas"),

    @XmlEnumValue("Áreas protegidas com reconhecimento internacional")
    VCGE1_AREAS_PROTEGIDAS_COM_RECONHECIMENTO_INTERNACIONAL("Áreas protegidas com reconhecimento internacional"),

    @XmlEnumValue("Áreas susceptíveis à desertificação")
    VCGE1_AREAS_SUSCEPTIVEIS_A_DESERTIFICACAO("Áreas susceptíveis à desertificação"),

    @XmlEnumValue("Ética de profissionais de saúde")
    VCGE1_ETICA_DE_PROFISSIONAIS_DE_SAUDE("Ética de profissionais de saúde"),

    @XmlEnumValue("Ética política")
    VCGE1_ETICA_POLITICA("Ética política"),

    @XmlEnumValue("Ética publica")
    VCGE1_ETICA_PUBLICA("Ética publica"),

    @XmlEnumValue("Óleo vegetal")
    VCGE1_OLEO_VEGETAL("Óleo vegetal"),

    @XmlEnumValue("Ópera")
    VCGE1_OPERA("Ópera"),

    @XmlEnumValue("Órgãos de execução penal")
    VCGE1_ORGAOS_DE_EXECUCAO_PENAL("Órgãos de execução penal"),

    @XmlEnumValue("Órgãos públicos gestores de recursos hídricos")
    VCGE1_ORGAOS_PUBLICOS_GESTORES_DE_RECURSOS_HIDRICOS("Órgãos públicos gestores de recursos hídricos"),

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
    VCGE2_AGUA("Água");

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
