package br.gov.servicos.importador;

import br.gov.servicos.config.GuiaDeServicosIndex;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
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

    GuiaDeServicosIndex indices;
    ImportadorCartasDeServico cartasDeServico;
    ImportadorV1 v1;
    ImportadorConteudo conteudo;

    @Autowired
    public Importador(GuiaDeServicosIndex indices,
                      ImportadorV1 v1,
                      ImportadorConteudo conteudo,
                      ImportadorCartasDeServico cartasDeServico) {

        this.indices = indices;
        this.v1 = v1;
        this.conteudo = conteudo;
        this.cartasDeServico = cartasDeServico;
    }

    @ManagedOperation
    @SneakyThrows
    public Map<String, Object> importar() {
        log.info("Iniciando importação");
        indices.recriar();

        File repositorioCartas = Files.createTempDirectory("guia-de-servicos").toFile();
        repositorioCartas.deleteOnExit();

        Map<String, Object> retorno = new HashMap<>();
        retorno.put("versao-cartas-de-servico", cartasDeServico.importar(repositorioCartas));
        retorno.put("servicos", v1.importar(repositorioCartas));
        retorno.put("conteudos", conteudo.importar());


        if (!repositorioCartas.delete())
            log.warn("Não foi possível excluir clone local do repositório de cartas de servico em {}",
                    repositorioCartas.getAbsolutePath());

        log.info("Importação concluída com sucesso");
        return retorno;
    }
}
