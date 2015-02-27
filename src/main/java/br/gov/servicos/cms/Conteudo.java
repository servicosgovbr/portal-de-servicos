package br.gov.servicos.cms;

import lombok.Value;

@Value
public class Conteudo {

    String titulo;
    String html;

    public Conteudo(String titulo, String html) {
        this.titulo = titulo;
        this.html = html;
    }
}
