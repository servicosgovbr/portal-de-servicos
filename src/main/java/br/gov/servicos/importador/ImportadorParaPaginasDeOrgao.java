package br.gov.servicos.importador;

import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.orgao.UrlsSiorg;
import br.gov.servicos.utils.LeitorDeArquivos;
import br.gov.servicos.v3.schema.OrgaoXML;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
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
    LeitorDeArquivos leitorDeArquivos;

    @Autowired
    public ImportadorParaPaginasDeOrgao(Slugify slugify, ConteudoParser parser, OrgaoRepository orgaoRepository, LeitorDeArquivos leitorDeArquivos) {
        this.slugify = slugify;
        this.parser = parser;
        this.orgaoRepository = orgaoRepository;
        this.leitorDeArquivos = leitorDeArquivos;
    }

    @SneakyThrows
    Iterable<OrgaoXML> importar(RepositorioCartasServico repositorioCartasServico) {
        File diretorioOrgaos = repositorioCartasServico.get(ORGAO.getCaminhoPasta().toString()).getFile();

        log.info("Importando órgãos em {}", diretorioOrgaos);
        return orgaoRepository.save(Stream.of(diretorioOrgaos.listFiles((d, n) -> n.endsWith(ORGAO.getExtensao())))
                .map(this::deserializaOrgao)
                .map(this::processaCampos)
                .peek(s -> log.debug("{} importado com sucesso", s.getId()))
                .collect(toList()));
    }

    private OrgaoXML deserializaOrgao(File f) {
        String xml = leitorDeArquivos.ler(f).get();
        return unmarshal(new StreamSource(new StringReader(xml)), OrgaoXML.class)
                .withXml(xml);
    }

    private OrgaoXML processaCampos(OrgaoXML orgaoXML) {
        UrlsSiorg.salvarUrl(orgaoXML.getUrl());
        String conteudoMarkdown = criaConteudoMarkdown(orgaoXML);
        String contatoMarkdown = criaContatoMarkdown(orgaoXML);

        return orgaoXML
                .withId(slugify.slugify(orgaoXML.getUrl()))
                .withTipoConteudo(ORGAO.getNome())
                .withConteudo(parser.conteudo(orgaoXML.getConteudo()))
                .withHtml(parser.conteudoHtml(conteudoMarkdown))
                .withContatoHtml(parser.conteudoHtml(contatoMarkdown));
    }

    private String criaContatoMarkdown(OrgaoXML orgaoXML) {
        return orgaoXML.getContato() == null ? "" : "Contato" + System.lineSeparator() +
                "---" + System.lineSeparator() + System.lineSeparator() +
                orgaoXML.getContato().trim();
    }

    private String criaConteudoMarkdown(OrgaoXML orgaoXML) {
        return orgaoXML.getConteudo() == null ? "" : orgaoXML.getNome().trim() + System.lineSeparator() +
                "---" + System.lineSeparator() + System.lineSeparator() +
                orgaoXML.getConteudo().trim();
    }
}