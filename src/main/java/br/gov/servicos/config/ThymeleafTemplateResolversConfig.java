package br.gov.servicos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafTemplateResolversConfig {

    @Autowired
    ThymeleafProperties properties;

    @Bean
    public SpringResourceTemplateResolver defaultTemplateResolver() {
        SpringResourceTemplateResolver resolver = resolver();

        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(0);
        resolver.setName("HTML template resolver");

        return resolver;
    }

    @Bean
    public SpringResourceTemplateResolver xmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = resolver();

        resolver.setSuffix(".xml");
        resolver.setTemplateMode("XML");
        resolver.setOrder(1);
        resolver.setName("XML template resolver");

        return resolver;
    }

    private SpringResourceTemplateResolver resolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

        resolver.setCacheable(properties.isCache());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("classpath:/templates/");
        return resolver;
    }

}
