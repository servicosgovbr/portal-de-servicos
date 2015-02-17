package br.gov.servicos;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
import br.gov.servicos.legado.DadosType;
import br.gov.servicos.legado.ServicoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
public class Importador {

    public static final String INDEX_NAME = "guia-de-servicos";
    private static final String XML_LEGADO = "guiadeservicos.xml";
    public static final String SETTINGS = "/elasticsearch/settings.json";
    private ElasticsearchTemplate es;
    private ServicoRepository servicos;

    @Autowired
    Importador(ElasticsearchTemplate es, ServicoRepository servicos) {
        this.es = es;
        this.servicos = servicos;
    }

    private static Stream<ServicoType> servicosLegados() throws IOException, JAXBException {
        return unmarshallDadosLegados()
                .getServicos()
                .getServico()
                .stream();
    }

    private static DadosType unmarshallDadosLegados() throws IOException, JAXBException {
        URL xmlLegado = new ClassPathResource(XML_LEGADO).getURL();
        return (DadosType) ((JAXBElement) unmarshaller().unmarshal(xmlLegado)).getValue();
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        recriarIndice();

        servicos.save(
                servicosLegados()
                        .map(Servico::servicoLegadoToServico)
                        .collect(toList()));

        return servicos.findAll();
    }

    private void recriarIndice() throws IOException {
        if (es.indexExists(INDEX_NAME)) {
            es.deleteIndex(INDEX_NAME);
        }

        es.createIndex(INDEX_NAME, readSettings());
        es.putMapping(Busca.class);
        es.putMapping(Servico.class);
    }

    private String readSettings() throws IOException {
        ClassPathResource resource = new ClassPathResource(SETTINGS);
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return br.lines().parallel().collect(Collectors.joining("\n"));
        }
    }
}
