package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
class RobotsTxtController {

    @RequestMapping(value = "/robots.txt", produces = "text/plain")
    @ResponseStatus(OK)
    @ResponseBody
    String robotsTxt(HttpServletRequest request, @Value("${gds.permitir-robos}") boolean permitirRobos) {
        if (!permitirRobos) {
            log.info("Robôs não permitidos neste ambiente (GDS_PERMITIR_ROBOS=false)");
            return "User-agent: *\nDisallow: /\n";
        }

        return format("Sitemap: %s\n\n" +
                "User-agent: *\n" +
                "Disallow:\n", request.getRequestURL().toString().replaceAll("^(http://(.*?))/(.*)$", "$1/sitemap.xml"));
    }
}