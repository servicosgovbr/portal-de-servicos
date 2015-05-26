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
import java.util.*;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.stream.Collectors.toList;
import static javax.xml.xpath.XPathConstants.NODESET;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

@RunWith(ParallelizedParameterized.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ValidadorLinksIntegrationTest {

    @Parameterized.Parameters(name = "{0}:{1}")
    public static Collection<Object[]> data() {
        return extraiLinksPorArquivo("file:src/main/resources/legado/*.xml")
                .entrySet()
                .stream()
                .flatMap(keyValue -> keyValue
                        .getValue()
                        .stream()
                        .map(url -> new Object[]{keyValue.getKey(), url}))
                .collect(toList());
    }

    @SneakyThrows
    private static Map<String, Set<String>> extraiLinksPorArquivo(String arquivosXmlLegados) {
        XPathExpression expressao = XPathFactory.newInstance().newXPath().compile("//url");
        Resource[] arquivosLegados = new PathMatchingResourcePatternResolver().getResources(arquivosXmlLegados);

        Map<String, Set<String>> linksPorAquivo = new HashMap<>();

        for (Resource resource : arquivosLegados) {
            NodeList nodes = (NodeList) expressao.evaluate(new InputSource(resource.getInputStream()), NODESET);
            linksPorAquivo.put(resource.getFilename(), extraiLinks(nodes));
        }

        return linksPorAquivo;
    }

    private static Set<String> extraiLinks(NodeList nodes) {
        Set<String> links = new LinkedHashSet<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node url = nodes.item(i);
            if (!isNullOrEmpty(url.getTextContent())) {
                links.add(url.getTextContent().trim());
            }
        }

        return links;
    }


    String url;
    RestTemplate http;

    public ValidadorLinksIntegrationTest(@SuppressFBWarnings(justification = "JUnit") String name, String url) throws Exception {
        desabilitaVerificaçãoSSL();

        this.url = url;
        this.http = new RestTemplate();

        HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
        rf.setConnectTimeout(5000);
        rf.setReadTimeout(10000);

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
    public void deveSerLinkVálido() throws Exception {
        new URL(url).toURI();
    }

    @Test
    @Ignore("Muitas URLs ainda estão falhando")
    public void deveSerLinkComRepostaVálida() throws Exception {
        System.out.println(url);
        http.getForObject(url, String.class);
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
