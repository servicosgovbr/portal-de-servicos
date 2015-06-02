package br.gov.servicos.frontend;

import br.gov.servicos.destaques.ServicosEmDestaque;
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

    private static final int SERVICOS_DESTACADOS = 10;

    ServicosEmDestaque destaques;

    @Autowired
    IndexController(ServicosEmDestaque servicosEmDestaque) {
        this.destaques = servicosEmDestaque;
    }

    @RequestMapping("/")
    ModelAndView index() {
        return new ModelAndView("index", "destaques", destaques.servicos(SERVICOS_DESTACADOS));
    }

}