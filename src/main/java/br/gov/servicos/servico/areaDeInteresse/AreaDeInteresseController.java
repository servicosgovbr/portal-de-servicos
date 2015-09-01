package br.gov.servicos.servico.areaDeInteresse;

import br.gov.servicos.busca.Buscador;
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

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class AreaDeInteresseController {

    Buscador buscador;
    private AreaDeInteresseRepository areasDeInteresse;
    Markdown markdown;

    @Autowired
    AreaDeInteresseController(Buscador buscador, AreaDeInteresseRepository areasDeInteresse, Markdown markdown) {
        this.buscador = buscador;
        this.areasDeInteresse = areasDeInteresse;
        this.markdown = markdown;
    }

    @RequestMapping("/area-de-interesse/{id}")
    ModelAndView areaDeInteresse(@PathVariable String id) {
        Map<String, Object> model = new HashMap<>();

        model.put("areaDeInteresse", areasDeInteresse.get(id).get());
        model.put("resultados", buscador.buscaConteudosPor("areasDeInteresse", ofNullable(AreaDeInteresse.findById(id).name()))
                .stream()
                .sorted((x, y) -> x.getId().compareTo(y.getId()))
                .collect(toList()));

        return new ModelAndView("area-de-interesse", model);
    }

}
