package br.gov.servicos.legado;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.util.Collection;

import static java.util.Arrays.asList;

@RunWith(Parameterized.class)
public class ValidadorXmlTest {

    private XMLReader xmlReader;

    @SneakyThrows
    @Parameterized.Parameters
    public static Collection<Resource> data() {
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("file:src/main/resources/legado/*.xml");

        return asList(resources);
    }

    Resource input;

    public ValidadorXmlTest(Resource input) throws Exception {
        this.input = input;
        this.xmlReader = XMLReaderFactory.createXMLReader();
    }

    @Test
    public void deveSerVálido() throws Exception {
        xmlReader.parse(new InputSource(input.getInputStream()));
    }
}
