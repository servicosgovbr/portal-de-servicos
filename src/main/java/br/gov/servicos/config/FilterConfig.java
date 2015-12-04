package br.gov.servicos.config;

import br.gov.servicos.frontend.LoggingFilter;
import br.gov.servicos.frontend.TicketFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Bean
    public FilterRegistrationBean securityHeadersFilter(@Value("${pds.piwik.url}") String urlPiwik) {
        return filter(2, new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                filterChain.doFilter(request, response);
                response.setHeader("X-XSS-Protection", "0");
                response.setHeader("X-Content-Type-Options", "nosniff");
                response.setHeader("Content-Security-Policy", "script-src: 'self' 'unsafe-inline' '" + urlPiwik + "' 'barra.brasil.gov.br'; default-src: 'self'");
            }
        });
    }

    private FilterRegistrationBean filter(int order, Filter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setOrder(order);
        registration.setFilter(filter);
        return registration;
    }
}
