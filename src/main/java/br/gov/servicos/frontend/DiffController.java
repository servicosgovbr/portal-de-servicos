package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DiffController {

    String commit;

    @Autowired
    DiffController(@Value("${spring.git.properties:classpath:git.properties}") Resource info) throws IOException {
        String id;
        try {
            Properties props = new Properties();
            props.load(info.getInputStream());
            id = props.getProperty("git.commit.id", "master");
        } catch (Exception e) {
            id = "master";
            log.warn("git.properties não encontrado no classpath", e);
        }

        this.commit = id;
    }

    @RequestMapping(value = "/diff")
    RedirectView diff() {
        return new RedirectView("https://github.com/servicosgovbr/guia-de-servicos/compare/" + commit + "...master");
    }
}
