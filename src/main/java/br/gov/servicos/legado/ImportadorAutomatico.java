package br.gov.servicos.legado;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    ImportadorAutomatico(Importador importador, @Value("${gds.importar-automaticamente}") boolean flag) {
        this.importador = importador;
        this.flag = flag;
    }

    @PostConstruct
    @SneakyThrows
    void appCarregada() {
        if(!flag) {
            log.info("Importação automática desligada (GDS_IMPORTAR_AUTOMATICAMENTE=false)");
            return;
        }

        log.info("Importando serviços legados para o ElasticSearch");
        importador.importar();
        log.info("Serviços importados com sucesso");
    }

}
