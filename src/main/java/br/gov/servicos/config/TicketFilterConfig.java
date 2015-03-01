package br.gov.servicos.config;

import br.gov.servicos.frontend.TicketFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Configuration
public class TicketFilterConfig {

    @Bean
    public Iterator<UUID> tickets() {
        return Stream.iterate(UUID.randomUUID(), i -> UUID.randomUUID()).iterator();
    }

    @Bean
    public FilterRegistrationBean ticketFilter(Iterator<UUID> tickets) {
        FilterRegistrationBean filters = new FilterRegistrationBean();

        filters.setFilter(new TicketFilter(tickets));
        filters.setUrlPatterns(asList("/*"));

        return filters;
    }
}
