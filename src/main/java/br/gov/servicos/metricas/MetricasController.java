package br.gov.servicos.metricas;

import br.gov.servicos.foundation.exceptions.ValidacaoFormularioException;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MetricasController {

    FeedbackRepository feedbacks;

    @Autowired
    MetricasController(FeedbackRepository feedbacks) {
        this.feedbacks = feedbacks;
    }

    @RequestMapping(value = "/feedback", method = POST)
    public RedirectView feedback(
            @RequestParam(required = true) String url,
            String queryString,
            @RequestParam(required = true) String tentandoFazer,
            @RequestParam(required = true) String aconteceu,
            @RequestParam(required = true) String ticket
    ) {

        if (tentandoFazer == null && aconteceu == null)
            throw new ValidacaoFormularioException("Os detalhes do feedback devem ser informados");

        feedbacks.save(new Feedback()
                .withUrl(url)
                .withQueryString(queryString)
                .withTimestamp(System.currentTimeMillis())
                .withTentandoFazer(tentandoFazer)
                .withAconteceu(aconteceu)
                .withTicket(ticket)
        );

        return new RedirectView("/feedback/obrigado");
    }

    @RequestMapping(value = "/feedback/obrigado", method = GET)
    public ModelAndView obrigado() {
        return new ModelAndView("obrigado");
    }

}
