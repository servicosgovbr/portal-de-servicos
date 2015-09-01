package br.gov.servicos.cms;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
public class ConteudoHtml {

    String id;
    String nome;
    String html;

    public ConteudoHtml() {
        this(null, null, null);
    }

}
