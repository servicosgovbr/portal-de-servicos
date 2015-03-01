package br.gov.servicos.config;

import br.gov.servicos.frontend.TicketFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
public class TicketFilterConfig {

    @Bean
    public FilterRegistrationBean ticketFilter() {
        FilterRegistrationBean filters = new FilterRegistrationBean();
        filters.setFilter(new TicketFilter());
        filters.setUrlPatterns(asList("/*"));

        return filters;
    }
}
