package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class RobotsTxtController {

    @RequestMapping(value = "/robots.txt", produces = "text/plain")
    @ResponseStatus(OK)
    @ResponseBody
    String robotsTxt() {
        return "User-agent: *\n" +
                "Disallow: /\n";
    }

}
