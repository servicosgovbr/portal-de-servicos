package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.orgao.OrgaoRepository;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

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

    @SneakyThrows
    Stream<Conteudo> importar(RepositorioCartasServico repositorioCartasServico) {
        return orgaoRepository.findAll()
                .stream()
                .map(orgao -> new Conteudo()
                        .withId(orgao.getId())
                        .withTipoConteudo("orgao")
                        .withNome(orgao.getNome())
                        .withConteudo(parser.conteudo(acessarDocumento(repositorioCartasServico, orgao.getId()))));
    }

    private Resource acessarDocumento(RepositorioCartasServico repositorioCartasServico, String id) {
        String caminhoDocumento = String.format("conteudo/orgaos/%s.md", id);
        return repositorioCartasServico.acessarDocumento(caminhoDocumento);
    }
}