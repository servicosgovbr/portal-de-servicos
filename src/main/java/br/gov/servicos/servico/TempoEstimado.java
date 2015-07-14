package br.gov.servicos.servico;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TempoEstimado {

    String tipo; // 'entre' ou 'até'

    String entreMinimo;
    String entreTipoMinimo; // minutos, horas, etc

    String ateMaximo;
    String ateTipoMaximo; // minutos, horas, etc

    String entreMaximo;
    String entreTipoMaximo; // minutos, horas, etc

    String excecoes;

    public TempoEstimado() {
        this(null, null, null, null, null, null, null, null);
    }

    public boolean isEmpty() {
        return isNullOrEmpty(tipo);
    }

    @Override
    public String toString() {
        if (tipo.equals("entre")) {
            if(entreTipoMinimo.equals(entreTipoMaximo)) {
                return format("Entre %s e %s %s", entreMinimo, entreMaximo, entreTipoMaximo);
            }
            return format("Entre %s %s e %s %s", entreMinimo, entreTipoMinimo, entreMaximo, entreTipoMaximo);
        }
        return format("Até %s %s", ateMaximo, ateTipoMaximo);
    }
}
