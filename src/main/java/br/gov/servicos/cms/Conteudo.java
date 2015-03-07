package br.gov.servicos.cms;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
public class Conteudo {

    String titulo;
    String html;

    public Conteudo() {
        this(null, null);
    }
}
