package br.gov.servicos.frontend;

import br.gov.servicos.dominio.ServicoRepository;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
class IndexController {

    ServicoRepository sr;
    ElasticsearchTemplate et;

    @Autowired
    public IndexController(ServicoRepository sr, ElasticsearchTemplate et) {
        this.sr = sr;
        this.et = et;
    }

    @RequestMapping("/tema")
    ModelAndView indexTema() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("acessos", sr.findAll(new PageRequest(0, 9, new Sort(Sort.Direction.DESC, "acessos"))));
        model.put("categorias", queryAgg("top-linhas", "linhasDaVida"));

        return new ModelAndView("index2", model);
    }

    @RequestMapping("/")
    ModelAndView index() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("acessos", sr.findAll(new PageRequest(0, 6, new Sort(Sort.Direction.DESC, "acessos"))));
        model.put("ativacoes", sr.findAll(new PageRequest(0, 6, new Sort(Sort.Direction.DESC, "ativacoes"))));

        model.put("areasDeInteresse", queryAgg("top-areas", "areasDeInteresse"));
        model.put("linhasDaVida", queryAgg("top-linhas", "linhasDaVida"));
        model.put("eventosDasLinhasDaVida", queryAgg("top-eventos", "eventosDasLinhasDaVida"));

        return new ModelAndView("index", model);
    }

    private List<AbstractMap.SimpleEntry<String, Long>> queryAgg(String nome, String campo) {
        return et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder(nome)
                                        .field(campo)
                                        .size(600)

                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get(nome))
                        .getBuckets()
                        .stream()
                        .map((bucket) -> new HashMap.SimpleEntry<>(bucket.getKey(), bucket.getDocCount()))
                        .collect(Collectors.toList())
        );
    }

}
