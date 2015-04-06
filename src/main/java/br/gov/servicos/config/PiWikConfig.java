package br.gov.servicos.config;

import br.gov.servicos.piwik.PiWikClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PiWikConfig {
    String url = "https://estatisticas.presidencia.gov.br";
    String token = "Precisa de um token válido aqui para funcionar";
    int idSite = 2;
    String format = "json";

    @Bean
    public PiWikClient piWikClient() {
        trustSelfSignedSSL();
        return new PiWikClient(
                new RestTemplate(),
                url,
                token,
                idSite,
                format);
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
