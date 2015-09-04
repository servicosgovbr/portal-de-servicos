package br.gov.servicos.importador;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ImportadorAutomatico {

    Importador importador;
    boolean flag;

    @Autowired
    ImportadorAutomatico(Importador importador, @Value("${flags.importar.automaticamente}") Boolean flag) {
        this.importador = importador;
        this.flag = flag;
    }

    @PostConstruct
    @Scheduled(cron = "${pds.importador.cron}")
    void appCarregada() {
        if (!flag) {
            log.info("Importação automática desligada (FLAGS_IMPORTAR_AUTOMATICAMENTE=false)");
            return;
        }

        importador.importar();
    }

}
