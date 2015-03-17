package br.gov.servicos.orgao;

import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class OrgaoRepository {

    ServicoRepository servicos;

    @Autowired
    OrgaoRepository(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    public List<Orgao> findAll() {
        Set<Orgao> orgaos = new TreeSet<>((left, right) -> left.getId().compareTo(right.getId()));

        Iterable<Servico> svcs = servicos.findAll();
        svcs.forEach(s -> orgaos.add(s.getPrestador()));
        svcs.forEach(s -> orgaos.add(s.getResponsavel()));

        return new ArrayList<>(orgaos);
    }
}
