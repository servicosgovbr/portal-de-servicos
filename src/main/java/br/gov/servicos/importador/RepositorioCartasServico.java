package br.gov.servicos.importador;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE)
public class RepositorioCartasServico {

    final String urlRepositorio;
    File caminhoLocal;

    @Autowired
    public RepositorioCartasServico(@Value("${pds.cartas.repositorio}") String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }

    @SneakyThrows
    public void prepararRepositorio() {
        caminhoLocal = clonarRepositorio(criarDiretorioTemporario());
    }

    public Resource acessarDocumento(String caminhoDocumento) {
        log.info("Acessando documento {} em {}", caminhoDocumento, caminhoLocal);
        return new FileSystemResource(caminhoLocal.toPath().resolve(caminhoDocumento).toFile());
    }

    private File criarDiretorioTemporario() throws IOException {
        File repositorioCartas = Files.createTempDirectory("portal-de-servicos").toFile();
        repositorioCartas.deleteOnExit();
        return repositorioCartas;
    }

    private File clonarRepositorio(File caminhoLocal) throws GitAPIException {
        log.info("Clonando repositório de cartas de serviço de {} para {}", urlRepositorio, caminhoLocal);
        CloneCommand clone = Git.cloneRepository()
                .setURI(urlRepositorio)
                .setProgressMonitor(new TextProgressMonitor())
                .setDirectory(caminhoLocal);

        try (Git repositorio = clone.call()) {
            String head = repositorio.log().call().iterator().next().getName();
            log.info("Repositório de cartas de serviço clonado na versão {}", head);
            return repositorio.getRepository().getWorkTree();
        }
    }
}
