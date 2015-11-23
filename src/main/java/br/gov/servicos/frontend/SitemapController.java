package br.gov.servicos.frontend;

import br.gov.servicos.cms.PaginaEstaticaRepository;
import br.gov.servicos.cms.PaginaTematicaRepository;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.AreaDeInteresse;
import br.gov.servicos.v3.schema.SegmentoDaSociedade;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SitemapController {

    ServicoRepository servicos;
    OrgaoRepository orgaos;
    private PaginaTematicaRepository paginasTematicas;
    private PaginaEstaticaRepository paginasEstaticas;

    @Autowired
    SitemapController(ServicoRepository servicos,
                      OrgaoRepository orgaos,
                      PaginaTematicaRepository paginasTematicas,
                      PaginaEstaticaRepository paginasEstaticas) {
        this.servicos = servicos;
        this.orgaos = orgaos;
        this.paginasTematicas = paginasTematicas;
        this.paginasEstaticas = paginasEstaticas;
    }

    @RequestMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    ModelAndView sitemap() {
        Map<String, Object> model = new HashMap<>();

        model.put("servicos", servicos.findAll());
        model.put("orgaos", orgaos.findAll());
        model.put("paginasTematicas", paginasTematicas.findAll());
        model.put("paginasEstaticas", paginasEstaticas.findAll());
        model.put("publicosAlvo", SegmentoDaSociedade.values());
        model.put("areasDeInteresse", AreaDeInteresse.values());

        return new ModelAndView("sitemap", model);
    }
}
