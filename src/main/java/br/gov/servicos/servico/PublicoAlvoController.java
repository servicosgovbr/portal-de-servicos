package br.gov.servicos.servico;

import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.v3.schema.SegmentoDaSociedade;
import br.gov.servicos.v3.schema.ServicoXML;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class PublicoAlvoController {

    ServicoRepository servicos;
    Slugify slugify;

    @Autowired
    PublicoAlvoController(ServicoRepository servicos, Slugify slugify) {
        this.servicos = servicos;
        this.slugify = slugify;
    }

    @RequestMapping({"/publico-alvo/servicos-aos-{id}", "/publico-alvo/servicos-as-{id}"})
    RedirectView legado(@PathVariable String id,
                        @RequestParam(required = false) Character letra) {
        return new RedirectView("/publico-alvo/" + id + ofNullable(letra).map(l -> "?letra=" + l).orElse(""));
    }

    @RequestMapping("/publico-alvo/{id}")
    ModelAndView publicoAlvo(@PathVariable("id") SegmentoDaSociedade segmento,
                             @RequestParam(required = false) Character letra) {

        Character primeiraLetra = ofNullable(letra).map(Character::toUpperCase).orElse('A');
        Map<Character, List<ServicoXML>> servicosPorLetraInicial = servicosAgrupadosPorLetraInicial(segmento);

        List<PaginaEstatica> servicos = servicosPorLetraInicial.getOrDefault(primeiraLetra, Collections.<ServicoXML>emptyList())
                .stream()
                .sorted(comparing(ServicoXML::getNome))
                .map(PaginaEstatica::fromServico)
                .collect(toList());

        Map<String, Object> model = new HashMap<>();
        model.put("letraAtiva", primeiraLetra);
        model.put("publicoAlvo", segmento);
        model.put("servicos", servicos);
        model.put("letras", letrasDisponiveis(servicosPorLetraInicial.keySet()));

        return new ModelAndView("publico-alvo", model);
    }

    private Map<Character, List<ServicoXML>> servicosAgrupadosPorLetraInicial(SegmentoDaSociedade segmento) {
        return servicos.findBySegmentoDaSociedade(segmento)
                .stream()
                .collect(groupingBy(s -> s.getNome().trim().toUpperCase().charAt(0)));
    }

    private List<Character> letrasDisponiveis(Set<Character> letras) {
        return letras
                .stream()
                .sorted()
                .collect(toList());
    }

}
