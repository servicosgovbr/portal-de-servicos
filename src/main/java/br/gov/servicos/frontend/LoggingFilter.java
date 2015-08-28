package br.gov.servicos.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.appendEntries;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();
        try {
            chain.doFilter(request, response);

        } finally {
            HttpStatus status = HttpStatus.valueOf(response.getStatus());

            Map<String, Object> data = new HashMap<>();
            data.put("req.remoteAddress", request.getRemoteAddr());
            data.put("req.remotePort", request.getRemotePort());
            data.put("req.remoteUser", request.getRemoteUser());
            data.put("req.headers.user-agent", request.getHeader("User-Agent"));
            data.put("req.headers.x-forwarded-for", request.getHeader("X-Forwarded-For"));
            data.put("req.method", request.getMethod());
            data.put("req.path", request.getRequestURI());
            if (request.getQueryString() == null) {
                data.put("req.url", request.getRequestURI());
            } else {
                data.put("req.url", request.getRequestURI() + "?" + request.getQueryString());
                data.put("req.queryString", request.getQueryString());
                request.getParameterMap().forEach((k,v) -> data.put("req.params." + k, v));
            }
            data.put("res.statusCode", status.value());
            data.put("res.responseTime", System.currentTimeMillis() - start);

            log.info(appendEntries(data), "{} {} {} - {}ms",
                    data.get("req.method"),
                    data.get("req.url"),
                    data.get("res.statusCode"),
                    data.get("res.responseTime"));
        }
    }
}
