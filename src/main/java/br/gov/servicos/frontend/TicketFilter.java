package br.gov.servicos.frontend;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TicketFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String old = Thread.currentThread().getName();
        try {
            Thread.currentThread().setName(Ticket.atual().toString());
            req.setAttribute("Ticket", Ticket.atual());

            long start = System.currentTimeMillis();
            chain.doFilter(request, response);
            long total = System.currentTimeMillis() - start;

            log.info("{} {} {} {}ms {}",
                    req.getRemoteHost(),
                    req.getMethod(),
                    req.getRequestURI(),
                    total,
                    res.getStatus()
            );
        } finally {
            Thread.currentThread().setName(old);
        }
    }

    @Override
    public void destroy() {
    }
}
