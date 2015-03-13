package br.gov.servicos.metricas;

import br.gov.servicos.foundation.exceptions.ValidacaoFormularioException;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping(value = "/metricas")
public class MetricasController {

    FeedbackRepositorio feedbackRepositorio;

    @Autowired
    MetricasController(FeedbackRepositorio feedbackRepositorio) {
        this.feedbackRepositorio = feedbackRepositorio;
    }

    @RequestMapping(value = "/feedback", method = POST)
    public ModelAndView feedback(
            @RequestParam(required = true) String url,
            String queryString,
            @RequestParam(required = true) String tentandoFazer,
            @RequestParam(required = true) String aconteceu)
            throws ValidacaoFormularioException {

        if (tentandoFazer == null && aconteceu == null)
            throw new ValidacaoFormularioException("Os detalhes do feedback devem ser informados");



        feedbackRepositorio.save(new Feedback()
                .withUrl(url)
                .withQueryString(queryString)
                .withTimestamp(System.currentTimeMillis())
                .withTentandoFazer(tentandoFazer)
                .withAconteceu(aconteceu));

        return new ModelAndView("obrigado");
    }

}
