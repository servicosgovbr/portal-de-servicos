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
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@FieldDefaults(level = PRIVATE)
public class TiposTest {

    private static final String XML_TESTE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><%s>%s</%s>";

    Validator validador;

    @Before
    public void setUp() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setResourceResolver(new ClassPathResolver());

        URL xsd = getClass().getResource("/tipos-test.xsd");
        validador = schemaFactory.newSchema(xsd).newValidator();
    }

    @Test
    public void textoCurtoNaoDevePassarDe255Caracteres() {
        validaTipoInvalido("textoCurto", repeat("X", 256));
    }

    @Test
    public void textoLongNaoDevePassarDe500Caracteres() {
        validaTipoInvalido("textoLongo", repeat("X", 501));
    }

    @SneakyThrows
    private void validaTipo(String tipo, String informacao) {
        String paraValidar = format(XML_TESTE, tipo, informacao, tipo);
        validador.validate(new StreamSource(new StringReader(paraValidar)));
    }

    private void validaTipoInvalido(String tipo, String informacao) {
        try {
            validaTipo(tipo, informacao);
            fail(format("Campo %s aceitou entrada supostamente inválida %s", tipo, informacao));
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("is not facet-valid with respect to"));
        }
    }
}
