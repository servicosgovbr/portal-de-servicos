package br.gov.servicos.foundation.http;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class Cookies {

    HttpServletRequest httpServletRequest;

    @Autowired
    Cookies(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public boolean isOn(String nome) {
        return stream(httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(nome))
                .filter(c -> c.getValue().equals("on"))
                .findAny().isPresent();
    }

}
