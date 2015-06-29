package br.gov.servicos.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();
        try {
            log.info("Request: {} {} - {} {} {}",
                    request.getMethod(),
                    request.getQueryString() == null ? request.getRequestURI() : request.getRequestURI() + "?" + request.getQueryString(),
                    request.getRemoteAddr(),
                    request.getHeader("X-Forwarded-For"),
                    request.getHeader("User-Agent")
            );

            chain.doFilter(request, response);

        } finally {
            HttpStatus status = HttpStatus.valueOf(response.getStatus());
            log.info("Response: HTTP {} {} - {}ms",
                    status.value(),
                    status.getReasonPhrase(),
                    System.currentTimeMillis() - start
            );
        }
    }
}
