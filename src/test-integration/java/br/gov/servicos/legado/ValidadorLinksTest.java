package br.gov.servicos.legado;

import br.gov.servicos.testutil.ParallelizedParameterized;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static javax.xml.xpath.XPathConstants.NODESET;

@RunWith(ParallelizedParameterized.class)
public class ValidadorLinksTest {

    XPathExpression expression;

    @SneakyThrows
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return asList(new PathMatchingResourcePatternResolver()
                .getResources("file:src/main/resources/legado/*.xml"))
                .stream()
                .map(r -> new Object[]{r.getFilename(), r})
                .collect(toList());
    }

    Resource input;
    RestTemplate http;

    public ValidadorLinksTest(String name, Resource input) throws Exception {
        desabilitaVerificaçãoSSL();

        this.input = input;
        this.http = new RestTemplate();

        HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
        rf.setConnectTimeout(1000);
        rf.setReadTimeout(3000);

        http.setRequestFactory(rf);
        expression = XPathFactory.newInstance().newXPath().compile("//url");
    }

    @Test
    @Ignore("Muitas URLs ainda estão falhando")
    public void deveTerLinksVálidos() throws Exception {
        NodeList results = (NodeList) expression.evaluate(new InputSource(input.getInputStream()), NODESET);
        for (int i = 0; i < results.getLength(); i++) {
            Node node = results.item(i);
            if (!isNullOrEmpty(node.getTextContent())) {
                http.headForHeaders(new URL(node.getTextContent()).toURI());
            }
        }
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
}
