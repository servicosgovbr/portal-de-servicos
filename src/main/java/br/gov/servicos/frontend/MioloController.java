package br.gov.servicos.frontend;

import br.gov.servicos.cms.ConteudoController;
import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.cms.PaginaTematica;
import br.gov.servicos.orgao.OrgaoController;
import br.gov.servicos.v3.schema.ServicoXML;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MioloController {

    OrgaoController orgaos;
    ConteudoController conteudos;

    @Autowired
    public MioloController(OrgaoController orgaos, ConteudoController conteudos) {
        this.orgaos = orgaos;
        this.conteudos = conteudos;
    }

    @RequestMapping(value = "/miolo/servico/{id}", method = GET)
    ModelAndView getHtml(@PathVariable("id") ServicoXML servico) {
        return new ModelAndView("servico :: //section", "servico", servico);
    }

    @RequestMapping(value = "/miolo/orgao/{id}", method = GET)
    ModelAndView getHtml(@PathVariable("id") String id) {
        return new ModelAndView("orgao :: //section", orgaos.orgao(id).getModel());
    }

    @RequestMapping(value = "/miolo/pagina-estatica/{id}", method = GET)
    ModelAndView getHtml(@PathVariable("id") PaginaEstatica paginaEstatica) {
        return new ModelAndView("conteudo :: //section", conteudos.paginaEstatica(paginaEstatica).getModel());
    }

    @RequestMapping(value = "/miolo/pagina-tematica/{id}", method = GET)
    ModelAndView getHtml(@PathVariable("id") PaginaTematica paginaTematica) {
        return new ModelAndView("conteudo :: //section", conteudos.paginaTematica(paginaTematica).getModel());
    }

}
