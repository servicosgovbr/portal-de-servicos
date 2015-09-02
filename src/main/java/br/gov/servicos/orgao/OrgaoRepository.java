package br.gov.servicos.orgao;

import br.gov.servicos.importador.ConteudoParser;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import br.gov.servicos.v3.schema.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoRepository {

    ServicoRepository servicos;
    ConteudoParser parser;

    @Autowired
    OrgaoRepository(ServicoRepository servicos, ConteudoParser parser) {
        this.servicos = servicos;
        this.parser = parser;
    }

    @Cacheable("orgaos")
    public List<Orgao> findAll() {
        Set<Orgao> orgaos = new TreeSet<>((left, right) -> left.getId().compareTo(right.getId()));

        Iterable<Servico> svcs = servicos.findAll();
        svcs.forEach(s -> {
            if (s.getOrgao() != null) {
                orgaos.add(s.getOrgao());
            }
        });

        return new ArrayList<>(orgaos);
    }
}
