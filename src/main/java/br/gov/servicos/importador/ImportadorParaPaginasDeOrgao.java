package br.gov.servicos.importador;

import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.orgao.UrlsSiorg;
import br.gov.servicos.v3.schema.OrgaoXML;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Stream;

import static br.gov.servicos.TipoPagina.ORGAO;
import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ImportadorParaPaginasDeOrgao {

    private Slugify slugify;
    ConteudoParser parser;
    OrgaoRepository orgaoRepository;

    @Autowired
    public ImportadorParaPaginasDeOrgao(Slugify slugify, ConteudoParser parser, OrgaoRepository orgaoRepository) {
        this.slugify = slugify;
        this.parser = parser;
        this.orgaoRepository = orgaoRepository;
    }

    @SneakyThrows
    Iterable<OrgaoXML> importar(RepositorioCartasServico repositorioCartasServico) {
        File diretorioOrgaos = repositorioCartasServico.get(ORGAO.getCaminhoPasta().toString()).getFile();

        log.info("Importando órgãos em {}", diretorioOrgaos);
        return orgaoRepository.save(Stream.of(diretorioOrgaos.listFiles((d, n) -> n.endsWith(ORGAO.getExtensao())))
                .map(f -> unmarshal(f, OrgaoXML.class))
                .map(this::processaCampos)
                .peek(s -> log.debug("{} importado com sucesso", s.getId()))
                .collect(toList()));
    }

    private OrgaoXML processaCampos(OrgaoXML orgaoXML) {
        String markdown = orgaoXML.getNome().trim() + System.lineSeparator() +
                "---" + System.lineSeparator() + System.lineSeparator() +
                orgaoXML.getConteudo().trim();

        UrlsSiorg.salvarUrl(orgaoXML.getUrl());
        return orgaoXML
                .withId(slugify.slugify(orgaoXML.getUrl()))
                .withTipoConteudo(ORGAO.getNome())
                .withConteudo(parser.conteudo(orgaoXML.getConteudo()))
                .withHtml(parser.conteudoHtml(markdown));
    }
}