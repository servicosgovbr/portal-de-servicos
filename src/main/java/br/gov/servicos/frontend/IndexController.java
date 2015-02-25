package br.gov.servicos.frontend;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
class IndexController {

    ServicoRepository servicos;

    @Autowired
    IndexController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @RequestMapping("/")
    ModelAndView index() {
        return new ModelAndView("index", "acessos", servicosMaisAcessados());
    }

    private Page<Servico> servicosMaisAcessados() {
        return servicos.findAll(new PageRequest(0, 9, new Sort(DESC, "acessos")));
    }

}
