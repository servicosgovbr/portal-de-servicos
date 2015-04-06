package br.gov.servicos.cartaServicos;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.StringReader;
import java.net.URL;

import static java.lang.String.format;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.junit.Assert.fail;

@FieldDefaults(level = PRIVATE)
public class TiposTest {

    private static final String XML_TESTE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><%s>%s</%s>";

    Validator validador;

    @Before
    public void setUp() throws Exception {
        URL xsd = getClass().getResource("/cartaServicos/tipos-test.xsd");
        validador = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(xsd).newValidator();
    }

    @Test
    public void tipoInformacaoDeveEstarEntreAsOpcoesValidas() {
        validaTipo("tipoInformacao", "Serviço");
        validaTipo("tipoInformacao", "Benefício");
        validaTipo("tipoInformacao", "Dever");
        validaTipoInvalido("tipoInformacao", "Qualquer outro texto");
    }

    @Test
    public void textoCurtoNaoDevePassarDe255Caracteres() {
        validaTipoInvalido("textoCurto", repeat("X", 256));
    }

    @Test
    public void textoLongNaoDevePassarDe500Caracteres() {
        validaTipoInvalido("textoLongo", repeat("X", 500));
    }

    @SneakyThrows
    private void validaTipo(String tipo, String informacao) {
        String paraValidar = format(XML_TESTE, tipo, informacao, tipo);
        validador.validate(new StreamSource(new StringReader(paraValidar)));
    }

    private void validaTipoInvalido(String tipo, String informacao) {
        try {
            validaTipo(tipo, informacao);
            fail();
        } catch (Exception e) {
            //Ignore
        }
    }
}
