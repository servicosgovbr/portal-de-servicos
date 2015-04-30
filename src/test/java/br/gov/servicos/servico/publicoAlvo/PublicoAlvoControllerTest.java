package br.gov.servicos.servico.publicoAlvo;

import br.gov.servicos.busca.Buscador;
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
        doReturn(asList(
                SERVICO.withTitulo("XXXX"),
                SERVICO.withTitulo("AAAA")
        )).when(buscador)
                .buscaPor("publicosAlvo.id", of("servicos-aos-cidadaos"));

        publicosAlvo = new PublicoAlvoController(buscador);
    }

    @Test
    public void deveRedirecionarParaPaginaDePublicosAlvo() {
        assertViewName(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", null), "publico-alvo");
    }

    @Test
    public void deveRetornarOsServicosRelacionadosAoPublicoAlvo() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", null), "servicos",
                asList(SERVICO.withTitulo("AAAA")));
    }

    @Test
    public void deveRetornarOPublicoAlvoPesquisado() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", null), "publicoAlvo",
                new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos"));
    }

    @Test
    public void deveRetornarAsLetrasDisponiveis() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", null), "letras", asList('A', 'X'));
    }

    @Test
    public void deveRetornarALetraAtiva() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", null), "letraAtiva", 'A');
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", 'x'), "letraAtiva", 'X');
    }

    @Test
    public void deveFiltrarPelaLetraInformada() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo("servicos-aos-cidadaos", 'X'), "servicos",
                asList(SERVICO.withTitulo("XXXX")));
    }

}