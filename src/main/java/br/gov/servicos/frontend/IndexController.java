package br.gov.servicos.frontend;

import br.gov.servicos.destaques.AreasDeInteresseEmDestaque;
import br.gov.servicos.destaques.ServicosEmDestaque;
import br.gov.servicos.orgao.OrgaoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;
import static net.logstash.logback.marker.Markers.append;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class IndexController {

    private static final int SERVICOS_DESTACADOS = 8;

    ServicosEmDestaque destaques;
    private AreasDeInteresseEmDestaque areasDestaque;
    OrgaoRepository orgaos;

    @Autowired
    IndexController(ServicosEmDestaque servicosEmDestaque, AreasDeInteresseEmDestaque areasDestaque, OrgaoRepository orgaos) {
        this.destaques = servicosEmDestaque;
        this.areasDestaque = areasDestaque;
        this.orgaos = orgaos;
    }

    @RequestMapping(value = "/", params = "orgao")
    ModelAndView redirectParaOrgao(@RequestParam("orgao") String url) throws IOException {
        return orgaos.findByUrl(url)
                .map(orgao -> new ModelAndView(new RedirectView("/orgaos/" + orgao.getId())))
                .orElseGet(() -> {
                    log.info(append("orgao.url", url), "Órgão com URL {} não cadastrado", url);
                    return index();
                });
    }

    @RequestMapping("/")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("destaques", destaques.servicos(SERVICOS_DESTACADOS));
        modelAndView.addObject("areasDestaque", areasDestaque.areasDeInteresse());
        return modelAndView;
    }
}