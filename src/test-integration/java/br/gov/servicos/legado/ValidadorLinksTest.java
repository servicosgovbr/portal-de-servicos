package br.gov.servicos.legado;

import br.gov.servicos.testutil.ParallelizedParameterized;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.function.Consumer;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static javax.xml.xpath.XPathConstants.NODESET;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

@RunWith(ParallelizedParameterized.class)
@FieldDefaults(level = PRIVATE, makeFinal = false)
public class ValidadorLinksTest {

    @SneakyThrows
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return asList(new PathMatchingResourcePatternResolver()
                .getResources("file:src/main/resources/legado/*.xml"))
                .stream()
                .map(r -> new Object[]{r.getFilename(), r})
                .collect(toList());
    }

    XPathExpression expression;
    Resource input;
    RestTemplate http;

    public ValidadorLinksTest(@SuppressFBWarnings(justification = "JUnit") String name, Resource input) throws Exception {
        desabilitaVerificaçãoSSL();

        this.input = input;
        this.http = new RestTemplate();
        this.expression = XPathFactory.newInstance().newXPath().compile("//url");

        HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
        rf.setConnectTimeout(1000);
        rf.setReadTimeout(3000);

        http.setRequestFactory(rf);
        http.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getRawStatusCode() <= 199 || response.getRawStatusCode() >= 400;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                fail(response.getRawStatusCode() + " " + response.getStatusText());
            }
        });
    }

    @Test
    public void deveTerLinksVálidos() throws Exception {
        testaLinks(node -> {
            try {
                new URL(node.getTextContent()).toURI();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    @Ignore("Muitas URLs ainda estão falhando")
    public void deveTerLinksComRepostasVálidas() throws Exception {
        testaLinks(node -> {
            http.headForHeaders(node.getTextContent().trim());
        });

    }

    private void desabilitaVerificaçãoSSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLContext.setDefault(ctx);
    }

    private void testaLinks(Consumer<Node> fn) throws Exception {
        NodeList results;
        synchronized (ValidadorLinksTest.class) { // bug JDK-8047329
            results = (NodeList) expression.evaluate(new InputSource(input.getInputStream()), NODESET);
        }
        for (int i = 0; i < results.getLength(); i++) {
            Node node = results.item(i);
            if (!isNullOrEmpty(node.getTextContent())) {
                fn.accept(node);
            }
        }
    }

}
