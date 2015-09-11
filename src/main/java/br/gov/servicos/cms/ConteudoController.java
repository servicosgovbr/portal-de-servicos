package br.gov.servicos.cms;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ConteudoController {

    @RequestMapping("/conteudo/{id}")
    ModelAndView conteudo(@PathVariable("id") ConteudoHtml conteudo) {
        return new ModelAndView("conteudo", "conteudo", conteudo);
    }

    @RequestMapping(value = "/miolo/conteudo/{id}", method = GET)
    ModelAndView getHtml(@PathVariable("id") ConteudoHtml conteudo) {
        return new ModelAndView("conteudo :: //section", conteudo(conteudo).getModel());
    }

}
