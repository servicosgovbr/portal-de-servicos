package br.gov.servicos.importador;

import br.gov.servicos.config.PortalDeServicosIndex;
import br.gov.servicos.orgao.Siorg;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.utils.LeitorDeArquivos;
import br.gov.servicos.v3.schema.OrgaoXML;
import br.gov.servicos.v3.schema.ServicoXML;
import com.github.slugify.Slugify;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Component
public class ImportadorServicos {

    PortalDeServicosIndex indices;

    ServicoRepository servicoRepository;

    Siorg siorg;

    Slugify slugify;

    LeitorDeArquivos leitorDeArquivos;

    @Autowired
    ImportadorServicos(PortalDeServicosIndex indices,
                       ServicoRepository servicoRepository,
                       Siorg siorg,
                       Slugify slugify,
                       LeitorDeArquivos leitorDeArquivos) {
        this.indices = indices;
        this.servicoRepository = servicoRepository;
        this.siorg = siorg;
        this.slugify = slugify;
        this.leitorDeArquivos = leitorDeArquivos;
    }

    @SneakyThrows
    public Iterable<ServicoXML> importar(RepositorioCartasServico repo) {
        log.info("Iniciando importação de serviços...");
        indices.recriar();

        return servicoRepository.save(
                Stream.of(repo.get("cartas-servico/v3/servicos").getFile()
                        .listFiles((d, n) -> n.endsWith(".xml")))
                        .parallel()
                        .map(this::deserializaServico)
                        .map(s -> s.withId(slugify.slugify(s.getNome())))
                        .map(s -> s.withOrgao(remapeiaESalvaOrgao(s.getOrgao())))
                        .map(s -> s.withIdAplicacao(getIdAplicacao(s)))
                        .peek(s -> log.debug("{} importado com sucesso", s.getId()))
                        .collect(toList())
        );
    }

    private String getIdAplicacao(ServicoXML s) {
        return Optional.ofNullable(s.getIdAplicacao())
                .map(String::trim)
                .orElse("");
    }

    private ServicoXML deserializaServico(File f) {
        String xml = leitorDeArquivos.ler(f).get();
        return unmarshal(new StreamSource(new StringReader(xml)), ServicoXML.class)
                .withXml(xml);
    }

    private OrgaoXML remapeiaESalvaOrgao(OrgaoXML orgao) {
        String url = orgao.getId();
        return orgao.withId(slugify.slugify(url))
                .withUrl(url);
    }
}
