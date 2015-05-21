package br.gov.servicos.config;

import br.gov.servicos.piwik.PiwikClient;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
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
@Getter
@Wither
@Setter(/* usado pelo Spring */)
@AllArgsConstructor
@NoArgsConstructor
public class PiwikConfig {

    Boolean enabled;

    String url;

    String token;

    int site;

    @Bean
    public PiwikClient piwikClient(PiwikConfig config) {
        trustSelfSignedSSL();
        return new PiwikClient(new RestTemplate(), config);
    }

    @SneakyThrows
    private void trustSelfSignedSSL() {
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                // não implementa nenhuma checagem
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                // não implementa nenhuma checagem
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLContext.setDefault(ctx);
    }

    public Boolean isEnabled() {
        return getEnabled();
    }
}
