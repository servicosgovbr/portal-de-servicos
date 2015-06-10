package br.gov.servicos.foundation.git;

import lombok.SneakyThrows;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ComandosGit {

    @SneakyThrows
    public String clonaRepositorio(String url, File caminhoLocal) {
        CloneCommand clone = Git.cloneRepository()
                .setURI(url)
                .setDirectory(caminhoLocal);

        try (Git repositorio = clone.call()) {
            RevCommit head = repositorio.log().call().iterator().next();
            return head.getName();
        }
    }

}
