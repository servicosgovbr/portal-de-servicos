package br.gov.servicos.cartaServicos;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static java.lang.String.format;
import static java.nio.file.Files.newDirectoryStream;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

@Slf4j
@FieldDefaults(level = PRIVATE)
public class VersoesTest {

    private static final String PASTA_DE_CARTAS_SERVICOS = "/cartaServicos/";
    private static final String VERSAO_CARTA = ".*/v\\d+$";

    SchemaFactory validadorXsd;
    Path cartasServico;

    @Before
    public void setUp() throws Exception {
        validadorXsd = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        cartasServico = Paths.get(resource(PASTA_DE_CARTAS_SERVICOS).toURI());
    }

    @Test
    public void deveValidarExemplosContraXSDs() throws Exception {
        Iterator<Path> pastas = newDirectoryStream(cartasServico, p -> p.toString().matches(VERSAO_CARTA)).iterator();
        if (!pastas.hasNext()) {
            fail("Nenhuma versão de XSD foi encontrada para ser validada");
        }

        pastas.forEachRemaining(this::validaExemplo);
    }

    @SneakyThrows
    private void validaExemplo(Path versao) {
        log.info("Validando XSD e exemplo XML em: {}", versao);

        Validator validador = validadorXsd
                .newSchema(xsd(versao))
                .newValidator();

        validador.validate(xml(versao));
    }

    private URL xsd(Path versao) {
        return versaoDe("servico.xsd", versao);
    }

    private StreamSource xml(Path versao) throws IOException {
        InputStream xml = versaoDe("exemplo.xml", versao).openStream();
        return new StreamSource(xml);
    }

    private URL versaoDe(String arquivo, Path versao) {
        return resource("%s/%s/%s", PASTA_DE_CARTAS_SERVICOS, versao.getFileName(), arquivo);
    }

    private URL resource(String path, Object... args) {
        return getClass().getResource(format(path, args));
    }
}
