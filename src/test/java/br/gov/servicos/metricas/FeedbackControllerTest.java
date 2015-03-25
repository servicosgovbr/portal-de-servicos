package br.gov.servicos.metricas;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.RedirectView;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class FeedbackControllerTest {

    private static final boolean ACHEI_O_QUE_PROCURAVA = true;
    private static final String TICKET = "a995f68470b9";

    @Mock
    FeedbackRepository feedbacks;

    FeedbackController controller;

    @Before
    public void setup() {
        controller = new FeedbackController(feedbacks);
    }

    @Test
    public void retornaFeedbackAgradecimentoParaOUsuario() {
        RedirectView response = controller.feedback("/", "query", TICKET, "feedback", ACHEI_O_QUE_PROCURAVA);
        assertThat(response.getUrl(), is("/conteudo/obrigado-pela-contribuicao"));
    }

    @Test
    public void deveSalvarOFeedbackDoUsuario() {
        controller.feedback("localhost", "query", TICKET, "Otimo site", ACHEI_O_QUE_PROCURAVA);

        verify(feedbacks).save(new Feedback()
                .withUrl("localhost")
                .withQueryString("query")
                .withTimestamp(anyLong())
                .withConteudoEncontrado(true)
                .withFeedback("Otimo site"));
    }

}
