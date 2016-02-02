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

        /* Quando permitido robos de sites de pesquisa, permite apenas o Googlebot (bot do google.com),
         * o Bingbot (bot do bing.com) e o Teoma (bot do ask.com),e bloqueia os demais robos de pesquisa
         * para evitar possiveis robos de malwares.
         * Tambem adiciona o sitemap.xml, arquivo com referencia a todas as URLs do site
         */
        BaseUrl baseUrl = new BaseUrl(request);
        return format("Sitemap: %s %n%n" +
                "User-agent: Googlebot %n" +
                "Disallow: %n%n" +
                "User-agent: Googlebot-Image %n" +
                "Disallow: %n%n" +
                "User-agent: Googlebot-News %n" +
                "Disallow: %n%n" +
                "User-agent: Bingbot %n" +
                "Disallow: %n%n" +
                "User-agent: Teoma %n" +
                "Disallow: %n%n" +
                "User-agent: * %n" +
                "Disallow: /%n",
                baseUrl.and("/sitemap.xml"));
    }
}