package br.gov.servicos.legado;

import br.gov.servicos.dominio.Busca;
import br.gov.servicos.dominio.Servico;
import br.gov.servicos.dominio.ServicoRepository;
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
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component
@ManagedResource(
        objectName = "ServicosGovBr:type=Importador",
        description = "Importa XML do Guia legado para o ElasticSearch"
)
class Importador {

    private static final String INDEX_NAME = "guia-de-servicos";
    private static final String XML_LEGADO = "guiadeservicos.xml";
    private static final String SETTINGS = "/elasticsearch/settings.json";

    private final ElasticsearchTemplate es;
    private final ServicoRepository servicos;

    @Autowired
    Importador(ElasticsearchTemplate es, ServicoRepository servicos) {
        this.es = es;
        this.servicos = servicos;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        recriarIndice();

        servicos.save(
                servicosLegados()
                        .map(Importador::servicoLegadoToServico)
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

    private static String readSettings() throws IOException {
        ClassPathResource resource = new ClassPathResource(SETTINGS);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream());

        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(joining("\n"));
        }
    }

    private static Stream<ServicoType> servicosLegados() throws IOException, JAXBException {
        return unmarshallDadosLegados()
                .getServicos()
                .getServico()
                .stream();
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

    private static Servico servicoLegadoToServico(ServicoType legado) {
        return new Servico(
                UUID.randomUUID().toString(),
                legado.getTitulo(),
                legado.getDescricao(),
                legado.getUrl(),
                legado.getTaxa(),
                legado.getOrgaoPrestador2(),
                legado.getOrgaoResponsavel2(),
                legado.getAreasDeInteresse(),
                legado.getLinhasDaVida(),
                legado.getEventosDasLinhasDaVida(),
                0L,
                0L
        );
    }
}
