package br.gov.servicos.importador;

import br.gov.servicos.foundation.git.ComandosGit;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Component
class ImportadorCartasDeServico {

    ComandosGit git;
    String urlRepositorio;
    String caminhoLocal;
    boolean deveImportar;

    @Autowired
    ImportadorCartasDeServico(
            ComandosGit git,
            @Value("${gds.cartas.repositorio}") String urlRepositorio,
            @Value("${gds.cartas.local}") String caminhoLocal,
            @Value("${flags.importar.cartas}") Boolean deveImportar) {

        this.git = git;
        this.deveImportar = deveImportar;
        this.urlRepositorio = urlRepositorio;
        this.caminhoLocal = caminhoLocal;
    }

    void importar() {
        if (!deveImportar) {
            log.info("Importação de cartas de servico desligada (FLAGS_IMPORTAR_CARTAS_DE_SERVICO=false)");
            return;
        }

        log.info("Importando cartas de serviço de {} para {}", urlRepositorio, caminhoLocal);
        git.clonaOuAtualizaRepositorio(urlRepositorio, caminhoLocal);
    }

}
