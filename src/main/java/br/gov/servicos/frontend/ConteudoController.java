package br.gov.servicos.frontend;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConteudoController {

    Markdown markdown;

    @Autowired
    public ConteudoController(Markdown markdown) {
        this.markdown = markdown;
    }

    @RequestMapping("/conteudo/{id}")
    public ModelAndView conteudo(@PathVariable("id") String id) throws NotFoundException, IOException {
        ClassPathResource resource = new ClassPathResource(String.format("/conteudo/%s.md", id));
        if (!resource.exists()) {
            throw new NotFoundException(resource.getDescription());
        }
        return new ModelAndView("conteudo", "conteudo", markdown.toHtml(resource));
    }

}
