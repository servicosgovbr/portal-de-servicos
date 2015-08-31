package br.gov.servicos.importador;

import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Servico;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Component
@ManagedResource(
        objectName = "ServicosGovBr:type=ImportadorV3",
        description = "Importa cartas para o ElasticSearch"
)
public class ImportadorV3 {

    String urlRepositorio;
    PortalDeServicosIndex indices;
    ServicoRepository indice;
    Slugify slugify;

    @Autowired
    ImportadorV3(
            @Value("${pds.cartas.repositorio}") String urlRepositorio,
            PortalDeServicosIndex indices,
            ServicoRepository indice,
            Slugify slugify) {
        this.urlRepositorio = urlRepositorio;
        this.indices = indices;
        this.indice = indice;
        this.slugify = slugify;
    }

    @SneakyThrows
    @ManagedOperation
    public Iterable<Servico> importar() {
        log.info("Iniciando importação");
        indices.recriar();

        File repositorioCartas = Files.createTempDirectory("portal-de-servicos").toFile();
        repositorioCartas.deleteOnExit();

        File dir = clonarRepositorio(repositorioCartas);

        return indice.save(
                Stream.of(dir.toPath().resolve("cartas-servico/v3/servicos").toFile()
                        .listFiles((d, n) -> n.endsWith(".xml")))
                        .map(f -> unmarshal(f, Servico.class))
                        .map(s -> s.withId(slugify.slugify(s.getNome() + " " + s.getSigla())))
                        .collect(toList())
        );
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
