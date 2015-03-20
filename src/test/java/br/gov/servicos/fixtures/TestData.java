package br.gov.servicos.fixtures;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;

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
            .withPublicosAlvo(asList(new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos cidadãos")))
            .withAreasDeInteresse(asList(new AreaDeInteresse().withId("3").withTitulo("Área de Interesse")))
            .withLinhasDaVida(asList(new LinhaDaVida().withId("4").withTitulo("Linha da Vida")))
            .withEventosDasLinhasDaVida(asList("Eventos das Linhas da Vida"));

    public static final Conteudo CONTEUDO = new Conteudo()
            .withTitulo("Título")
            .withHtml("<h1>Título</h1>\n\nConteúdo");
}
