package br.gov.servicos.importador;

import br.gov.servicos.foundation.git.ComandosGit;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

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
            @Value("gds.cartas-de-servico.url-repositorio") String urlRepositorio,
            @Value("gds.cartas-de-servico.caminho-local") String caminhoLocal,
            @Value("flags.importar.cartas-de-servico") boolean deveImportar) {

        this.git = git;
        this.deveImportar = deveImportar;
        this.urlRepositorio = urlRepositorio;
        this.caminhoLocal = caminhoLocal;
    }

    void importar() {
        if (!deveImportar) return;
        git.clonaOuAtualizaRepositorio(urlRepositorio, caminhoLocal);
    }
}
