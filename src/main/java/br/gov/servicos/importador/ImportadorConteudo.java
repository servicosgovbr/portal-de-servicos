package br.gov.servicos.importador;

import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.cms.PaginaEstaticaRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorConteudo {

    ConteudoRepository repository;
    PaginaEstaticaRepository paginaEstaticaRepository;
    ImportadorParaConteudoDeOrgaos orgaos;
    ImportadorParaPaginasTematicas paginas;
    ImportadorParaPaginasEstaticas estaticas;

    @Autowired
    public ImportadorConteudo(
            ConteudoRepository repository,
            PaginaEstaticaRepository paginaEstaticaRepository,
            ImportadorParaConteudoDeOrgaos orgaos,
            ImportadorParaPaginasTematicas paginas,
            ImportadorParaPaginasEstaticas estaticas
    ) {
        this.repository = repository;
        this.paginaEstaticaRepository = paginaEstaticaRepository;
        this.orgaos = orgaos;
        this.paginas = paginas;
        this.estaticas = estaticas;
    }

    public Iterable<PaginaEstatica> importar(RepositorioCartasServico repo) {
        log.info("Iniciando a importação de conteúdos...");

        return paginaEstaticaRepository.save(
                Stream.of(/*orgaos.importar(repo), paginas.importar(repo)*/ estaticas.importar(repo))
                        .flatMap(x -> x)
                        .peek(c -> log.debug("{} importado com sucesso", c.getId()))
                        .collect(toList()));
    }

}
