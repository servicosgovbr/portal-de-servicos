package br.gov.servicos.fixtures;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.metricas.Opiniao;
import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

public class TestData {

    public static final Servico SERVICO = new Servico()
            .withId("1")
            .withTitulo("Título")
            .withDescricao("Descrição serviço")
            .withUrl("url://site")
            .withUrlAgendamento("url://agendamento")
            .withTaxa("Gratuita")
            .withPrestador(new Orgao().withId("1").withNome("Nome").withTelefone("123"))
            .withResponsavel(new Orgao().withId("2").withNome("Nome").withTelefone(null))
            .withPublicosAlvo(singletonList(new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos")))
            .withAreasDeInteresse(singletonList(new AreaDeInteresse().withId("3").withTitulo("Área de Interesse")))
            .withLinhasDaVida(singletonList(new LinhaDaVida().withId("4").withTitulo("Linha da Vida")))
            .withEventosDasLinhasDaVida(singletonList("Eventos das Linhas da Vida"));

    public static final ConteudoHtml CONTEUDO_HTML = new ConteudoHtml()
            .withId("pagina")
            .withTitulo("Título")
            .withHtml("<h1>Título</h1>\n\nConteúdo");

    public static final Conteudo CONTEUDO = new Conteudo()
            .withId("pagina")
            .withTitulo("Título")
            .withConteudo("Descrição conteúdo");

    public static final Opiniao OPINIAO = new Opiniao()
            .withUrl("/servico/foo")
            .withQueryString("bar=baz")
            .withTimestamp(123L)
            .withConteudoEncontrado(true)
            .withMensagem("Otimo site");

    public static final List<Orgao> ORGAOS = Arrays.asList(
            new Orgao().withId("arquivo-nacional").withNome("Arquivo Nacional").withTelefone("(61) 1111 1111"),
            new Orgao().withId("banco-central-do-brasil-bcb").withNome("Banco Central do Brasil").withTelefone("(61) 2222 2222")
    );

    public static final List<LinhaDaVida> LINHAS_DA_VIDA = Arrays.asList(
            new LinhaDaVida().withId("ter-um-imovel").withTitulo("Ter um Imóvel"),
            new LinhaDaVida().withId("obter-credito-e-apoio-financeiro").withTitulo("Obter Crédito e Apoio Financeiro")
    );
}
