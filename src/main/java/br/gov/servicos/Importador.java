package br.gov.servicos;

import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.indexador.ImportadorConteudo;
import br.gov.servicos.legado.ImportadorLegado;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
public class Importador {
    GuiaDeServicosIndex guiaDeServicosIndex;
    ImportadorLegado legado;
    ImportadorConteudo conteudo;

    @Autowired
    public Importador(GuiaDeServicosIndex guiaDeServicosIndex, ImportadorLegado legado, ImportadorConteudo conteudo) {
        this.guiaDeServicosIndex = guiaDeServicosIndex;
        this.legado = legado;
        this.conteudo = conteudo;
    }

    public void importar() {
        try {
            log.info("Recriando índices");
            this.guiaDeServicosIndex.recriar();

            log.info("Iniciando importação");
            legado.importar();
            conteudo.importar();

        } catch (Exception e) {
            log.error("Erro durante a importação", e);
            throw new RuntimeException(e);
        }
        log.info("Importação finalizada");
    }
}