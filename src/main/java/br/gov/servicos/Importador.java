package br.gov.servicos;

import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.indexador.ImportadorConteudo;
import br.gov.servicos.legado.ImportadorLegado;
import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
public class Importador {
    GuiaDeServicosIndex indices;
    ImportadorLegado legado;
    ImportadorConteudo conteudo;

    @Autowired
    public Importador(GuiaDeServicosIndex indices, ImportadorLegado legado, ImportadorConteudo conteudo) {
        this.indices = indices;
        this.legado = legado;
        this.conteudo = conteudo;
    }

    @ManagedOperation
    @SneakyThrows
    public Map<String, Object> importar() {
        log.info("Iniciando importação");
        indices.recriar();

        return ImmutableMap.of(
                "servicos", legado.importar(),
                "conteudos", conteudo.importar());
    }
}
