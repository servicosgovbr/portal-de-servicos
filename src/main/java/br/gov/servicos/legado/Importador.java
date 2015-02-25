package br.gov.servicos.legado;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import com.github.slugify.Slugify;
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
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
    Slugify slugify;

    @Autowired
    Importador(ElasticsearchTemplate es, ServicoRepository servicos, Slugify slugify) {
        this.es = es;
        this.servicos = servicos;
        this.slugify = slugify;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException, JAXBException {
        recriarIndice();

        servicos.save(
                servicosLegados()
                        .map(this::servicoLegadoToServico)
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

    private Servico servicoLegadoToServico(ServicoType legado) {
        return new Servico(
                slugify.slugify(legado.getTitulo()),
                legado.getTitulo(),
                legado.getDescricao(),
                getUrl(legado),
                legado.getTaxa(),
                getOrgaoPrestador2(legado),
                getOrgaoResponsavel2(legado),
                getAreasDeInteresse(legado),
                getLinhasDaVida(legado),
                getEventosDasLinhasDaVida(legado),
                0L,
                0L
        );
    }

    private static List<String> getLinhasDaVida(ServicoType servicoType) {
        PublicosAlvoType publicosAlvoType = servicoType.getPublicosAlvo();
        if (publicosAlvoType == null) return Arrays.asList();

        List<Serializable> publicosAlvo = publicosAlvoType.getContent();
        if (publicosAlvo == null || publicosAlvo.isEmpty()) return Arrays.asList();

        JAXBElement element = (JAXBElement) publicosAlvo.get(0);
        if (element == null) return Arrays.asList();

        PublicoAlvoType publicoAlvo = (PublicoAlvoType) element.getValue();
        if (publicoAlvo == null) return Arrays.asList();

        LinhasDaVivaType linhasDaViva = publicoAlvo.getLinhasDaViva();
        if (linhasDaViva == null) return Arrays.asList();

        List<LinhaDaVidaType> linhaDaVida = linhasDaViva.getLinhaDaVida();
        if(linhaDaVida == null) return Arrays.asList();

        return linhaDaVida.stream()
                .map(LinhaDaVidaType::getTitulo)
                .collect(toList());
    }


    private static Orgao getOrgaoPrestador2(ServicoType servicoType) {
        OrgaoPrestadorType prestador = servicoType.getOrgaoPrestador();
        String nome = prestador == null ? null : prestador.getTitulo();
        String telefone = prestador == null ? null : prestador.getTelefone();

        return new Orgao(nome, telefone);
    }

    private static Orgao getOrgaoResponsavel2(ServicoType servicoType) {
        OrgaoResponsavelType responsavel = servicoType.getOrgaoResponsavel();
        return responsavel == null ? null : new Orgao(responsavel.getTitulo(), null);
    }

    private static List<String> getEventosDasLinhasDaVida(ServicoType servicoType) {
        PublicosAlvoType publicosAlvoType = servicoType.getPublicosAlvo();
        if (publicosAlvoType == null) return Arrays.asList();

        List<Serializable> publicosAlvo = publicosAlvoType.getContent();
        if (publicosAlvo == null || publicosAlvo.isEmpty()) return Arrays.asList();

        JAXBElement element = (JAXBElement) publicosAlvo.get(0);
        if (element == null) return Arrays.asList();

        PublicoAlvoType publicoAlvo = (PublicoAlvoType) element.getValue();
        if (publicoAlvo == null) return Arrays.asList();

        LinhasDaVivaType linhasDaViva = publicoAlvo.getLinhasDaViva();
        if (linhasDaViva == null) return Arrays.asList();

        List<LinhaDaVidaType> linhaDaVida = linhasDaViva.getLinhaDaVida();
        if (linhaDaVida == null) return Arrays.asList();

        return linhaDaVida.stream()
                .flatMap((linhaDaVidaType) -> {
                    EventoslinhaDaVidaType eventoslinhaDaVida = linhaDaVidaType.getEventoslinhaDaVida();
                    if (eventoslinhaDaVida == null) return null;

                    List<EventolinhaDaVidaType> eventolinhaDaVida = eventoslinhaDaVida.getEventolinhaDaVida();
                    if (eventolinhaDaVida == null) return null;

                    return eventolinhaDaVida.stream().map(EventolinhaDaVidaType::getTitulo);
                })
                .collect(toList());

    }

    private static List<String> getAreasDeInteresse(ServicoType servicoType) {
        AreasInteresseType areasInteresseType = servicoType.getAreasInteresse();
        if(areasInteresseType == null) {
            return Arrays.asList();
        }

        return areasInteresseType.getArea().stream().map(AreaType::getTitulo).collect(toList());
    }

    private static String getUrl(ServicoType servicoType) {
        if (servicoType.getCanaisPrestacaoServico() == null || servicoType.getCanaisPrestacaoServico().getCanalPrestacaoServico() == null) {
            return null;
        }

        for (CanalPrestacaoServicoType canal : servicoType.getCanaisPrestacaoServico().getCanalPrestacaoServico()) {
            if(canal.getUrl() != null) {
                return canal.getUrl();
            }
        }

        return null;
    }


}
