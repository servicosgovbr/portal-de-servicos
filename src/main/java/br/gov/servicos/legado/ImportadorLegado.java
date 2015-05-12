package br.gov.servicos.legado;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ManagedResource(
        objectName = "ServicosGovBr:type=ImportadorLegado",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
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

    private Stream<ServicoType> servicosLegados() throws IOException, JAXBException {

        return Stream.of(resolver.getResources("classpath:legado/*.xml"))
                .parallel()
                .map(r -> {
                    log.debug("XML legado utilizado para importação: {}", r);
                    try {
                        return (ServicoType) unmarshaller().unmarshal(r.getURL());
                    } catch (JAXBException | IOException e) {
                        log.error("Erro ao importar {}", r, e);
                        return null;
                    }
                })
                .filter(i -> i != null);
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

    public Iterable<Servico> importar() throws IOException, JAXBException {
        return servicos.save(
                servicosLegados()
                        .map(servicoLegadoParaServico)
                        .collect(toList()));
    }

    @ManagedOperation
    public void mapearParaXMLsIndependentes() throws JAXBException, IOException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");

        URL xmlLegado = new ClassPathResource(XML_LEGADO).getURL();
        log.debug("XML legado utilizado para importação: {}", xmlLegado);

        JAXBElement element = (JAXBElement) unmarshaller().unmarshal(xmlLegado);
        log.debug("XML legado lido com sucesso");

        ((DadosType) element.getValue()).getServicos().getServico().parallelStream().forEach(s -> {
            try {
                contexto.createMarshaller().marshal(s, new File("src/main/resources/legado/" + new Slugify().slugify(s.getTitulo()) + ".xml"));
            } catch (IOException | JAXBException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
