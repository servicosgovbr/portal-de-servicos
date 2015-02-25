package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return buscaUtilizando(q, buscador::busca);
    }

    @RequestMapping("/area-de-interesse")
    ModelAndView areaDeInteresse(@RequestParam(required = true) String q) {
        return buscaUtilizando(q, termo -> buscador.buscaPor("areasDeInteresse", termo));
    }

    @RequestMapping("/linha-da-vida")
    ModelAndView linhaDaVida(@RequestParam(required = true) String q) {
        return buscaUtilizando(q, termo -> buscador.buscaPor("linhasDaVida", termo));
    }

    @RequestMapping("/eventos-das-linhas-da-vida")
    ModelAndView eventosDasLinhasDaVida(@RequestParam(required = true) String q) {
        return buscaUtilizando(q, termo -> buscador.buscaPor("eventosDasLinhasDaVida", termo));
    }

    @RequestMapping("/orgao")
    ModelAndView orgao(@RequestParam(required = true) String q) {
        return buscaUtilizando(q, termo -> buscador.buscaSemelhante(termo, "prestado.nome", "responsavel.nome"));
    }

    private ModelAndView buscaUtilizando(String q, Function<Optional<String>, List<Servico>> executaBusca) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);
        model.put("resultados", executaBusca.apply(ofNullable(q)));

        return new ModelAndView("resultados-busca", model);
    }

}
