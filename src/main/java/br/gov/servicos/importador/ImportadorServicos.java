package br.gov.servicos.importador;

import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Servico;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Component
public class ImportadorServicos {

    PortalDeServicosIndex indices;
    ServicoRepository indice;
    Slugify slugify;

    @Autowired
    ImportadorServicos(PortalDeServicosIndex indices,
                       ServicoRepository indice,
                       Slugify slugify) {
        this.indices = indices;
        this.indice = indice;
        this.slugify = slugify;
    }

    @SneakyThrows
    public Iterable<Servico> importar(RepositorioCartasServico repo) {
        log.info("Iniciando importação de serviços...");
        indices.recriar();

        return indice.save(
                Stream.of(repo.acessarDocumento("cartas-servico/v3/servicos").getFile()
                        .listFiles((d, n) -> n.endsWith(".xml")))
                        .parallel()
                        .map(f -> unmarshal(f, Servico.class))
                        .map(s -> s.withId(slugify.slugify(s.getNome())))
                        .collect(toList())
        );
    }
}
