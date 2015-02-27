package br.gov.servicos.busca;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class LinhaDaVidaController {

    Buscador buscador;

    @Autowired
    LinhaDaVidaController(Buscador buscador) {
        this.buscador = buscador;
    }

    @RequestMapping("/linha-da-vida/{id}")
    ModelAndView linhaDaVida(@PathVariable String id) {

        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", id);
        model.put("resultados", buscador.buscaPor("linhasDaVida.id", ofNullable(id)));

        return new ModelAndView("linha-da-vida", model);
    }

}
