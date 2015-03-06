package br.gov.servicos.servico;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Value
    @XStreamAlias("servico")
    static class Servico {
        String link;
        Object dataAtualizacao = null;

        Servico(String id, HttpServletRequest request) {
            link = String.format("%s://%s:%s/xml/servico/%s",
                    request.getProtocol(),
                    request.getServerName(),
                    request.getServerPort(),
                    id);
        }
    }

    @Value
    @XStreamAlias("resultadoDetalharServico")
    static class ResultadoDetalharServico {
        br.gov.servicos.servico.Servico servico;
    }

    @Value
    @XStreamAlias("resultadoListarServicos")
    static class ResultadoListarServicos {
        List<Servico> listaServicos;

        ResultadoListarServicos(List<br.gov.servicos.servico.Servico> servicos, HttpServletRequest request) {
            listaServicos = new ArrayList<>(servicos.stream()
                    .map(br.gov.servicos.servico.Servico::getId)
                    .map((id) -> new Servico(id, request))
                    .collect(Collectors.toList()));
        }
    }
}
