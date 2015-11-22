package br.gov.servicos.orgao;

import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.OrgaoXML;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoController {

    OrgaoRepository orgaos;
    ServicoRepository servicos;

    @Autowired
    OrgaoController(OrgaoRepository orgaos, ServicoRepository servicos) {
        this.orgaos = orgaos;
        this.servicos = servicos;
    }

    @RequestMapping("/orgaos")
    ModelAndView orgaos() {
        return new ModelAndView("orgaos", "orgaos", orgaos.findAll());
    }

    @RequestMapping("/orgao/{id}")
    public ModelAndView orgao(@PathVariable String id) {
        Map<String, Object> model = new HashMap<>();

        model.put("termo", id);
        model.put("conteudo", orgaos.findOne(id));
        model.put("resultados", servicos.findByOrgao(new OrgaoXML().withId(id))
                .stream()
                .map(PaginaEstatica::fromServico)
                .sorted(comparing(PaginaEstatica::getId))
                .collect(toList()));

        return new ModelAndView("orgao", model);
    }

}
