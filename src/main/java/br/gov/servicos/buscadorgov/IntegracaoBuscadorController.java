package br.gov.servicos.buscadorgov;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/xml")
@FieldDefaults(level = PRIVATE, makeFinal = true)
class IntegracaoBuscadorController {

    ServicoRepository servicos;

    @Autowired
    IntegracaoBuscadorController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/servicos", produces = "text/xml", method = GET)
    ModelAndView get() {
        return new ModelAndView("resultado-listar-servicos", "servicos", servicos.findAll());
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/servico/{id}", produces = "text/xml", method = GET)
    ModelAndView get(@PathVariable("id") String id) {
        return new ModelAndView("resultado-detalhar-servico", "servico", servicos.findOne(id));
    }

}
