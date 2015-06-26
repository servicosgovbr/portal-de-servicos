package br.gov.servicos.fixtures;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.metricas.Opiniao;
import br.gov.servicos.servico.*;
import br.gov.servicos.servico.areaDeInteresse.AreaDeInteresse;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class TestData {

    public static final Servico SERVICO = new Servico()
            .withId("exemplo-de-servico")
            .withTitulo("Exemplo de serviço")
            .withDescricao("Descrição serviço")
            .withUrl("url://site")
            .withUrlAgendamento("url://agendamento")
            .withTaxa("R$ 10,50")
            .withPrestador(new Orgao().withId("orgao-prestador").withNome("Órgão prestador"))
            .withResponsavel(new Orgao().withId("orgao-responsavel").withNome("Órgão responsável"))
            .withPublicosAlvo(singletonList(new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos")))
            .withAreasDeInteresse(singletonList(new AreaDeInteresse().withId("area-de-interesse").withArea("Área de Interesse")))
            .withLinhasDaVida(singletonList(new LinhaDaVida().withId("linha-da-vida").withTitulo("Linha da Vida")))
            .withCanaisDePrestacao(singletonList(new CanalDePrestacao().withTipo("web").withDescricao("Web").withUrl("http://canal-prestacao")))
            .withInformacoesUteis(singletonList(new InformacaoUtil().withTipo("app").withDescricao("Aplicativo móvel").withUrl("http://app-movel")));

    public static final Conteudo CONTEUDO_DE_SERVICO = Conteudo.fromServico(SERVICO);

    public static final ConteudoHtml CONTEUDO_HTML = new ConteudoHtml()
            .withId("pagina")
            .withTitulo("Título")
            .withHtml("<h1>Título</h1>\n\nConteúdo");

    public static final Conteudo CONTEUDO = new Conteudo()
            .withId("pagina-orgao")
            .withTipoConteudo("orgao")
            .withTitulo("Página órgão")
            .withConteudo("Descrição conteúdo");

    public static final Opiniao OPINIAO = new Opiniao()
            .withUrl("/servico/foo")
            .withQueryString("bar=baz")
            .withTimestamp(123L)
            .withConteudoEncontrado(true)
            .withMensagem("Otimo site");

    public static final List<Orgao> ORGAOS = unmodifiableList(asList(
            new Orgao().withId("arquivo-nacional-an").withNome("Arquivo Nacional").withTelefone("(61) 1111 1111"),
            new Orgao().withId("banco-central-do-brasil-bcb").withNome("Banco Central do Brasil").withTelefone("(61) 2222 2222")
    ));

    public static final List<LinhaDaVida> LINHAS_DA_VIDA = unmodifiableList(asList(
            new LinhaDaVida().withId("imoveis").withTitulo("Imóveis"),
            new LinhaDaVida().withId("apoio-financeiro-e-credito").withTitulo("Apoio financeiro e crédito")
    ));
}
