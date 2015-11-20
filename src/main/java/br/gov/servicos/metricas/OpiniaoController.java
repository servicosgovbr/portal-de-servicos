package br.gov.servicos.metricas;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class OpiniaoController {

    OpiniaoRepository opinioes;
    EmailDeOpiniao mail;

    @Autowired
    OpiniaoController(OpiniaoRepository opinioes, EmailDeOpiniao mail) {
        this.opinioes = opinioes;
        this.mail = mail;
    }

    @RequestMapping(value = "/opiniao", method = GET)
    ModelAndView formularioPara(
            @RequestParam(required = false) String url,
            @RequestParam(required = false) String ticket,
            @RequestParam(required = false) String busca) {

        Map<String, Object> model = new HashMap<>();
        model.put("url", url);
        model.put("ticket", ticket);
        model.put("busca", busca);

        return new ModelAndView("opiniao", model);
    }

    @RequestMapping(value = "/opiniao", method = POST)
    RedirectView opiniao(
            @RequestParam(required = false) String url,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String ticket,
            @RequestParam(required = false) String mensagem,
            @RequestParam(required = false) Boolean conteudoEncontrado) {

        Opiniao f = new Opiniao()
                .withUrl(url)
                .withQueryString(busca)
                .withTimestamp(currentTimeMillis())
                .withTicket(ticket)
                .withConteudoEncontrado(conteudoEncontrado)
                .withMensagem(mensagem);

        mail.enviar(opinioes.save(f));

        return new RedirectView("/pagina-estatica/obrigado-pela-contribuicao");
    }

}
