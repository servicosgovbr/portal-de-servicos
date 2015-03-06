package br.gov.servicos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class MarshallerConfig {

    @Bean
    MarshallingHttpMessageConverter converter(XStreamMarshaller xstream) {
        return new MarshallingHttpMessageConverter(xstream);
    }

    @Bean
    XStreamMarshaller marshaller() {
        XStreamMarshaller xstream = new XStreamMarshaller();
        xstream.setAutodetectAnnotations(true);
        return xstream;
    }
}
