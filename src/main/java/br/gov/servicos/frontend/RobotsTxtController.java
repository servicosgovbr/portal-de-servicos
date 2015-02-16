package br.gov.servicos.frontend;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RobotsTxtController {

    @RequestMapping(value = "/robots.txt", produces = "text/plain")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String robotsTxt() {
        return "User-agent: *\n" +
                "Disallow: /\n";
    }

}
