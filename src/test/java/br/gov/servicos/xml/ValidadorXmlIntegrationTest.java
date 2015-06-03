package br.gov.servicos.xml;

import br.gov.servicos.testutil.ParallelizedParameterized;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import static java.util.stream.Collectors.toList;

@RunWith(ParallelizedParameterized.class)
public class ValidadorXmlIntegrationTest {

    private XMLReader xmlReader;

    @SneakyThrows
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return asList(new PathMatchingResourcePatternResolver()
                .getResources("file:src/main/resources/v1/*.xml"))
                .stream()
                .map(r -> new Object[]{r.getFilename(), r})
                .collect(toList());
    }

    Resource input;

    public ValidadorXmlIntegrationTest(@SuppressFBWarnings(justification = "JUnit") String name, Resource input) throws Exception {
        this.input = input;
        this.xmlReader = XMLReaderFactory.createXMLReader();
    }

    @Test
    public void deveSerVálido() throws Exception {
        xmlReader.parse(new InputSource(input.getInputStream()));
    }
}
