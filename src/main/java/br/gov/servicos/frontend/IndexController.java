package br.gov.servicos.frontend;

import br.gov.servicos.destaques.AreasDeInteresseEmDestaque;
import br.gov.servicos.destaques.ServicosEmDestaque;
import br.gov.servicos.orgao.OrgaoRepositoryUtil;
import br.gov.servicos.v3.schema.OrgaoXML;
import com.github.slugify.Slugify;
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

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class IndexController {

    private static final int SERVICOS_DESTACADOS = 8;

    ServicosEmDestaque destaques;

    AreasDeInteresseEmDestaque areasDestaque;

    OrgaoRepositoryUtil orgaos;

    Slugify slugify;

    @Autowired
    IndexController(ServicosEmDestaque servicosEmDestaque, AreasDeInteresseEmDestaque areasDestaque, OrgaoRepositoryUtil orgaos, Slugify slugify) {
        this.destaques = servicosEmDestaque;
        this.areasDestaque = areasDestaque;
        this.orgaos = orgaos;
        this.slugify = slugify;
    }

    @RequestMapping(value = "/", params = "orgao")
    ModelAndView redirectParaOrgao(@RequestParam("orgao") String url) throws IOException {
        OrgaoXML orgao = orgaos.obterOrgao(new OrgaoXML()
                .withId(slugify.slugify(url))
                .withUrl(url));
        if (orgao == null)
            return index();
        return new ModelAndView(new RedirectView("/orgao/" + orgao.getId()));
    }

    @RequestMapping("/")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("destaques", destaques.servicos(SERVICOS_DESTACADOS));
        modelAndView.addObject("areasDestaque", areasDestaque.areasDeInteresse());
        return modelAndView;
    }
}