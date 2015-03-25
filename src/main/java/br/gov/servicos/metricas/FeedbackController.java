package br.gov.servicos.metricas;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import static java.lang.System.currentTimeMillis;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class FeedbackController {

    FeedbackRepository feedbacks;

    @Autowired
    FeedbackController(FeedbackRepository feedbacks) {
        this.feedbacks = feedbacks;
    }

    @RequestMapping(value = "/feedback", method = POST)
    RedirectView feedback(
            @RequestParam String url,
            @RequestParam String queryString,
            @RequestParam String ticket,
            @RequestParam String feedback,
            @RequestParam Boolean conteudoEncontrado) {

        feedbacks.save(new Feedback()
                .withUrl(url)
                .withQueryString(queryString)
                .withTimestamp(currentTimeMillis())
                .withTicket(ticket)
                .withConteudoEncontrado(conteudoEncontrado)
                .withFeedback(feedback));

        return new RedirectView("/conteudo/obrigado-pela-contribuicao");
    }

}
