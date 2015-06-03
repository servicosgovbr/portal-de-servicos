package br.gov.servicos.xml;

import br.gov.servicos.testutil.ParallelizedParameterized;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.FieldDefaults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.stream.Stream;

import static br.gov.servicos.foundation.IO.read;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

@RunWith(ParallelizedParameterized.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ValidadorLinksIntegrationTest {

    @Parameterized.Parameters(name = "{0}:{1}")
    public static Collection<Object[]> data() throws IOException {
        return Stream.of(new PathMatchingResourcePatternResolver().getResources("file:src/main/resources/v1/*.xml"))
                .flatMap(r -> {
                    Document doc = Jsoup.parse(read(r));
                    return doc.select("link")
                            .stream()
                            .filter(e -> !e.attr("href").startsWith("http://localhost"))
                            .map(e -> new Object[]{r.getFilename(), e.attr("href")});
                })
                .collect(toList());
    }

    String url;

    public ValidadorLinksIntegrationTest(@SuppressFBWarnings(justification = "JUnit") String name, String url) throws Exception {
        this.url = url;
    }

    @Test
    public void deveSerLinkVálido() throws Exception {
        new URL(url).toURI();
    }

}
