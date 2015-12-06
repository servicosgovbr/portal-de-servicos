package br.gov.servicos.orgao;

import br.gov.servicos.cms.PaginaEstatica;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.OrgaoXML.PaginaOrgaoFormatter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Controller
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrgaoController {

    OrgaoRepository orgaoRepository;
    OrgaoRepositoryUtil orgaosRepositoryUtil;
    ServicoRepository servicos;
    private Formatter<OrgaoXML> orgaoXMLFormatter;

    @Autowired
    OrgaoController(OrgaoRepository orgaoRepository, OrgaoRepositoryUtil orgaosRepositoryUtil, ServicoRepository servicos, PaginaOrgaoFormatter orgaoXMLFormatter) {
        this.orgaoRepository = orgaoRepository;
        this.orgaosRepositoryUtil = orgaosRepositoryUtil;
        this.servicos = servicos;
        this.orgaoXMLFormatter = orgaoXMLFormatter;
    }

    @RequestMapping("/orgaos")
    ModelAndView orgaos() {
        return new ModelAndView("orgaos", "orgaos", orgaosRepositoryUtil.findAll());
    }

    @RequestMapping("/orgao/{id}")
    public ModelAndView orgao(@PathVariable("id") String idOrgao) {
        try {
            log.info("Carregando órgão: " + idOrgao);
            OrgaoXML orgaoXML = orgaoXMLFormatter.parse(idOrgao, Locale.forLanguageTag("pt-BR"));

            Map<String, Object> model = new HashMap<>();

            model.put("termo", orgaoXML.getId());
            model.put("conteudo", orgaoXML);

            log.info("Carregando serviços para órgão: " + idOrgao);
            model.put("resultados", servicos.findByOrgao(orgaoXML)
                    .stream()
                    .map(PaginaEstatica::fromServico)
                    .sorted(comparing(PaginaEstatica::getId))
                    .collect(toList()));

            log.info("Carregando página de órgão: " + idOrgao);
            return new ModelAndView("orgao", model);
        } catch (Throwable t) {
            log.error("Erro carregando órgão", t);
            Map<String, Object> model = new HashMap<>();
            model.put("errorMsg", idOrgao);
            model.put("urls", UrlsSiorg.getUrls());
            model.put("errorTrace", ExceptionUtils.getFullStackTrace(t));
            return new ModelAndView("error", model);
        }
    }
}
