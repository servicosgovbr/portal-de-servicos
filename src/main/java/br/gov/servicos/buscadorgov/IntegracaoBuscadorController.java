package br.gov.servicos.buscadorgov;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/xml")
@FieldDefaults(level = PRIVATE, makeFinal = true)
class IntegracaoBuscadorController {

    ServicoRepository servicos;

    @Autowired
    IntegracaoBuscadorController(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/servicos", produces = "text/xml", method = GET)
    @ResponseBody
    ResultadoListarServicos get(HttpServletRequest request) {
        FacetedPageImpl all = (FacetedPageImpl) servicos.findAll();
        return new ResultadoListarServicos(((List<br.gov.servicos.servico.Servico>) all.getContent()), request);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/servico/{id}", produces = "text/xml", method = GET)
    @ResponseBody
    ResultadoDetalharServico get(@PathVariable("id") String id) {
        return new ResultadoDetalharServico(servicos.findOne(id));
    }

}
