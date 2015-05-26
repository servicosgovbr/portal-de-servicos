package br.gov.servicos.servico;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoController {

    ServicoRepository servicos;

    @Autowired
    ServicoController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @RequestMapping(value = "/servicos", method = GET)
    ModelAndView todos(@RequestParam(required = false) Character letra) {
        Character primeiraLetra = ofNullable(letra).map(Character::toUpperCase).orElse('A');
        Map<Character, List<Servico>> servicosPorLetra = servicosAgrupadosPorLetraInicial();

        Map<String, Object> model = new HashMap<>();
        model.put("letraAtiva", primeiraLetra);

        model.put("servicos", servicosPorLetra.getOrDefault(primeiraLetra, Collections.<Servico>emptyList())
                .stream()
                .sorted((x, y) -> x.getId().compareTo(y.getId()))
                .map(Conteudo::fromServico)
                .collect(toList()));

        model.put("letras",
                servicosPorLetra
                        .keySet()
                        .stream()
                        .sorted()
                        .collect(toList()));

        return new ModelAndView("servicos", model);
    }

    @RequestMapping(value = "/repositorioServico/{id}", method = GET)
    RedirectView getLegado(@PathVariable("id") String id) {
        return new RedirectView("/servico/" + id);
    }

    @RequestMapping(value = "/servico/{id}", method = GET)
    ModelAndView get(@PathVariable("id") String id) {
        Servico servico = ofNullable(servicos.findOne(id))
                .orElseThrow(ConteudoNaoEncontrado::new);

        return new ModelAndView("servico", "servico", servico);
    }

    @RequestMapping(value = "/servico/{id}.xml", method = GET, produces = APPLICATION_XML_VALUE)
    ModelAndView xml(@PathVariable("id") String id) {
        Servico servico = ofNullable(servicos.findOne(id))
                .orElseThrow(ConteudoNaoEncontrado::new);

        return new ModelAndView("servico-xml", "servico", servico);
    }

    private Map<Character, List<Servico>> servicosAgrupadosPorLetraInicial() {
        return stream(servicos.findAll(new Sort(ASC, "titulo")).spliterator(), false)
                .collect(groupingBy(s -> s.getTitulo().trim().toUpperCase().charAt(0)));
    }

}
