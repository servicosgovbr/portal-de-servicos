package br.gov.servicos.orgao;

import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.v3.schema.Orgao;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoRepository {

    ConteudoRepository conteudos;

    @Autowired
    OrgaoRepository(ConteudoRepository conteudos) {
        this.conteudos = conteudos;
    }

    @Cacheable("orgaos")
    public List<Orgao> findAll() {
        return StreamSupport.stream(conteudos.findByTipoConteudo("orgao").spliterator(), false)
                .map(c -> new Orgao()
                        .withId(c.getId())
                        .withNome(c.getNome()))
                .sorted((a, b) -> a.getNome().compareTo(b.getNome()))
                .collect(toList());
    }
}
