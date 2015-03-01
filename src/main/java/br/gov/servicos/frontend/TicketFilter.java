package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TicketFilter implements Filter {

    Iterator<UUID> tickets;

    public TicketFilter(Iterator<UUID> tickets) {
        this.tickets = tickets;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        doFilter(req, res, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String old = Thread.currentThread().getName();
        try {
            UUID ticket = tickets.next();

            Thread.currentThread().setName(ticket.toString());
            request.setAttribute("Ticket", ticket);

            long start = System.currentTimeMillis();
            chain.doFilter(request, response);
            long total = System.currentTimeMillis() - start;

            log.info("{} {} {} {}ms {}",
                    request.getRemoteHost(),
                    request.getMethod(),
                    request.getRequestURI(),
                    total,
                    response.getStatus()
            );
        } finally {
            Thread.currentThread().setName(old);
        }
    }

    @Override
    public void destroy() {
    }
}
