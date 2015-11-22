package br.gov.servicos.busca;

import br.gov.servicos.cms.PaginaEstatica;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.OK;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class BuscaController {

    BuscadorConteudo buscador;

    @Autowired
    BuscaController(BuscadorConteudo buscador) {
        this.buscador = buscador;
    }

    @RequestMapping("/busca")
    ModelAndView busca(@RequestParam(required = true) String q,
                       @RequestParam(required = false, defaultValue = "1") Integer pagina) {

        Map<String, Object> model = new HashMap<>();
        model.put("termo", q);
        model.put("resultados", buscador.busca(ofNullable(q), pagina - 1));

        return new ModelAndView("resultados-busca", model);
    }

    @ResponseStatus(MOVED_PERMANENTLY)
    @RequestMapping("/search")
    String search(@RequestParam(value = "SearchableText", required = true) String q) {
        return format("redirect:/busca?q=%s", ofNullable(q).orElse(""));
    }

    @RequestMapping(value = "/sugestao", produces = "application/json")
    @ResponseStatus(OK)
    @ResponseBody
    String sugestao(@RequestParam(required = true) String q) {
        List<PaginaEstatica> listaServico = buscador.buscaSemelhante(ofNullable(q));

        return format("[\"%s\", %s]",
                q,
                listaServico.stream()
                        .map(PaginaEstatica::getNome)
                        .limit(7)
                        .collect(joining("\",\"", "[\"", "\"]")));
    }

    @RequestMapping(value = "/opensearch.xml", produces = MediaType.APPLICATION_XML_VALUE)
    ModelAndView openSearch() {
        return new ModelAndView("opensearch");
    }
}

