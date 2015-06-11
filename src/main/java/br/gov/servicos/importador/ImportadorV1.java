package br.gov.servicos.importador;

import br.gov.servicos.servico.*;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static br.gov.servicos.foundation.IO.read;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;
import static org.jsoup.Jsoup.parse;
import static org.jsoup.parser.Parser.xmlParser;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ManagedResource
public class ImportadorV1 {

    ResourcePatternResolver resolver;
    ServicoRepository servicos;

    @Autowired
    public ImportadorV1(ResourcePatternResolver resolver,
                        ServicoRepository servicos) {

        this.resolver = resolver;
        this.servicos = servicos;
    }

    @ManagedOperation
    public Iterable<Servico> importar(File repositorioCartas) throws IOException {
        Set<Servico> servicosImportados = cartasDeServicoEm(repositorioCartas)
                .parallel()
                .map(this::toDocument)
                .map(this::toServico)
                .collect(toSet());

        if (servicosImportados.isEmpty())
            return emptyList();

        return servicos.save(servicosImportados);
    }

    private Stream<Resource> cartasDeServicoEm(File repositorioCartas) throws IOException {
        if (!repositorioCartas.exists())
            return Stream.empty();

        return Stream.of(
                resolver.getResources("file://" + repositorioCartas.getAbsolutePath() + "/**/v1/servicos/**/*.xml"));
    }

    @SneakyThrows
    private Document toDocument(Resource resource) {
        log.debug("Importando XML v1: {}", resource);
        return parse(read(resource), resource.getURI().toASCIIString(), xmlParser());
    }

    private Servico toServico(Document doc) {
        doc.outputSettings().prettyPrint(false); // respeita formatação de CDATA

        return new Servico()
                .withId(doc.select("servico > id").text().trim())
                .withTitulo(doc.select("servico > nome").text().trim())
                .withDescricao(doc.select("servico > descricao").html().trim())
                .withResponsavel(orgao(doc.select("servico > orgaoResponsavel")))
                .withPrestador(orgao(doc.select("servico > orgaoPrestador")))
                .withLinhasDaVida(linhasDaVida(doc.select("servico > eventosDaLinhaDaVida")))
                .withAreasDeInteresse(areasDeInteresse(doc.select("servico > areasDeInteresse")))
                .withPublicosAlvo(publicosAlvo(doc.select("servico > segmentosDaSociedade")))
                .withCanaisDePrestacao(canaisDePrestacao(doc.select("servico > canaisDePrestacao")))
                .withInformacoesUteis(informacoesUteis(doc.select("servico > informacoesUteis")))
                .withTaxa(doc.select("servico > custoTotalEstimado").text().trim())
                .withUrl(doc.select("servico > url").text().trim())
                .withUrlAgendamento(doc.select("servico > urlAgendamento").text().trim());
    }

    private Orgao orgao(Elements doc) {
        return new Orgao()
                .withId(doc.select("id").text().trim())
                .withNome(doc.select("nome").text().trim());
    }

    private List<InformacaoUtil> informacoesUteis(Elements doc) {
        return doc.select("informacaoUtil")
                .stream()
                .map(e -> new InformacaoUtil()
                        .withDescricao(e.select("descricao").text().trim())
                        .withTipo(e.attr("tipo").trim())
                        .withUrl(e.select("link").attr("href").trim()))
                .collect(toList());
    }

    private List<CanalDePrestacao> canaisDePrestacao(Elements doc) {
        return doc.select("canalDePrestacao")
                .stream()
                .map(e -> new CanalDePrestacao()
                        .withDescricao(e.select("descricao").text().trim())
                        .withTipo(e.attr("tipo").trim())
                        .withUrl(e.select("link").attr("href").trim()))
                .collect(toList());
    }

    private List<PublicoAlvo> publicosAlvo(Elements doc) {
        return doc.select("segmentoDaSociedade")
                .stream()
                .map(e -> new PublicoAlvo()
                        .withId(e.select("id").text().trim())
                        .withTitulo(e.select("nome").text().trim()))
                .collect(toList());
    }

    private List<LinhaDaVida> linhasDaVida(Elements doc) {
        return doc.select("eventoDaLinhaDaVida")
                .stream()
                .map(e -> new LinhaDaVida()
                        .withId(e.select("id").text().trim())
                        .withTitulo(e.select("nome").text().trim()))
                .collect(toList());
    }

    private List<AreaDeInteresse> areasDeInteresse(Elements doc) {
        return doc.select("areaDeInteresse")
                .stream()
                .map(e -> new AreaDeInteresse()
                        .withId(e.select("id").text().trim())
                        .withTitulo(e.select("nome").text().trim()))
                .collect(toList());
    }
}
