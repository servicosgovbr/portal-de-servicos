package br.gov.servicos.frontend;

import br.gov.servicos.dominio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
class IndexController {

    ServicoRepository sr;

    @Autowired
    public IndexController(ServicoRepository sr) {
        this.sr = sr;
    }

    @RequestMapping("/")
    ModelAndView index() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("acessos", sr.findAll(new PageRequest(0, 9, new Sort(Sort.Direction.DESC, "acessos"))));

        return new ModelAndView("index", model);
    }

}
