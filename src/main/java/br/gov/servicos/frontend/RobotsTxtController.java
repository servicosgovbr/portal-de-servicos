package br.gov.servicos.frontend;

import br.gov.servicos.foundation.http.BaseUrl;
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
    String robotsTxt(HttpServletRequest request, @Value("${flags.permitir.robos}") Boolean permitirRobos) {
        if (!permitirRobos) {
            log.info("Robôs não permitidos neste ambiente (FLAGS_PERMITIR_ROBOS=false)");
            return format("User-agent: *%n" +
                    "Disallow: /%n");
        }

        BaseUrl baseUrl = new BaseUrl(request);
        return format("Sitemap: %s%n%n" +
                "User-agent: *%n" +
                "Disallow:%n", baseUrl.and("/sitemap.xml"));
    }
}