package br.gov.servicos.importador;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa dados para o ElasticSearch"
)
public class Importador {

    ImportadorV3 v3;
    ImportadorConteudo conteudo;

    @Autowired
    public Importador(ImportadorV3 v3,
                      ImportadorConteudo conteudo) {
        this.v3 = v3;
        this.conteudo = conteudo;
    }

    @ManagedOperation
    @SneakyThrows
    public Map<String, Object> importar() {
        log.info("Iniciando importação");

        Map<String, Object> retorno = new HashMap<>();
        retorno.put("servicos-v3", v3.importar());
        retorno.put("conteudos", conteudo.importar());

        log.info("Importação concluída com sucesso");
        return retorno;
    }
}
