package br.gov.servicos.foundation.git;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static org.eclipse.jgit.merge.MergeStrategy.THEIRS;

@Slf4j
@Component
public class ComandosGit {

    public void clonaOuAtualizaRepositorio(String url, String caminhoLocal) {
        File diretorioLocal = new File(caminhoLocal);

        try(Git repositorio = Git.open(diretorioLocal)) {
            pull(repositorio);
        } catch (IOException e) {
            clone(url, diretorioLocal);
        }
    }

    private void pull(Git repositorio) {
        try {
            repositorio.pull().setStrategy(THEIRS).call();
        } catch (GitAPIException e) {
            log.error(format("Erro ao atualizar repositório %s", repositorio), e);
        }
    }

    private void clone(String url, File diretorioLocal) {
        CloneCommand clone = Git.cloneRepository().setDirectory(diretorioLocal);

        try (Git repositorio = clone.call()) {
            pull(repositorio);
        } catch (GitAPIException e) {
            log.error(format("Erro ao importar repositório %s para %s", url, diretorioLocal), e);
        }
    }

}
