package br.gov.servicos.servico;

import br.gov.servicos.cms.PaginaEstatica;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static br.gov.servicos.v3.schema.SegmentoDaSociedade.CIDADAOS;
import static br.gov.servicos.v3.schema.SegmentoDaSociedade.EMPRESAS;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class PublicoAlvoControllerTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    ServicoRepository servicos;

    PublicoAlvoController publicosAlvo;

    @Before
    public void setUp() throws IOException {
        given(servicos.findBySegmentoDaSociedade(CIDADAOS)).willReturn(asList(
                SERVICO.withNome("XXXX").withSegmentosDaSociedade(asList(CIDADAOS, EMPRESAS)),
                SERVICO.withNome("AAAA").withSegmentosDaSociedade(asList(CIDADAOS, EMPRESAS))));

        given(servicos.findBySegmentoDaSociedade(EMPRESAS)).willReturn(asList(
                SERVICO.withNome("FFFF").withSegmentosDaSociedade(asList(CIDADAOS, EMPRESAS)),
                SERVICO.withNome("AAAA").withSegmentosDaSociedade(asList(CIDADAOS, EMPRESAS))));

        publicosAlvo = new PublicoAlvoController(servicos, new Slugify());
    }

    @Test
    public void deveRedirecionarParaPaginaDePublicosAlvo() {
        assertViewName(publicosAlvo.publicoAlvo(CIDADAOS, null), "publico-alvo");
    }

    @Test
    public void deveRetornarOsServicosRelacionadosAoPublicoAlvo() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo(CIDADAOS, null), "servicos",
                asList(PaginaEstatica.fromServico(SERVICO.withNome("AAAA")), PaginaEstatica.fromServico(SERVICO.withNome("XXXX"))));
    }

    @Test
    public void deveRetornarOPublicoAlvoPesquisado() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo(CIDADAOS, null), "publicoAlvo", CIDADAOS);
    }

    @Test
    public void deveRetornarAsLetrasDisponiveis() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo(CIDADAOS, null), "letras", asList('A', 'X'));
        assertModelAttributeValue(publicosAlvo.publicoAlvo(EMPRESAS, null), "letras", asList('A', 'F'));
    }

    @Test
    public void deveRetornarALetraAtiva() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo(CIDADAOS, 'x'), "letraAtiva", 'X');
    }

    @Test
    public void deveFiltrarPelaLetraInformada() {
        assertModelAttributeValue(publicosAlvo.publicoAlvo(CIDADAOS, 'X'), "servicos",
                singletonList(PaginaEstatica.fromServico(SERVICO.withNome("XXXX"))));
    }

}