package br.gov.servicos.cartaServicos;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

@FieldDefaults(level = PRIVATE)
public class VersoesTest {

    private static final String CARTA_SERVICOS = "/cartaServicos/";
    private static final String ARQUIVO_XSD = ".*/v\\d+/\\w+\\.xsd";
    private static final String XSD = ".xsd";
    private static final String EXEMPLO_XSL = "-exemplo.xml";

    SchemaFactory validadorXsd;
    Path cartaServicos;

    @Before
    public void setUp() throws Exception {
        validadorXsd = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        validadorXsd.setResourceResolver(new ClassPathResolver());

        cartaServicos = Paths.get(
                getClass().getResource(CARTA_SERVICOS).toURI()
        );
    }

    @Test
    public void deveValidarExemplosContraXSDs() throws Exception {
        Iterator<Path> xsds = Files.walk(cartaServicos)
                .filter(p -> p.toString().matches(ARQUIVO_XSD))
                .iterator();

        if (!xsds.hasNext()) {
            fail("Nenhuma versão de XSD foi encontrada para ser validada");
        }

        xsds.forEachRemaining(this::validaExemplo);
    }

    @SneakyThrows
    private void validaExemplo(Path versao) {
        Validator validador = validadorXsd
                .newSchema(versao.toUri().toURL())
                .newValidator();

        validador.validate(xml(versao));
    }

    private StreamSource xml(Path versao) throws IOException {
        String arquivoExemplo = versao.toString().replace(XSD, EXEMPLO_XSL);
        InputStream xml = new FileInputStream(arquivoExemplo);

        return new StreamSource(xml);
    }

}
