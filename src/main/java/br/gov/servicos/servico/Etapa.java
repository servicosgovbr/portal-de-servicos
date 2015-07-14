package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class Etapa {

    String titulo;
    String descricao;
    List<Custo> custos;
    Boolean custoTemExcecoes;
    List<String> documentos;
    List<CanalDePrestacao> canaisDePrestacao;

    public boolean isEmpty() {
        return isNullOrEmpty(titulo) &&
                isNullOrEmpty(descricao) &&
                ofNullable(custos).orElse(emptyList()).isEmpty() &&
                ofNullable(documentos).orElse(emptyList()).isEmpty() &&
                ofNullable(canaisDePrestacao).orElse(emptyList()).isEmpty();
    }

}

