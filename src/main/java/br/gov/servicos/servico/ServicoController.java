package br.gov.servicos.servico;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
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
    ModelAndView all() {
        return new ModelAndView("servicos");
    }

    @RequestMapping(value = "/servico/{id}", method = GET)
    ModelAndView get(@PathVariable("id") String id) {
        Servico servico = servicos.save(buscaServico(id).withNovoAcesso());
        return new ModelAndView("servico", "servico", servico);
    }

    @RequestMapping(value = "/navegar/{id}", method = GET)
    RedirectView navegar(@PathVariable("id") String id) {
        return navegaPara(id, Servico::getUrl);
    }

    @RequestMapping(value = "/agendar/{id}", method = GET)
    public RedirectView agendar(@PathVariable("id") String id) {
        return navegaPara(id, Servico::getUrlAgendamento);
    }

    private RedirectView navegaPara(String id, Function<Servico, String> url) {
        Servico servico = servicos.save(buscaServico(id).withNovaAtivacao());

        return ofNullable(servico)
                .map(url)
                .map(RedirectView::new)
                .orElseThrow(ConteudoNaoEncontrado::new);
    }

    @SneakyThrows
    private Servico buscaServico(String id) {
        Servico servico = servicos.findOne(id);
        return ofNullable(servico).orElseThrow(ConteudoNaoEncontrado::new);
    }
}
