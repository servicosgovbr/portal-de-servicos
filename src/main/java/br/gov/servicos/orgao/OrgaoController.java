package br.gov.servicos.orgao;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.Orgao;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class OrgaoController {

    OrgaoRepository orgaos;
    ServicoRepository servicos;
    ConteudoRepository conteudos;

    @Autowired
    OrgaoController(OrgaoRepository orgaos, ServicoRepository servicos, ConteudoRepository conteudos) {
        this.orgaos = orgaos;
        this.servicos = servicos;
        this.conteudos = conteudos;
    }

    @RequestMapping("/orgaos")
    ModelAndView orgaos() {
        return new ModelAndView("orgaos", "orgaos", orgaos.findAll());
    }

    @RequestMapping("/orgao/{id}")
    ModelAndView orgao(@PathVariable String id) {
        Map<String, Object> model = new HashMap<>();

        model.put("termo", id);
        model.put("conteudo", conteudos.findOne(id));
        model.put("resultados", servicos.findByOrgao(new Orgao().withId(id))
                .stream()
                .map(Conteudo::fromServico)
                .sorted((left, right) -> left.getId().compareTo(right.getId()))
                .collect(toList()));

        return new ModelAndView("orgao", model);
    }

}
