package br.gov.servicos.importador;

import br.gov.servicos.utils.LogstashProgressMonitor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static lombok.AccessLevel.PRIVATE;
import static org.eclipse.jgit.api.ResetCommand.ResetType.HARD;
import static org.eclipse.jgit.merge.MergeStrategy.THEIRS;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RepositorioCartasServico {

    String urlRepositorio;

    @NonFinal
    File caminhoLocal;

    @Autowired
    public RepositorioCartasServico(@Value("${pds.cartas.repositorio}") String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }

    @SneakyThrows
    public void prepararRepositorio() {
        if (caminhoLocal == null) {
            caminhoLocal = clonarRepositorio(criarDiretorioTemporario());
        } else {
            atualizarRepositorio();
        }
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
                .setProgressMonitor(new LogstashProgressMonitor(log))
                .setDirectory(caminhoLocal);

        try (Git repositorio = clone.call()) {
            String head = repositorio.log().call().iterator().next().getName();
            log.info("Repositório de cartas de serviço clonado na versão {}", head);
            return repositorio.getRepository().getWorkTree();
        }
    }

    private void atualizarRepositorio() throws IOException, GitAPIException {
        log.info("Atualizando repositório de cartas de serviço de {} para {}", urlRepositorio, caminhoLocal);
        try (Git repositorio = Git.open(caminhoLocal)) {
            PullResult result = repositorio.pull()
                    .setProgressMonitor(new LogstashProgressMonitor(log))
                    .setStrategy(THEIRS)
                    .call();

            if (result.isSuccessful()) {
                String head = repositorio.reset()
                        .setMode(HARD)
                        .setRef("refs/remotes/origin/master")
                        .call()
                        .getName();

                log.info("Repositório de cartas de serviço em {} atualizado para a versão {}", caminhoLocal, head);
            } else {
                log.error("Erro ao atualizar repositório: {}", result);
            }
        }
    }
}
