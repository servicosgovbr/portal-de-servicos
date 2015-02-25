package br.gov.servicos.cms;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.String.format;
import static java.util.Optional.of;
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
        ClassPathResource pagina = new ClassPathResource(format("/conteudo/%s.md", id));

        return of(pagina)
                .filter(ClassPathResource::exists)
                .map(markdown::toHtml)
                .map(html -> new ModelAndView("conteudo", "conteudo", html))
                .orElseThrow(ConteudoNaoEncontrado::new);
    }

}
