package br.gov.servicos.foundation.http;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class BaseUrl {

    HttpServletRequest request;

    @Autowired
    BaseUrl(HttpServletRequest request) {
        this.request = request;
    }

    public String and(String path) {
        return request.getRequestURL()
                .toString()
                .replaceAll("^(https?://(.*?))/(.*)$", (path.startsWith("/") ? "$1" : "$1/") + path);
    }

}
