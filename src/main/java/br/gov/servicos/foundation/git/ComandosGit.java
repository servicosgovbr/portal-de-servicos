package br.gov.servicos.foundation.git;

import lombok.SneakyThrows;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static org.eclipse.jgit.merge.MergeStrategy.THEIRS;

@Component
public class ComandosGit {

    public String clonaOuAtualizaRepositorio(String url, String caminhoLocal) {
        File diretorioLocal = new File(caminhoLocal);

        try (Git repositorio = Git.open(diretorioLocal)) {
            return pull(repositorio);
        } catch (IOException e) {
            return clone(url, diretorioLocal);
        }
    }

    @SneakyThrows
    private String pull(Git repositorio) {
        PullResult pull = repositorio.pull().setStrategy(THEIRS).call();
        return pull.getMergeResult().getNewHead().name();
    }

    @SneakyThrows
    private String clone(String url, File diretorioLocal) {
        CloneCommand clone = Git.cloneRepository().setURI(url).setDirectory(diretorioLocal);

        try (Git repositorio = clone.call()) {
            return pull(repositorio);
        }
    }

}
