package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
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
    ImportadorParaConteudoDeOrgaos orgaos;
    ImportadorParaConteudoDePaginas paginas;

    @Autowired
    public ImportadorConteudo(
            ConteudoRepository repository,
            ImportadorParaConteudoDeOrgaos orgaos,
            ImportadorParaConteudoDePaginas paginas
    ) {
        this.repository = repository;
        this.orgaos = orgaos;
        this.paginas = paginas;
    }

    public Iterable<Conteudo> importar(RepositorioCartasServico repo) {
        log.info("Iniciando a importação de conteúdos...");

        return repository.save(
                Stream.of(orgaos.importar(repo), paginas.importar(repo))
                        .flatMap(x -> x)
                        .peek(c -> log.debug("{} importado com sucesso", c.getId()))
                        .collect(toList()));
    }

}
