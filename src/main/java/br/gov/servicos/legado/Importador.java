package br.gov.servicos.legado;

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
import java.util.stream.Collectors;

@Component
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
public class Importador {

    private ServicoRepository sr;

    @Autowired
    public Importador(ServicoRepository sr) {
        this.sr = sr;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        Dados dados = (Dados) unmarshaller().unmarshal(new URL(new ClassPathResource("guiadeservicos.xml").getURL().toString()));

        sr.save(
                dados.getServicos().getServico().stream().map(Servico::new).collect(Collectors.toList())
        );

        return sr.findAll();
    }

    private Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

}
