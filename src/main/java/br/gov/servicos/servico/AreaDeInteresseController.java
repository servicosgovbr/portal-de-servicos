package br.gov.servicos.servico;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.Markdown;
import br.gov.servicos.v3.schema.AreaDeInteresse;
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
class AreaDeInteresseController {

    ServicoRepository servicos;
    Markdown markdown;

    @Autowired
    AreaDeInteresseController(ServicoRepository servicos, Markdown markdown) {
        this.servicos = servicos;
        this.markdown = markdown;
    }

    @RequestMapping("/area-de-interesse/{id}")
    ModelAndView areaDeInteresse(@PathVariable String id) {
        Map<String, Object> model = new HashMap<>();
        AreaDeInteresse areaDeInteresse = AreaDeInteresse.findById(id);

        model.put("areaDeInteresse", areaDeInteresse);
        model.put("resultados", servicos.findByAreaDeInteresse(areaDeInteresse)
                .stream()
                .map(Conteudo::fromServico)
                .sorted((x, y) -> x.getId().compareTo(y.getId()))
                .collect(toList()));

        return new ModelAndView("area-de-interesse", model);
    }

}
