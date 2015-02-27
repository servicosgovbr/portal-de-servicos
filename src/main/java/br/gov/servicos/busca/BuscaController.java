package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
        return new ModelAndView("resultados-busca", buscaUtilizando(q, buscador::busca));
    }

    @RequestMapping("/linha-da-vida/{id}")
    ModelAndView linhaDaVida(@PathVariable String id) {
        return new ModelAndView("linha-da-vida",
                buscaUtilizando(id, termo -> buscador.buscaPor("linhasDaVida.id", termo)));
    }

    @RequestMapping("/orgao/{id}")
    ModelAndView orgao(@PathVariable String id) {
        return new ModelAndView("resultados-busca",
                buscaUtilizando(id, termo -> buscador.buscaSemelhante(termo, "prestador.id", "responsavel.id")));
    }

    private HashMap<String, Object> buscaUtilizando(String q, Function<Optional<String>, List<Servico>> executaBusca) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);
        model.put("resultados", executaBusca.apply(ofNullable(q)));

        return model;
    }

}
