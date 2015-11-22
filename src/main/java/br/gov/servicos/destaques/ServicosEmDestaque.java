package br.gov.servicos.destaques;

import br.gov.servicos.config.ServicosDestaqueConfig;
import br.gov.servicos.piwik.PiwikClient;
import br.gov.servicos.piwik.PiwikPage;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.empty;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ServicosEmDestaque {

    ServicoRepository servicos;
    ServicosDestaqueConfig destaques;
    PiwikClient piwikClient;
    Boolean destaquesAutomaticos;

    @Autowired
    public ServicosEmDestaque(
            ServicoRepository servicos,
            ServicosDestaqueConfig destaques,
            PiwikClient piwikClient,
            @Value("${flags.destaques.automaticos}") Boolean destaquesAutomaticos
    ) {
        this.servicos = servicos;
        this.destaques = destaques;
        this.piwikClient = piwikClient;
        this.destaquesAutomaticos = destaquesAutomaticos;
    }

    @Cacheable("destaques")
    public List<ServicoXML> servicos(int quantidade) {
        if (destaquesAutomaticos) {
            return completaSevicosAteOLimite(
                    concat(servicosMaisAcessados(),
                            buscaDestaquesSeNecessario()), quantidade);
        }

        return completaSevicosAteOLimite(buscaDestaquesSeNecessario(), quantidade);
    }

    private Stream<ServicoXML> buscaDestaquesSeNecessario() {
        if (destaques.getServicos().isEmpty())
            return empty();

        return destaques.getServicos().stream()
                .map(servicos::findOne)
                .filter(Objects::nonNull);
    }

    private List<ServicoXML> completaSevicosAteOLimite(Stream<ServicoXML> servicosBase, int quantidade) {
        PageRequest pagina = new PageRequest(0, quantidade, new Sort(DESC, "nome"));

        Stream<ServicoXML> outrosServicos = servicos.findAll(pagina).getContent()
                .stream()
                .filter(s -> !destaques.getServicos().contains(s.getId()));

        return concat(servicosBase, outrosServicos)
                .limit(quantidade)
                .collect(toList());
    }

    private Stream<ServicoXML> servicosMaisAcessados() {
        log.debug("Piwik: listando serviços mais acessados...");

        Stream<ServicoXML> servicos = piwikClient.getPageUrls("week", "yesterday").stream()
                .sorted(comparing(PiwikPage::getUniqueVisitors))
                .map(PiwikPage::getIdServico)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .distinct()
                .map(this.servicos::findOne)
                .filter(Objects::nonNull);

        log.debug("Piwik: serviços mais acessados obtidos");
        return servicos;
    }
}
