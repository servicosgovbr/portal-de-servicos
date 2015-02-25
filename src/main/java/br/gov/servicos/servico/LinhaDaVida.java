package br.gov.servicos.servico;

import lombok.Value;

@Value
class LinhaDaVida {

    String titulo;

    Long servicos;

    LinhaDaVida() {
        this(null, null);
    }

    LinhaDaVida(String titulo, Long servicos) {
        this.titulo = titulo;
        this.servicos = servicos;
    }
}
