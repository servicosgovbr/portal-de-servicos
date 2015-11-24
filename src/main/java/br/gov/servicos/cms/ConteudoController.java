package br.gov.servicos.cms;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ConteudoController {

    @RequestMapping("/pagina-estatica/{id}")
    public ModelAndView paginaEstatica(@PathVariable("id") PaginaEstatica paginaEstatica) {
        return new ModelAndView("conteudo", "conteudo", paginaEstatica);
    }

    @RequestMapping("/pagina-tematica/{id}")
    public ModelAndView paginaTematica(@PathVariable("id") PaginaTematica paginaTematica) {
        return new ModelAndView("conteudo", "conteudo", paginaTematica);
    }

}
