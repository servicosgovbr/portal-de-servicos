package br.gov.servicos.frontend;

import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoController {

    ServicoRepository servicos;

    @Autowired
    public ServicoController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @RequestMapping("/servico/{id}")
    ModelAndView get(@PathVariable("id") String id) {
        Servico servico = servicos.save(servicos.findOne(id).withNovoAcesso());
        return new ModelAndView("servico", "servico", servico);
    }

    @RequestMapping("/navegar/{id}")
    RedirectView navegar(@PathVariable("id") String id) {
        return new RedirectView(servicos.save(servicos.findOne(id).withNovaAtivacao()).getUrl());
    }

}
