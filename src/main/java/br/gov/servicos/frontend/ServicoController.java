package br.gov.servicos.frontend;

import br.gov.servicos.servicos.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@Controller
class ServicoController {

    ServicoRepository sr;

    @Autowired
    public ServicoController(ServicoRepository sr) {
        this.sr = sr;
    }

    @RequestMapping("/servico/{id}")
    ModelAndView get(@PathVariable("id") String id) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("servico", sr.save(sr.findOne(id).withNovoAcesso()));
        return new ModelAndView("servico", model);
    }

    @RequestMapping("/navegar/{id}")
    RedirectView navegar(@PathVariable("id") String id) {
        return new RedirectView(sr.save(sr.findOne(id).withNovaAtivacao()).getUrl());
    }

}
