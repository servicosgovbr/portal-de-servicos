package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.jboss.logging.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TicketFilter extends OncePerRequestFilter {

    public static final String TICKET_KEY = "req.ticket";

    Iterator<UUID> tickets;

    public TicketFilter(Iterator<UUID> tickets) {
        this.tickets = tickets;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String ticket = tickets.next().toString();

            MDC.put(TICKET_KEY, ticket);
            request.setAttribute(TICKET_KEY, ticket);

            chain.doFilter(request, response);
        } finally {
            MDC.remove(TICKET_KEY);
        }
    }
}
