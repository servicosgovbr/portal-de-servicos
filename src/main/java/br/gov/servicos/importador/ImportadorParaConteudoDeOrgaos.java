package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.orgao.OrgaoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ImportadorParaConteudoDeOrgaos {

    OrgaoRepository orgaoRepository;
    ConteudoParser parser;

    @Autowired
    public ImportadorParaConteudoDeOrgaos(OrgaoRepository orgaoRepository, ConteudoParser parser) {
        this.orgaoRepository = orgaoRepository;
        this.parser = parser;
    }

    Stream<Conteudo> importar() {
        return orgaoRepository.findAll().stream().map((orgao) -> new Conteudo()
                .withId(orgao.getId())
                .withTipoConteudo("orgao")
                .withTitulo(orgao.getNome())
                .withConteudo(parser.conteudo(format("/conteudo/orgaos/%s.md", orgao.getId()))));
    }
}
