package br.gov.servicos.busca;

import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;

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

    @RequestMapping(value = "/sugestaobusca", produces = "text/json")
    @ResponseStatus(OK)
    @ResponseBody
    String sugestaoBusca(@RequestParam(required = true) String q) {
        List<Servico> listaServico = buscador.buscaSemelhante(ofNullable(q),"titulo","descricao");

        String resultado = "[\""+q+"\",";

        resultado = resultado + listaServico.stream()
                .map(Servico::getTitulo).limit(7)
                .collect(Collectors.joining("\",\"","[\"","\"]"));

        resultado = resultado +"]";

        return resultado;
    }

}
