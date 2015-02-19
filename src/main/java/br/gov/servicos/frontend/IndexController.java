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

import java.util.HashMap;
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

    @RequestMapping("/")
    ModelAndView index() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("acessos", sr.findAll(new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "acessos"))));
        model.put("ativacoes", sr.findAll(new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "ativacoes"))));

        model.put("areasDeInteresse", et.query(
                new NativeSearchQueryBuilder()
                        .addAggregation(
                                new TermsBuilder("top-areas")
                                        .field("areasDeInteresse")
                                        .size(600)

                        ).build()
                , response -> ((Terms) response.getAggregations()
                        .get("top-areas"))
                        .getBuckets()
                        .stream()
                        .map((bucket) -> new HashMap.SimpleEntry<>(bucket.getKey(), bucket.getDocCount()))
                        .collect(Collectors.toList())
        ));

        return new ModelAndView("index", model);
    }

}
