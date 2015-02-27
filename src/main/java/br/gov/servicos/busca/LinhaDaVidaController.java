package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
        Function<Optional<String>, List<Servico>> executaBusca = termo -> buscador.buscaPor("linhasDaVida.id", termo);

        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", id);
        model.put("resultados", executaBusca.apply(ofNullable(id)));

        return new ModelAndView("linha-da-vida",
                model);
    }

}
