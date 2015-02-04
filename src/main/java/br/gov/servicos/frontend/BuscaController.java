package br.gov.servicos.frontend;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
class BuscaController {

    ServicoRepository sr;

    @Autowired
    public BuscaController(ServicoRepository sr) {
        this.sr = sr;
    }

    @RequestMapping("/busca")
    ModelAndView busca(@RequestParam(required = true) String q) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);
        model.put("resultados", sr.search(new SimpleQueryStringBuilder(q)));
        return new ModelAndView("resultados-busca", model);
    }

}
