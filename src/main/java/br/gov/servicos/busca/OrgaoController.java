package br.gov.servicos.busca;

import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class OrgaoController {

    Buscador buscador;
    Markdown markdown;

    @Autowired
    OrgaoController(Buscador buscador, Markdown markdown) {
        this.buscador = buscador;
        this.markdown = markdown;
    }

    @RequestMapping("/orgao/{id}")
    ModelAndView orgao(@PathVariable String id) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", id);
        model.put("conteudo", markdown.toHtml(new ClassPathResource(format("conteudo/orgaos/%s.md", id))));
        model.put("resultados", buscador.buscaSemelhante(ofNullable(id), "prestador.id", "responsavel.id"));

        return new ModelAndView("orgao", model);
    }

}
