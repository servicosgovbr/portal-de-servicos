package br.gov.servicos.metricas;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

import static br.gov.servicos.fixtures.TestData.OPINIAO;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValues;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class OpiniaoControllerTest {

    private static final boolean ACHEI_O_QUE_PROCURAVA = true;
    private static final String TICKET = "a995f68470b9";

    @Mock
    OpiniaoRepository opinioes;

    @Mock
    EmailDeOpiniao email;

    OpiniaoController controller;

    @Before
    public void setup() {
        controller = new OpiniaoController(opinioes, email);
    }

    @Test
    public void deveRedirecionarParaOFormularioDeOpiniao() {
        assertViewName(controller.formularioPara(null, null, null), "opiniao");
    }

    @Test
    public void deveRepassarDadosParaOFormulario() {
        Map<String, Object> valores = new HashMap<>();
        valores.put("url", "url-servico");
        valores.put("ticket", TICKET);
        valores.put("busca", "serviço buscado");

        assertModelAttributeValues(controller.formularioPara("url-servico", TICKET, "serviço buscado"), valores);
    }

    @Test
    public void retornaAgradecimentoParaOUsuario() {
        RedirectView response = controller.opiniao("/", "query", TICKET, "opiniao", ACHEI_O_QUE_PROCURAVA);
        assertThat(response.getUrl(), is("/conteudo/obrigado-pela-contribuicao"));
    }

    @Test
    public void deveSalvarOpiniaoDoUsuario() {
        controller.opiniao("localhost", "query", TICKET, "Otimo site", ACHEI_O_QUE_PROCURAVA);

        verify(opinioes).save(OPINIAO.withTimestamp(anyLong()));
    }

    @Test
    public void deveEnviarOpiniaoPorEmail() {
        controller.opiniao("localhost", "query", TICKET, "Otimo site", ACHEI_O_QUE_PROCURAVA);

        verify(email).enviar(OPINIAO.withTimestamp(anyLong()));
    }

}
