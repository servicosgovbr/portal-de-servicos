package br.gov.servicos.metricas;

import br.gov.servicos.foundation.exceptions.ValidacaoFormularioException;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class MetricasControllerTest {
    @Mock
    FeedbackRepositorio feedbackRepositorio;
    MetricasController metricasController;

    @Before
    public void setup() {
        metricasController = new MetricasController(feedbackRepositorio);
    }

    @Test
    public void retornaFeedbackAgradecimentoParaOUsuario() {
        ModelAndView response = metricasController.feedback("/", null, "a", "b");
        assertViewName(response, "obrigado");
    }

    @Test
    public void aceitaFeedbackDoUsuarioSeTiverApenasOQueTentouFazer() throws Exception {
        ModelAndView response = metricasController.feedback("/", null, "tentou fazer", null);
        assertViewName(response, "obrigado");
    }

    @Test
    public void aceitaFeedbackDoUsuarioSeTiverApenasOQueAcontece() throws Exception {
        ModelAndView response = metricasController.feedback("/", null, null, "aconteceu");
        assertViewName(response, "obrigado");
    }

    @Test(expected = ValidacaoFormularioException.class)
    public void retorna400AoPreencherFormularioComCamposVazios() { metricasController.feedback(null, null, null, null); }

    @Test
    public void deveSalvarOFeedbackDoUsuario() {
        metricasController.feedback("localhost", null, "Estou tentando mandar feedback", "E está tudo certo :)");

        verify(feedbackRepositorio).save(new Feedback()
                .withUrl("localhost")
                .withQueryString(null)
                .withTimestamp(anyLong())
                .withTentandoFazer("Estou tentando mandar feedback")
                .withAconteceu("E está tudo certo :)"));
    }
}
