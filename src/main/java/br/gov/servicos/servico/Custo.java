package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import static com.google.common.base.Strings.isNullOrEmpty;

@Value
@Wither
@AllArgsConstructor
public class Custo {

    String descricao;
    String valor;

    public Custo() {
        this(null, null);
    }

    public boolean isEmpty() {
        return isNullOrEmpty(descricao) || isNullOrEmpty(valor);
    }
}

