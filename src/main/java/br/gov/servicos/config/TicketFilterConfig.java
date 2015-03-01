package br.gov.servicos.config;

import br.gov.servicos.frontend.Tickets;
import br.gov.servicos.frontend.TicketFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
public class TicketFilterConfig {

    @Bean
    public FilterRegistrationBean ticketFilter(Tickets tickets) {
        FilterRegistrationBean filters = new FilterRegistrationBean();

        filters.setFilter(new TicketFilter(tickets));
        filters.setUrlPatterns(asList("/*"));

        return filters;
    }
}
