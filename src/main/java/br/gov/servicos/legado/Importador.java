package br.gov.servicos.legado;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
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
import java.util.stream.Stream;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
public class Importador {

    private static final String INDEX_NAME = "guia-de-servicos";
    private static final String XML_LEGADO = "guiadeservicos.xml";
    private static final String SETTINGS = "/elasticsearch/settings.json";

    ElasticsearchTemplate es;
    ServicoRepository servicos;
    ServicoLegadoParaServico servicoLegadoParaServico;

    @Autowired
    Importador(ElasticsearchTemplate es, ServicoRepository servicos, ServicoLegadoParaServico servicoLegadoParaServico) {
        this.es = es;
        this.servicos = servicos;
        this.servicoLegadoParaServico = servicoLegadoParaServico;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        recriarIndice();

        return servicos.save(
                servicosLegados()
                        .map(servicoLegadoParaServico)
                        .collect(toList()));
    }

    private void recriarIndice() throws IOException {
        if (es.indexExists(INDEX_NAME)) {
            es.deleteIndex(INDEX_NAME);
        }

        es.createIndex(INDEX_NAME, settings());
        es.putMapping(Busca.class);
        es.putMapping(Servico.class);
    }

    private static String settings() throws IOException {
        ClassPathResource resource = new ClassPathResource(SETTINGS);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), defaultCharset());

        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(joining("\n"));
        }
    }

    private static Stream<ServicoType> servicosLegados() throws IOException, JAXBException {
        return unmarshallDadosLegados()
                .getServicos()
                .getServico()
                .parallelStream();
    }

    private static DadosType unmarshallDadosLegados() throws IOException, JAXBException {
        URL xmlLegado = new ClassPathResource(XML_LEGADO).getURL();
        JAXBElement element = (JAXBElement) unmarshaller().unmarshal(xmlLegado);

        return (DadosType) element.getValue();
    }

    private static Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("br.gov.servicos.legado");
        return contexto.createUnmarshaller();
    }

}
