package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorParaConteudoDePaginas {

    ConteudoParser parser;

    @Autowired
    public ImportadorParaConteudoDePaginas(
            ConteudoParser parser) {
        this.parser = parser;
    }

    @SneakyThrows
    public Stream<Conteudo> importar(RepositorioCartasServico repositorio) {
        File dir = repositorio.get("conteudo/paginas-especiais").getFile();

        log.info("Importando páginas especiais em {}", dir);
        return Stream.of(dir.listFiles((d, n) -> n.endsWith(".md")))
                .parallel()
                .map(FileSystemResource::new)
                .map(this::fromResource);
    }

    private Conteudo fromResource(Resource r) {
        return new Conteudo()
                .withId(r.getFilename().replace(".md", ""))
                .withTipoConteudo("conteudo")
                .withNome(parser.titulo(r))
                .withConteudo(parser.conteudo(r))
                .withHtml(parser.conteudoHtml(r));
    }
}
