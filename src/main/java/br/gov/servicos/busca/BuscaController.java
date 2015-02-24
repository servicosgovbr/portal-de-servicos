package br.gov.servicos.busca;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.BuscaRepository;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.common.collect.Iterables;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class BuscaController {
    
    BuscaRepository br;
    ServicoRepository sr;

    @Autowired
    BuscaController(ServicoRepository sr, BuscaRepository br) {
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
        return doSearch(q, termQuery("linhasDaVida", q));
    }

    @RequestMapping("/eventos-das-linhas-da-vida")
    ModelAndView eventosDasLinhasDaVida(@RequestParam(required = true) String q) {
        return doSearch(q, termQuery("eventosDasLinhasDaVida", q));
    }

    @RequestMapping("/orgao")
    ModelAndView orgao(@RequestParam(required = true) String q) {
        return doSearch(q, fuzzyLikeThisQuery("prestador.nome", "responsavel.nome").likeText(q));
    }

    private ModelAndView doSearch(String q, QueryBuilder query) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", q);

        Iterable<Servico> servicos = sr.search(query);
        model.put("resultados", servicos);

        Busca busca = br.findOne(q);
        if (busca == null) {
            busca = new Busca(q, Iterables.size(servicos), 0);
        }
        br.save(busca.withNovaAtivacao());

        return new ModelAndView("resultados-busca", model);
    }

}
