package br.gov.servicos.busca;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class BuscaController {

    Buscador buscador;

    @Autowired
    BuscaController(Buscador buscador) {
        this.buscador = buscador;
    }

    @RequestMapping("/busca")
    ModelAndView busca(@RequestParam(required = true) String q) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);
        model.put("resultados", buscador.busca(ofNullable(q)));

        return new ModelAndView("resultados-busca", model);
    }

}
