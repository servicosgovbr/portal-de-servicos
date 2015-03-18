package br.gov.servicos.busca;

import br.gov.servicos.servico.PublicoAlvo;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class PublicoAlvoControllerTest {

    @Mock
    Buscador buscador;

    PublicoAlvoController publicosAlvo;

    @Before
    public void setUp() {
        doReturn(asList(SERVICO))
                .when(buscador)
                .buscaPor("publicosAlvo.id", of("servicos-aos-cidadaos"));

        publicosAlvo = new PublicoAlvoController(buscador);
    }

    @Test
    public void deveRedirecionarParaPaginaDePublicosAlvo() {
        assertViewName(publicosAlvo.publicoAlvo("servicos-aos-cidadaos"), "publico-alvo");
    }

    @Test
    public void deveRetornarOsServicosRelacionadosAoPublicoAlvo() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos"), "servicos", asList(SERVICO));
    }

    @Test
    public void deveRetornarOPublicoAlvoPesquisado() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos"), "publicoAlvo",
                new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos cidadãos"));
    }

}