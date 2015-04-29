package br.gov.servicos.legado;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static br.gov.servicos.Profiles.PRODUCTION;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Profile(PRODUCTION)
class ImportadorAutomatico {

    Importador importador;

    @Autowired
    ImportadorAutomatico(Importador importador) {
        this.importador = importador;
    }

    @PostConstruct
    @SneakyThrows
    void appCarregada() {
        log.info("Importando serviços legados para o ElasticSearch");
        importador.importar();
        log.info("Serviços importados com sucesso");
    }

}
