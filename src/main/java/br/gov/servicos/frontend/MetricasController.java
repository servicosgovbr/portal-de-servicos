package br.gov.servicos.frontend;

import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
class MetricasController {

    BuscaRepository br;
    ServicoRepository sr;

    @Autowired
    public MetricasController(ServicoRepository sr, BuscaRepository br) {
        this.sr = sr;
        this.br = br;
    }

    @RequestMapping(value = "/metricas", produces = "application/json")
    ModelAndView metricas() {
        HashMap<String, Object> model = new HashMap<>();

        model.put("topAtivacoes", br.findAll(new PageRequest(0, 10, new Sort(DESC, "ativacoes"))));
        model.put("topResultados", br.findAll(new PageRequest(0, 10, new Sort(DESC, "resultados"))));
        model.put("bottomResultados", br.findAll(new PageRequest(0, 10, new Sort(ASC, "resultados"))));

        model.put("topServicosAcessados", sr.findAll(new PageRequest(0, 10, new Sort(DESC, "acessos"))));
        model.put("topServicosAtivados", sr.findAll(new PageRequest(0, 10, new Sort(DESC, "ativacoes"))));

        return new ModelAndView("metricas", model);
    }
}
