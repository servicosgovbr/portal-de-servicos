package br.gov.servicos.servico.publicoAlvo;

import br.gov.servicos.busca.Buscador;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class PublicoAlvoController {

    Buscador buscador;

    @Autowired
    PublicoAlvoController(Buscador buscador) {
        this.buscador = buscador;
    }

    @RequestMapping("/publico-alvo/{id}")
    ModelAndView publicoAlvo(@PathVariable String id) {
        List<Servico> servicos = buscador.buscaPor("publicosAlvo.id", ofNullable(id));


        PublicoAlvo publicoAlvo = servicos.stream()
                .flatMap(s -> s.getPublicosAlvo().stream())
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();

        Map<String, Object> model = new HashMap<>();
        model.put("servicos", servicos);
        model.put("publicoAlvo", publicoAlvo);

        return new ModelAndView("publico-alvo", model);
    }

}
