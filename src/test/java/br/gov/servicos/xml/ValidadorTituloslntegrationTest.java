package br.gov.servicos.xml;

import br.gov.servicos.testutil.ParallelizedParameterized;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Collection;
import java.util.stream.Stream;

import static br.gov.servicos.foundation.IO.read;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ParallelizedParameterized.class)
public class ValidadorTituloslntegrationTest {

    @SneakyThrows
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Stream.of(new PathMatchingResourcePatternResolver().getResources("file:src/main/resources/v1/*.xml"))
                .map(r -> {
                    Document doc = Jsoup.parse(read(r));
                    return new Object[]{
                            doc.select("servico > id").text(),
                            doc.select("servico > nome").text(),
                            r.getFilename()};
                })
                .collect(toList());
    }

    String id;
    String titulo;
    String arquivo;

    public ValidadorTituloslntegrationTest(String id, String titulo, String arquivo) throws Exception {
        this.id = id;
        this.titulo = titulo;
        this.arquivo = arquivo;
    }

    @Test
    public void slugDoNomeDeveSerIgualAoIdDoServiço() throws Exception {
        Slugify slugify = new Slugify();
        assertThat(slugify.slugify(titulo), is(id));
    }

    @Test
    public void nomeDoArquivoDeveSerIgualAoIdDoServiço() throws Exception {
        assertThat(id + ".xml", is(arquivo));
    }
}
