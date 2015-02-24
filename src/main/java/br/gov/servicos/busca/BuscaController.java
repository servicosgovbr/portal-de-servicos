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
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.index.query.QueryBuilders.fuzzyLikeThisQuery;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class BuscaController {

    BuscaRepository br;
    ServicoRepository sr;
    Buscador buscador;

    @Autowired
    BuscaController(ServicoRepository sr, BuscaRepository br, Buscador buscador) {
        this.sr = sr;
        this.br = br;
        this.buscador = buscador;
    }

    @RequestMapping("/busca")
    ModelAndView busca(@RequestParam(required = true) String termoBuscado) {
        return buscaUtilizando(termoBuscado, buscador::busca);
    }

    @RequestMapping("/area-de-interesse")
    ModelAndView areaDeInteresse(@RequestParam(required = true) String termoBuscado) {
        return buscaUtilizando(termoBuscado, q -> buscador.buscaPor("areasDeInteresse", q));
    }

    @RequestMapping("/linha-da-vida")
    ModelAndView linhaDaVida(@RequestParam(required = true) String termoBuscado) {
        return buscaUtilizando(termoBuscado, q -> buscador.buscaPor("linhasDaVida", q));
    }

    @RequestMapping("/eventos-das-linhas-da-vida")
    ModelAndView eventosDasLinhasDaVida(@RequestParam(required = true) String termoBuscado) {
        return buscaUtilizando(termoBuscado, q -> buscador.buscaPor("eventosDasLinhasDaVida", q));
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

    private ModelAndView buscaUtilizando(String termoBuscado, Function<Optional<String>, List<Servico>> executaBusca) {
        Optional<String> termo = ofNullable(termoBuscado);
        
        HashMap<String, Object> model = new HashMap<>();
        model.put("termo", termoBuscado);
        model.put("resultados", executaBusca.apply(termo));

        return new ModelAndView("resultados-busca", model);
    }

}
