package br.gov.servicos.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

import static org.apache.commons.lang.StringUtils.join;

@Configuration
public class ServletContainerConfig {

    public static final String COMPRESSIBLE_MEDIA_TYPES = join(new String[]{
            "text/html",
            "text/xml",
            "text/plain",
            "application/json",
            "application/javascript",
            "text/css"
    }, ',');

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
        return servletContainer -> {
            TomcatEmbeddedServletContainerFactory container = (TomcatEmbeddedServletContainerFactory) servletContainer;

            container.setRegisterJspServlet(false);

            container.addContextCustomizers(customizer -> customizer.addWelcomeFile("index.html"));
            container.addConnectorCustomizers(
                    connector -> {
                        AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
                        httpProtocol.setCompression("on");
                        httpProtocol.setCompressionMinSize(256);
                        httpProtocol.setCompressableMimeTypes(COMPRESSIBLE_MEDIA_TYPES);
                    }
            );
        };
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

}
