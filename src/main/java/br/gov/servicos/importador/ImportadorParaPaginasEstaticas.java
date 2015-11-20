package br.gov.servicos.importador;

import br.gov.servicos.cms.PaginaEstatica;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Stream;

import static br.gov.servicos.TipoPagina.PAGINA_ESTATICA;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorParaPaginasEstaticas {

    ConteudoParser parser;

    @Autowired
    public ImportadorParaPaginasEstaticas(
            ConteudoParser parser) {
        this.parser = parser;
    }

    @SneakyThrows
    public Stream<PaginaEstatica> importar(RepositorioCartasServico repositorio) {
        File dir = repositorio.get(PAGINA_ESTATICA.getCaminhoPasta().toString()).getFile();

        log.info("Importando páginas temáticas em {}", dir);
        return Stream.of(dir.listFiles((d, n) -> n.endsWith(PAGINA_ESTATICA.getExtensao())))
                .parallel()
                .map(FileSystemResource::new)
                .map(this::fromResource);
    }

    private PaginaEstatica fromResource(Resource r) {
        return new PaginaEstatica()
                .withId(r.getFilename().replace(PAGINA_ESTATICA.getExtensao(), ""))
                .withTipoConteudo(PAGINA_ESTATICA.getNome())
                .withNome(parser.titulo(r))
                .withConteudo(parser.conteudo(r))
                .withHtml(parser.conteudoHtml(r));
    }
}
