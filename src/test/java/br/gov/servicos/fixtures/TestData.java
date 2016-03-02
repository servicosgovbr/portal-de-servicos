package br.gov.servicos.fixtures;

import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.cms.PaginaTematica;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.ServicoXML;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class TestData {

    public static final PaginaTematica PAGINA_TEMATICA = new PaginaTematica()
            .withId("pagina-orgao")
            .withTipoConteudo("pagina-tematica")
            .withNome("Página órgão")
            .withConteudo("Descrição conteúdo");

    public static final OrgaoXML PAGINA_ORGAO = new OrgaoXML()
            .withId("pagina-orgao")
            .withTipoConteudo("orgao")
            .withNome("Página órgão")
            .withConteudo("Descrição conteúdo");

    public static final ServicoXML SERVICO = new ServicoXML()
            .withId("exemplo-de-servico")
            .withNome("Exemplo de serviço")
            .withDescricao("Descrição serviço")
            .withOrgao(PAGINA_ORGAO);

    public static final PaginaEstatica PAGINA_ESTATICA_DE_TEMATICA = new PaginaEstatica()
            .withId("pagina-orgao")
            .withTipoConteudo("pagina-tematica")
            .withNome("Página órgão")
            .withConteudo("Descrição conteúdo");

    public static final List<OrgaoXML> ORGAOS = unmodifiableList(asList(
            new OrgaoXML().withId("arquivo-nacional-an"),
            new OrgaoXML().withId("banco-central-do-brasil-bcb")
    ));

    public static final PaginaEstatica PAGINA_ESTATICA_DE_SERVICO = PaginaEstatica.fromServico(SERVICO);
}
