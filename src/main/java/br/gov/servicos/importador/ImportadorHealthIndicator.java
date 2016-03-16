package br.gov.servicos.importador;

import lombok.experimental.FieldDefaults;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorHealthIndicator implements HealthIndicator {

    private static final int MAX_SIZE = 10;
    private static final List<String> ULTIMAS_ATUALIZACOES = new ArrayList<>();

    @Override
    public Health health() {
        if (ULTIMAS_ATUALIZACOES.isEmpty()) {
            return Health.unknown().build();
        }

        return new Health.Builder()
                .up()
                .withDetail("atualizacoes", ULTIMAS_ATUALIZACOES)
                .build();
    }

    public static void importacaoConcluida() {
        ULTIMAS_ATUALIZACOES.add(new Date().toString());
        if (ULTIMAS_ATUALIZACOES.size() > MAX_SIZE) {
            ULTIMAS_ATUALIZACOES.remove(0);
        }
    }
}
