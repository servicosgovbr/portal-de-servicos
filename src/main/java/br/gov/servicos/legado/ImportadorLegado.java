package br.gov.servicos.legado;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorLegado {

    private static final String XML_LEGADO = "guiadeservicos.xml";

    ResourcePatternResolver resolver;
    ServicoRepository servicos;
    ServicoLegadoParaServico servicoLegadoParaServico;

    @Autowired
    ImportadorLegado(ResourcePatternResolver resolver, ServicoRepository servicos, ServicoLegadoParaServico servicoLegadoParaServico) {
        this.resolver = resolver;
        this.servicos = servicos;
        this.servicoLegadoParaServico = servicoLegadoParaServico;
    }

    public Iterable<Servico> importar() throws IOException, JAXBException {
        return servicos.save(servicosImportados());
    }

    private List<Servico> servicosImportados() throws IOException {
        return Stream.of(resolver.getResources("classpath:legado/*.xml"))
                .parallel()
                .map(this::parsearXML)
                .filter(Objects::nonNull)
                .map(servicoLegadoParaServico)
                .collect(toList());
    }

    private ServicoType parsearXML(Resource resource) {
        log.debug("Importando XML legado: {}", resource);
        try {
            return (ServicoType) unmarshaller().unmarshal(resource.getURL());
        } catch (JAXBException | IOException e) {
            log.error("Erro ao importar {}", resource, e);
            return null;
        }
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }
}
