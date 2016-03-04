package br.gov.servicos.importador;

import lombok.AccessLevel;
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

    RepositorioCartasServico repositorioCartasServico;

    ImportadorServicos servicos;

    ImportadorParaPaginasEstaticas estaticas;

    ImportadorParaPaginasTematicas tematicas;

    ImportadorParaPaginasDeOrgao orgaos;

    @Autowired
    public Importador(RepositorioCartasServico repositorioCartasServico,
                      ImportadorServicos servicos,
                      ImportadorParaPaginasEstaticas estaticas,
                      ImportadorParaPaginasTematicas tematicas,
                      ImportadorParaPaginasDeOrgao orgaos) {
        this.repositorioCartasServico = repositorioCartasServico;
        this.servicos = servicos;
        this.estaticas = estaticas;
        this.tematicas = tematicas;
        this.orgaos = orgaos;
    }

    @ManagedOperation
    public Map<String, Object> importar() {
        log.info("Iniciando a importação");

        Map<String, Object> retorno = new HashMap<>();
        if(!repositorioCartasServico.contemAtualizacoes()) {
            return retorno;
        }

        retorno.put("servicos", servicos.importar(repositorioCartasServico));
        retorno.put("paginas-estaticas", estaticas.importar(repositorioCartasServico));
        retorno.put("paginas-tematicas", tematicas.importar(repositorioCartasServico));
        retorno.put("orgaos", orgaos.importar(repositorioCartasServico));

        log.info("Importação concluída com sucesso");
        return retorno;
    }

}
