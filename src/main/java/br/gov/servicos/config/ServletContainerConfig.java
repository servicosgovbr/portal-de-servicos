package br.gov.servicos.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
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
            "application/x-font-woff",
            "text/css"
    }, ',');

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
        return servletContainer -> {
            addMimeMappingsForFonts(servletContainer);

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

    private void addMimeMappingsForFonts(ConfigurableEmbeddedServletContainer servletContainer) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("eot", "application/vnd.ms-fontobject");
        mappings.add("ttf", "application/font-sfnt");
        mappings.add("otf", "application/font-sfnt");
        mappings.add("woff", "application/font-woff");
        mappings.add("woff2", "application/font-woff");
        servletContainer.setMimeMappings(mappings);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

}
