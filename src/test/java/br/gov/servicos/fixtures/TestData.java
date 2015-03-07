package br.gov.servicos.fixtures;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.LinhaDaVida;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;

import static java.util.Arrays.asList;

public class TestData {

    public static final Servico SERVICO = new Servico()
            .withId("1")
            .withTitulo("Título")
            .withDescricao("Descrição")
            .withUrl("url://site")
            .withUrlAgendamento("url://agendamento")
            .withTaxa("Gratuita")
            .withPrestador(new Orgao().withId("1").withNome("Nome").withTelefone("123"))
            .withResponsavel(new Orgao().withId("2").withNome("Nome").withTelefone(null))
            .withAreasDeInteresse(asList(new AreaDeInteresse().withId("3").withTitulo("Área de Interesse")))
            .withLinhasDaVida(asList(new LinhaDaVida().withId("4").withTitulo("Linha da Vida")))
            .withEventosDasLinhasDaVida(asList("Eventos das Linhas da Vida"))
            .withAcessos(0L)
            .withAtivacoes(0L);

    public static final Conteudo CONTEUDO = new Conteudo()
            .withTitulo("Título")
            .withHtml("<h1>Título</h1>\n\nConteúdo");

    public static final Busca BUSCA = new Busca()
            .withTermo("um serviço")
            .withResultados(1).withAtivacoes(1);
}
