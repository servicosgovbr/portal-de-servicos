package br.gov.servicos.cms;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ConteudoController {

    Markdown markdown;

    @Autowired
    ConteudoController(Markdown markdown) {
        this.markdown = markdown;
    }

    @RequestMapping("/conteudo/{id}")
    ModelAndView conteudo(@PathVariable("id") String id) throws ConteudoNaoEncontrado {
        String html = markdown.toHtml(new ClassPathResource(format("/conteudo/%s.md", id)));
        return new ModelAndView("conteudo", "conteudo", html);
    }

}
