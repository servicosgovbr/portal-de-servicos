package br.gov.servicos.legado;

import br.gov.servicos.testutil.ParallelizedParameterized;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.xml.xpath.XPathConstants.NODESET;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ParallelizedParameterized.class)
public class ValidadorTituloslntegrationTest {


    @SneakyThrows
    @Parameterized.Parameters(name = "{0}:{1}")
    public static Collection<Object[]> data() {
        Resource[] arquivosLegados = new PathMatchingResourcePatternResolver().getResources("file:src/main/resources/legado/*.xml");
        XPathExpression expressao = XPathFactory.newInstance().newXPath().compile("/servico/titulo");
        Slugify slugify = new Slugify();

        List<Object[]> titulosPorArquivo = new ArrayList<>();
        for (Resource resource : arquivosLegados) {
            NodeList nodes = (NodeList) expressao.evaluate(new InputSource(resource.getInputStream()), NODESET);
            titulosPorArquivo.add(new Object[]{
                    slugify.slugify(nodes.item(0).getTextContent().trim()),
                    resource.getFilename()
            });
        }

        return titulosPorArquivo;
    }

    String titulo;
    String arquivo;

    public ValidadorTituloslntegrationTest(String titulo, String arquivo) throws Exception {
        this.titulo = titulo;
        this.arquivo = arquivo;
    }

    @Test
    public void nomeDoArquivoDeveSerIgualAoTítuloDoServiço() throws Exception {
        assertThat(titulo + ".xml", is(arquivo));
    }
}
