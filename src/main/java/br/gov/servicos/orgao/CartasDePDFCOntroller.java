package br.gov.servicos.orgao;


import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.OrgaoXML;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartasDePDFCOntroller {

    ServicoRepository servicos;

    @RequestMapping(value = "/carta-de-servicos/{id}", produces = "application/json")
    public ModelAndView orgaoXML(@PathVariable("id") OrgaoXML orgao) {
        servicos.findByOrgao(orgao)
                .stream()
                .map(ServicoXML::xml)
       return new CartaDeServico()
               .withXmlOrgao("<orgao><nome>asdf</nome></orgao>")
               .withXmlServicos(Arrays.asList(
                       "<servico><nome>Nome de um servicos</nome></servico>"
               ));
    }

    @Data
    @Wither
    class CartaDeServico {
        String xmlOrgao;
        List<String> xmlServicos;
    }
}