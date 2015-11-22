package br.gov.servicos.importador;

import br.gov.servicos.cms.PaginaTematica;
import br.gov.servicos.cms.PaginaTematicaRepository;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Stream;

import static br.gov.servicos.TipoPagina.PAGINA_TEMATICA;
import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorParaPaginasTematicas {

    ConteudoParser parser;
    private PaginaTematicaRepository repository;

    @Autowired
    public ImportadorParaPaginasTematicas(
            ConteudoParser parser,
            PaginaTematicaRepository repository) {


        this.parser = parser;
        this.repository = repository;
    }

    @SneakyThrows
    public Iterable<PaginaTematica> importar(RepositorioCartasServico repositorio) {
        File dir = repositorio.get(PAGINA_TEMATICA.getCaminhoPasta().toString()).getFile();

        log.info("Importando páginas especiais em {}", dir);
        return repository.save(Stream.of(dir.listFiles((d, n) -> n.endsWith(PAGINA_TEMATICA.getExtensao())))
                .map(f -> unmarshal(f, PaginaTematica.class)
                        .withId(f.getName().replace(PAGINA_TEMATICA.getExtensao(), "")))
                .map(this::processaCampos)
                .peek(s -> log.debug("{} importado com sucesso", s.getId()))
                .collect(toList()));
    }

    private PaginaTematica processaCampos(PaginaTematica pagina) {
        String markdown = pagina.getNome().trim() + System.lineSeparator() +
                "---" + System.lineSeparator() + System.lineSeparator() +
                pagina.getConteudo().trim();

        return pagina
                .withTipoConteudo(PAGINA_TEMATICA.getNome())
                .withConteudo(parser.conteudo(pagina.getConteudo()))
                .withHtml(parser.conteudoHtml(markdown));
    }

}
