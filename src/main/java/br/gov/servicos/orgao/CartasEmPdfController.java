package br.gov.servicos.orgao;


import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.ServicoXML;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CartasEmPdfController {

    ServicoRepository servicos;

    @Autowired
    public CartasEmPdfController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @RequestMapping(value = "/carta/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Carta carta(@PathVariable("id") OrgaoXML orgaoXML) {
        List<String> servicos = this.servicos.findByOrgao(orgaoXML)
                .stream()
                .map(ServicoXML::getXml)
                .collect(toList());

        return new Carta()
                .withXmlOrgao(orgaoXML.getXml())
                .withXmlServicos(servicos);
    }

    @Wither
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Carta {
        String xmlOrgao;

        List<String> xmlServicos;
    }

}
