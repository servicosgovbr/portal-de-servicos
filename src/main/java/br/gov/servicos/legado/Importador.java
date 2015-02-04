package br.gov.servicos.legado;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
class Importador {

    private static final String XML_LEGADO = "guiadeservicos.xml";
    private final ServicoRepository servicos;

    @Autowired
    Importador(ServicoRepository servicos) {
        this.servicos = servicos;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        servicos.save(
                servicosLegados()
                        .map(Servico::servicoLegadoToServico)
                        .collect(toList())
        );

        return servicos.findAll();
    }

    private static Stream<Dados.Servicos.Servico> servicosLegados() throws IOException, JAXBException {
        return unmarshallDadosLegados()
                .getServicos()
                .getServico()
                .stream();
    }

    private static Dados unmarshallDadosLegados() throws IOException, JAXBException {
        URL xmlLegado = new ClassPathResource(XML_LEGADO).getURL();
        return (Dados) unmarshaller().unmarshal(xmlLegado);
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

}
