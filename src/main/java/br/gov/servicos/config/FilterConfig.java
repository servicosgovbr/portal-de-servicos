package br.gov.servicos.config;

import br.gov.servicos.frontend.LoggingFilter;
import br.gov.servicos.frontend.TicketFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

@Configuration
public class FilterConfig {

    @Bean
    public Iterator<UUID> tickets() {
        return Stream.iterate(UUID.randomUUID(), i -> UUID.randomUUID()).iterator();
    }

    @Bean
    public FilterRegistrationBean ticketFilter(Iterator<UUID> tickets) {
        return filter(0, new TicketFilter(tickets));
    }

    @Bean
    public FilterRegistrationBean loggingFilter() {
        return filter(1, new LoggingFilter());
    }

    private FilterRegistrationBean filter(int order, Filter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setOrder(order);
        registration.setFilter(filter);
        return registration;
    }
}
