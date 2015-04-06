package br.gov.servicos.config;

import br.gov.servicos.piwik.PiwikClient2;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
@ConfigurationProperties("gds.piwik")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PiwikConfig2 {

    @Getter
    @Setter(/* usado pelo Spring */)
    String url;

    @Getter
    @Setter(/* usado pelo Spring */)
    String token;

    @Getter
    @Setter(/* usado pelo Spring */)
    int site;

    @Bean
    public PiwikClient2 piwikClient() {
        trustSelfSignedSSL();
        return new PiwikClient2(new RestTemplate(), url, token, site);
    }

    private void trustSelfSignedSSL() {
        try {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
