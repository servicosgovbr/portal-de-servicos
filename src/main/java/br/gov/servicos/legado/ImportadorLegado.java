package br.gov.servicos.legado;

import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorLegado {

    private static final String XML_LEGADO = "guiadeservicos.xml";

    ServicoRepository servicos;
    ServicoLegadoParaServico servicoLegadoParaServico;

    @Autowired
    ImportadorLegado(ServicoRepository servicos, ServicoLegadoParaServico servicoLegadoParaServico) {
        this.servicos = servicos;
        this.servicoLegadoParaServico = servicoLegadoParaServico;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        return servicos.save(
                servicosLegados()
                        .map(servicoLegadoParaServico)
                        .collect(toList()));
    }

    private static Stream<ServicoType> servicosLegados() throws IOException, JAXBException {
        return unmarshallDadosLegados()
                .getServicos()
                .getServico()
                .parallelStream();
    }

    private static DadosType unmarshallDadosLegados() throws IOException, JAXBException {
        URL xmlLegado = new ClassPathResource(XML_LEGADO).getURL();
        log.debug("Xml legado utilizado para importação: {}", xmlLegado);

        JAXBElement element = (JAXBElement) unmarshaller().unmarshal(xmlLegado);
        return (DadosType) element.getValue();
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

}
