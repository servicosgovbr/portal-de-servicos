package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class RobotsTxtController {

    @RequestMapping(value = "/robots.txt", produces = "text/plain")
    @ResponseStatus(OK)
    @ResponseBody
    String robotsTxt(HttpServletRequest request) {
        return "Sitemap: " + request.getRequestURL().toString().replaceAll("^(http://(.*?))/(.*)$", "$1/sitemap.xml") + "\n\n" +
                "User-agent: *\n" +
                "Disallow:\n";
    }

}
