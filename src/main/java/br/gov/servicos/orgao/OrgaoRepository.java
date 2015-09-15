package br.gov.servicos.orgao;

import br.gov.servicos.busca.BuscadorFacetado;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoRepository {

    ConteudoRepository conteudos;
    BuscadorFacetado buscador;
    ServicoRepository servicos;
    Siorg siorg;

    @Autowired
    OrgaoRepository(ConteudoRepository conteudos, BuscadorFacetado buscador, ServicoRepository servicos, Siorg siorg) {
        this.conteudos = conteudos;
        this.buscador = buscador;
        this.servicos = servicos;
        this.siorg = siorg;
    }

    @Cacheable("orgaos")
    public SortedSet<Orgao> findAll() {
        return servicos.findAll(new PageRequest(0, MAX_VALUE)).getContent()
                .stream()
                .map(Servico::getOrgao)
                .collect(toCollection(() -> new TreeSet<>(comparing(Orgao::getNome))));
    }

    public Optional<Orgao> findByUrl(String urlOrgao) {
        return findAll()
                .stream()
                .filter(o -> o.getUrl().equals(urlOrgao))
                .findFirst();
    }
}
