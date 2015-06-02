package br.gov.servicos.destaques;

import br.gov.servicos.config.DestaquesConfig;
import br.gov.servicos.piwik.PiwikClient;
import br.gov.servicos.piwik.PiwikPage;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.empty;
import static java.util.stream.StreamSupport.stream;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@Service
@Cacheable("servicosEmDestaque")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ServicosEmDestaque {

    ServicoRepository servicos;
    DestaquesConfig destaques;
    PiwikClient piwikClient;

    @Autowired
    public ServicosEmDestaque(ServicoRepository servicos, DestaquesConfig destaques, PiwikClient piwikClient) {
        this.servicos = servicos;
        this.destaques = destaques;
        this.piwikClient = piwikClient;
    }

    public List<Servico> servicosParaExibir(int quantidade) {
        return completaSevicosAteOLimite(buscaDestaquesSeNecessario(), quantidade);
    }

    public List<Servico> servicosParaExibirMaisAcessados(int quantidade) {
        return completaSevicosAteOLimite(
                concat(servicosMaisAcessados(),
                        buscaDestaquesSeNecessario()), quantidade);
    }

    private Stream<Servico> buscaDestaquesSeNecessario() {
        if (destaques.getServicos().isEmpty())
            return empty();

        return destaques.getServicos().stream()
                .map(servicos::findOne)
                .filter(Objects::nonNull);
    }

    private List<Servico> completaSevicosAteOLimite(Stream<Servico> servicosBase, int quantidade) {
        PageRequest pagina = new PageRequest(0, quantidade, new Sort(DESC, "titulo"));
        Stream<Servico> outros = stream(servicos.findAll(pagina).spliterator(), false)
                .filter(s -> !destaques.getServicos().contains(s.getId()));

        return concat(servicosBase, outros)
                .limit(quantidade)
                .collect(toList());
    }

    private Stream<Servico> servicosMaisAcessados() {
        log.debug("Piwik: listando serviços mais acessados...");

        Stream<Servico> servicos = piwikClient.getPageUrls("week", "yesterday").stream()
                .sorted((a, b) -> b.getUniqueVisitors().compareTo(a.getUniqueVisitors()))
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
