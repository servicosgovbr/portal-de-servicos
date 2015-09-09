package br.gov.servicos.orgao;

import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.v3.schema.Orgao;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoRepository {

    ConteudoRepository conteudos;
    Siorg siorg;

    @Autowired
    OrgaoRepository(ConteudoRepository conteudos, Siorg siorg) {
        this.conteudos = conteudos;
        this.siorg = siorg;
    }

    @Cacheable("orgaos")
    public List<Orgao> findAll() {
        return conteudos.findByTipo("orgao")
                .stream()
                .map(c -> new Orgao()
                        .withId(c.getId())
                        .withNome(c.getNome()))
                .sorted((a, b) -> a.getNome().compareTo(b.getNome()))
                .collect(toList());
    }

    @Cacheable("orgaosByUrl")
    public Orgao findBySiorg(String url) {
        return siorg.slugDoOrgao(url)
                .flatMap(id -> findAll()
                        .stream()
                        .filter(o -> o.getId().equals(id))
                        .findFirst())
                .orElse(null);
    }
}
