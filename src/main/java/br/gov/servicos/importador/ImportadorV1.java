package br.gov.servicos.importador;

import br.gov.servicos.foundation.IO;
import br.gov.servicos.servico.*;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ManagedResource
public class ImportadorV1 {

    ResourcePatternResolver resolver;
    ServicoRepository servicos;

    @Autowired
    public ImportadorV1(ResourcePatternResolver resolver, ServicoRepository servicos) {
        this.resolver = resolver;
        this.servicos = servicos;
    }

    @ManagedOperation
    public Iterable<Servico> importar() throws IOException {
        return servicos.save(
                Stream.of(resolver.getResources("classpath:v1/**/*.xml"))
                        .parallel()
                        .map(this::parse)
                        .map(this::documentToServico)
                        .collect(toSet()));
    }

    @SneakyThrows
    private Document parse(Resource resource) {
        log.debug("Importando XML v1: {}", resource);
        return Jsoup.parse(IO.read(resource), resource.getURI().toASCIIString(), Parser.xmlParser());
    }

    private Servico documentToServico(Document $) {
        $.outputSettings().prettyPrint(false); // respeita formatação de CDATA

        return new Servico()
                .withId($.select("servico > id").text().trim())
                .withTitulo($.select("servico > nome").text().trim())
                .withDescricao($.select("servico > descricao").html().trim())
                .withResponsavel(
                        new Orgao()
                                .withId($.select("orgaoResponsavel > id").text().trim())
                                .withNome($.select("orgaoResponsavel > nome").text().trim()))
                .withPrestador(
                        new Orgao()
                                .withId($.select("orgaoPrestador > id").text().trim())
                                .withNome($.select("orgaoPrestador > nome").text().trim()))
                .withLinhasDaVida(linhasDaVida($))
                .withAreasDeInteresse(areasDeInteresse($))
                .withPublicosAlvo(publicosAlvo($))
                .withCanaisDePrestacao(canaisDePrestacao($))
                .withInformacoesUteis(informacoesUteis($))
                .withTaxa($.select("servico > custoTotalEstimado").text().trim())
                .withUrl($.select("servico > url").text().trim())
                .withUrlAgendamento($.select("servico > urlAgendamento").text().trim())
                ;
    }

    private List<InformacaoUtil> informacoesUteis(Document $) {
        List<InformacaoUtil> canaisDePrestacao = new ArrayList<>();
        $.select("servico > informacoesUteis > informacaoUtil").forEach(e -> canaisDePrestacao.add(new InformacaoUtil()
                .withDescricao(e.select("descricao").text().trim())
                .withTipo(e.attr("tipo").trim())
                .withUrl(e.select("link").attr("href").trim())));
        return canaisDePrestacao;
    }

    private List<CanalDePrestacao> canaisDePrestacao(Document $) {
        List<CanalDePrestacao> canaisDePrestacao = new ArrayList<>();
        $.select("servico > canaisDePrestacao > canalDePrestacao").forEach(e -> canaisDePrestacao.add(new CanalDePrestacao()
                .withDescricao(e.select("descricao").text().trim())
                .withTipo(e.attr("tipo").trim())
                .withUrl(e.select("link").attr("href").trim())));
        return canaisDePrestacao;
    }

    private List<PublicoAlvo> publicosAlvo(Document $) {
        List<PublicoAlvo> publicoAlvo = new ArrayList<>();
        $.select("servico > segmentosDaSociedade > segmentoDaSociedade").forEach(e -> publicoAlvo.add(new PublicoAlvo()
                .withId(e.select("id").text().trim())
                .withTitulo(e.select("nome").text().trim())));
        return publicoAlvo;
    }

    private List<LinhaDaVida> linhasDaVida(Document $) {
        List<LinhaDaVida> linhasDaVida = new ArrayList<>();
        $.select("servico > eventosDaLinhaDaVida > eventoDaLinhaDaVida").forEach(e -> linhasDaVida.add(new LinhaDaVida()
                .withId(e.select("id").text().trim())
                .withTitulo(e.select("nome").text().trim())));
        return linhasDaVida;
    }

    private List<AreaDeInteresse> areasDeInteresse(Document $) {
        List<AreaDeInteresse> areasDeInteresse = new ArrayList<>();
        $.select("servico > areasDeInteresse > areaDeInteresse").forEach(e -> areasDeInteresse.add(new AreaDeInteresse()
                .withId(e.select("id").text().trim())
                .withTitulo(e.select("nome").text().trim())));
        return areasDeInteresse;
    }
}
