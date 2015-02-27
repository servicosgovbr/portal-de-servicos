package br.gov.servicos.fixtures;

import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.LinhaDaVida;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;

import static java.util.Arrays.asList;

public class TestData {

    public static final Servico SERVICO = new Servico(
            "1",
            "Título",
            "Descrição",
            "url://site",
            "Gratuita",
            new Orgao("1", "Nome", "123"),
            new Orgao("2", "Nome", null),
            asList(new AreaDeInteresse("3", "Área de Interesse")),
            asList(new LinhaDaVida("4", "Linha da Vida")),
            asList("Eventos das Linhas da Vida"),
            0L, 0L
    );
}
