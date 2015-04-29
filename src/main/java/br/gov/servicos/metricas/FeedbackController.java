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
class FeedbackController {

    FeedbackRepository feedbacks;
    EmailDeFeedback mail;

    @Autowired
    FeedbackController(FeedbackRepository feedbacks, EmailDeFeedback mail) {
        this.feedbacks = feedbacks;
        this.mail = mail;
    }

    @RequestMapping(value = "/feedback", method = GET)
    ModelAndView formularioPara(
            @RequestParam(required = false) String url,
            @RequestParam(required = false) String ticket,
            @RequestParam(required = false) String busca) {

        Map<String, Object> model = new HashMap<>();
        model.put("url", url);
        model.put("ticket", ticket);
        model.put("busca", busca);

        return new ModelAndView("feedback", model);
    }

    @RequestMapping(value = "/feedback", method = POST)
    RedirectView feedback(
            @RequestParam(required = false) String url,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) String ticket,
            @RequestParam(required = false) String feedback,
            @RequestParam(required = false) Boolean conteudoEncontrado) {

        Feedback f = new Feedback()
                .withUrl(url)
                .withQueryString(busca)
                .withTimestamp(currentTimeMillis())
                .withTicket(ticket)
                .withConteudoEncontrado(conteudoEncontrado)
                .withFeedback(feedback);

        mail.enviar(feedbacks.save(f));

        return new RedirectView("/conteudo/obrigado-pela-contribuicao");
    }

}
