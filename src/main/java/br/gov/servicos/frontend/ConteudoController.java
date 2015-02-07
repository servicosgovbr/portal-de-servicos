package br.gov.servicos.frontend;

import org.pegdown.PegDownProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
class ConteudoController {

    @RequestMapping("/conteudo/{id}")
    ModelAndView docs(@PathVariable("id") String id) throws DocNotFoundException, IOException {
        ClassPathResource resource = new ClassPathResource(String.format("/conteudo/%s.md", id));
        if (!resource.exists()) {
            throw new DocNotFoundException();
        }
        return new ModelAndView("conteudo", "conteudo", markdown(resource));
    }

    private String markdown(ClassPathResource resource) throws IOException {
        return new PegDownProcessor().markdownToHtml(read(resource));
    }

    private String read(ClassPathResource resource) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))) {
            return br.lines().parallel().collect(Collectors.joining("\n"));
        }
    }

    @ResponseStatus(value = NOT_FOUND, reason = "Documento não encontrado")
    public static class DocNotFoundException extends Exception {
    }

}
