package br.gov.servicos.frontend;

import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
class ServicoController {

    ServicoRepository sr;

    @Autowired
    public ServicoController(ServicoRepository sr) {
        this.sr = sr;
    }

    @RequestMapping("/servico/{id}")
    ModelAndView get(@PathVariable("id") String id) {
        Servico servico = sr.save(sr.findOne(id).withNovoAcesso());

        Map<String, Object> model = new HashMap<>();
        model.put("servico", servico);
        model.put("similares", sr.searchSimilar(
                servico,
                new String[]{
                        "titulo",
                        "descricao",
                        "areasDeInteresse",
                },
                new PageRequest(0, 5)
        ));

        return new ModelAndView("servico", model);
    }

    @RequestMapping("/navegar/{id}")
    RedirectView navegar(@PathVariable("id") String id) {
        return new RedirectView(sr.save(sr.findOne(id).withNovaAtivacao()).getUrl());
    }

}
