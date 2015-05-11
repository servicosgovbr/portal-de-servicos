package br.gov.servicos;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.indexador.ImportadorConteudo;
import br.gov.servicos.legado.ImportadorLegado;
import br.gov.servicos.servico.Servico;
import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
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
    GuiaDeServicosIndex guiaDeServicosIndex;
    ImportadorLegado legado;
    ImportadorConteudo conteudo;

    @Autowired
    public Importador(GuiaDeServicosIndex guiaDeServicosIndex, ImportadorLegado legado, ImportadorConteudo conteudo) {
        this.guiaDeServicosIndex = guiaDeServicosIndex;
        this.legado = legado;
        this.conteudo = conteudo;
    }

    @ManagedOperation
    public Map<String, Object> importar() {
        try {
            log.info("Recriando índices");
            this.guiaDeServicosIndex.recriar();

            log.info("Iniciando importação");

            Iterable<Servico> servicos = legado.importar();
            Iterable<Conteudo> conteudos = conteudo.importar();

            log.info("Importação finalizada com sucesso");

            return ImmutableMap.of("servicos", servicos, "conteudos", conteudos);
        } catch (Exception e) {
            log.error("Erro durante a importação", e);
            throw new RuntimeException(e);
        }
    }
}
