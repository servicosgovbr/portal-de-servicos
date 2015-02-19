package br.gov.servicos.frontend;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import org.elasticsearch.common.collect.Iterables;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.elasticsearch.index.query.QueryBuilders.queryString;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Controller
class BuscaController {

    BuscaRepository br;
    ServicoRepository sr;

    @Autowired
    public BuscaController(ServicoRepository sr, BuscaRepository br) {
        this.sr = sr;
        this.br = br;
    }

    @RequestMapping("/busca")
    ModelAndView busca(@RequestParam(required = true) String q) {
        return doSearch(q, queryString(q));
    }

    @RequestMapping("/area-de-interesse")
    ModelAndView areaDeInteresse(@RequestParam(required = true) String q) {
        return doSearch(q, termQuery("areasDeInteresse", q));
    }

    @RequestMapping("/linha-da-vida")
    ModelAndView linhaDaVida(@RequestParam(required = true) String q) {
        TermQueryBuilder linhasDaVida;
        return doSearch(q, termQuery("linhasDaVida", q));
    }

    private ModelAndView doSearch(String q, QueryBuilder query) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);

        Iterable<Servico> servicos = sr.search(query);
        model.put("resultados", servicos);

        Busca busca = br.findOne(q);
        if (busca == null) {
            busca = new Busca(q, (long) Iterables.size(servicos), 0L);
        }
        br.save(busca.withNovaAtivacao());

        return new ModelAndView("resultados-busca", model);
    }

}
