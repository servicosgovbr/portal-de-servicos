package br.gov.servicos.ponte;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PonteController {
    final static String url = "http://localhost:8100/ponte";
    RestTemplate restTemplate;

    @Autowired
    public PonteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/ponte", method = GET)
    ModelAndView carregarApp(@RequestParam("appId") String appId) {
        ResponseEntity<RespostaPonte> entity = restTemplate.postForEntity(url, new RequestPonte().withAppId(appId), RespostaPonte.class);
        return new ModelAndView("ponte/index", "ponte", entity.getBody());
    }

    @RequestMapping(value = "/ponte/acao", method = POST)
    ModelAndView acaoApp(@RequestParam Map<String, String> params) {
        RequestPonte requestPonte = new RequestPonte()
                .withAppId(params.get("appId"))
                .withAction(params.get("action"))
                .withParams(params);

        ResponseEntity<RespostaPonte> resposta = restTemplate.postForEntity(url, requestPonte, RespostaPonte.class);
        return new ModelAndView("ponte/index", "ponte", resposta.getBody());
    }
}
