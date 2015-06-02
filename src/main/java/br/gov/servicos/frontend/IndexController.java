package br.gov.servicos.frontend;

import br.gov.servicos.config.DestaquesConfig;
import br.gov.servicos.destaques.ServicosEmDestaque;
import br.gov.servicos.piwik.PiwikClient;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class IndexController {

    ServicosEmDestaque destaques;

    private static final int SERVICOS_DESTACADOS = 10;

    @Autowired
    IndexController(ServicoRepository servicos, DestaquesConfig destaques, PiwikClient piwikClient) {
        this.destaques = new ServicosEmDestaque(servicos, destaques, piwikClient);
    }

    @RequestMapping("/")
    ModelAndView index() {
        return new ModelAndView("index", "destaques", destaques.servicosParaExibir(SERVICOS_DESTACADOS));
    }

    @RequestMapping("/maisAcessados")
    ModelAndView maisAcessados() {
        return new ModelAndView("index", "destaques", destaques.servicosParaExibirMaisAcessados(SERVICOS_DESTACADOS));
    }

}