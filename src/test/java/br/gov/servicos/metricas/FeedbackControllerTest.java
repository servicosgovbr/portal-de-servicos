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

    public static final String TICKET = "a995f68470b9";

    @Mock
    FeedbackRepository feedbacks;

    FeedbackController controller;

    @Before
    public void setup() {
        controller = new FeedbackController(feedbacks);
    }

    @Test
    public void retornaFeedbackAgradecimentoParaOUsuario() {
        RedirectView response = controller.feedback("/", "query", "tentando", "aconteceu", TICKET);
        assertThat(response.getUrl(), is("/conteudo/obrigado-pela-contribuicao"));
    }
    
    @Test
    public void deveSalvarOFeedbackDoUsuario() {
        controller.feedback("localhost", "query", "Estou tentando mandar feedback", "E está tudo certo :)", TICKET);

        verify(feedbacks).save(new Feedback()
                .withUrl("localhost")
                .withQueryString("query")
                .withTimestamp(anyLong())
                .withTentandoFazer("Estou tentando mandar feedback")
                .withAconteceu("E está tudo certo :)"));
    }

}
