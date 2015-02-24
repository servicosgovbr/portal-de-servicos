package br.gov.servicos.dominio;

import lombok.Value;

@Value
public class LinhaDaVida {

    String titulo;

    Long servicos;

    public LinhaDaVida() {
        this(null, null);
    }

    public LinhaDaVida(String titulo, Long servicos) {
        this.titulo = titulo;
        this.servicos = servicos;
    }
}
