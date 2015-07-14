package br.gov.servicos.importador;

import br.gov.servicos.servico.*;
import br.gov.servicos.servico.areaDeInteresse.AreaDeInteresse;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.github.slugify.Slugify;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Arrays.asList;
import static java.util.Optional.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ImportadorV2 {

    private ServicoRepository servicos;
    private Slugify slugify;

    @Autowired
    public ImportadorV2(ServicoRepository servicos, Slugify slugify) {
        this.servicos = servicos;
        this.slugify = slugify;
    }

    @SneakyThrows
    public Iterable<Servico> importar(@NonNull File repositorioCartas) {
        return servicos.save(asList(arquivosXml(diretorioV2(repositorioCartas)))
                .parallelStream()
                .map(this::carregar)
                .map(Optional::get)
                .collect(toList()));
    }

    private File[] arquivosXml(File dir) {
        return ofNullable(dir.listFiles((f, name) -> name.endsWith(".xml")))
                .orElse(new File[0]);
    }

    private File diretorioV2(@NonNull File repositorioCartas) {
        return new File(repositorioCartas.getAbsoluteFile() + "/cartas-servico/v2/servicos");
    }

    @SneakyThrows
    public Optional<Servico> carregar(File id) {
        return conteudoServicoV2(id)
                .map(this::parse)
                .map(xml -> xml.select("servico").first())
                .map(ArquivoXml::new)
                .map(this::carregar);
    }

    private Optional<String> conteudoServicoV2(File arquivo) {
        if (arquivo.exists()) {
            log.info("Arquivo {} encontrado", arquivo);
            return ler(arquivo);
        }

        log.info("Arquivo {} não encontrado", arquivo);
        return empty();
    }

    @SneakyThrows
    private Optional<String> ler(File arquivo) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), defaultCharset()))) {
            return of(reader.lines().collect(joining("\n")));
        }
    }

    @SneakyThrows
    private Element parse(String conteudo) {
        return Jsoup.parse(conteudo, "/", Parser.xmlParser());
    }

    public Servico carregar(ArquivoXml xml) {
        return new Servico()
                .withId(slugify.slugify(xml.texto("nome")))
                .withTitulo(xml.texto("nome"))
                .withNomesPopulares(xml.texto("nomes-populares"))
                .withDescricao(xml.html("descricao"))
                .withPalavrasChave(xml.texto("palavras-chave"))

                .withSegmentosDaSociedade(xml.coleta("segmentos-da-sociedade > segmento-da-sociedade", s -> new PublicoAlvo()
                        .withId(s.texto("id"))
                        .withTitulo(s.texto("nome")), x -> !x.isEmpty()))

                .withEventosDaLinhaDaVida(xml.coleta("eventos-da-linha-da-vida > evento-da-linha-da-vida", e -> new LinhaDaVida()
                        .withId(e.texto("id"))
                        .withTitulo(e.texto("nome")), x -> !x.isEmpty()))

                .withLegislacoes(xml.coleta("legislacao-relacionada > link", l -> l.atributo("href")))
                .withSolicitantes(xml.coleta("solicitantes > solicitante", ArquivoXml::texto, x -> !x.isEmpty()))
                .withGratuito(xml.textoAtivo("gratuito"))
                .withSituacao(xml.texto("situacao"))

                .withAreasDeInteresse(xml.coleta("areas-de-interesse > area-de-interesse", a -> new AreaDeInteresse()
                        .withId(a.texto("id"))
                        .withArea(a.texto("area")), x -> !x.isEmpty()))

                .withTempoEstimado(xml.converte("tempo-total-estimado", t -> {
                    if ("entre".equals(t.atributo("tipo"))) {
                        return new TempoEstimado()
                                .withTipo(t.atributo("tipo"))
                                .withEntreTipoMinimo(t.atributo("minimo", "tipo"))
                                .withEntreMinimo(t.texto("minimo"))
                                .withEntreTipoMaximo(t.atributo("maximo", "tipo"))
                                .withEntreMaximo(t.texto("maximo"))
                                .withExcecoes(t.texto("excecoes"));
                    } else if ("até".equals(t.atributo("tipo"))) {
                        return new TempoEstimado()
                                .withTipo(t.atributo("tipo"))
                                .withAteTipoMaximo(t.atributo("maximo", "tipo"))
                                .withAteMaximo(t.texto("maximo"))
                                .withExcecoes(t.texto("excecoes"));
                    }

                    return null;
                }))

                .withEtapas(xml.coleta("etapas > etapa", e ->
                        new Etapa()
                                .withTitulo(e.texto("titulo"))
                                .withDescricao(e.texto("descricao"))
                                .withDocumentos(e.coleta("documentos documento"))
                                .withCustos(e.coleta("custos custo",
                                        c -> new Custo()
                                                .withDescricao(c.texto("descricao"))
                                                .withValor(c.texto("valor")), x -> !x.isEmpty()))

                                .withCanaisDePrestacao(
                                        e.coleta("canais-de-prestacao canal-de-prestacao",
                                                c -> new CanalDePrestacao()
                                                        .withTipo(c.atributo("tipo"))
                                                        .withTitulo(c.texto("canal-de-prestacao > titulo"))
                                                        .withReferencia(c.texto("canal-de-prestacao > referencia"))
                                                        .withPreferencial(c.atrituboAtivo("preferencial")), x -> !x.isEmpty()))
                        , x -> !x.isEmpty()))

                .withOrgao(xml.converte("orgao",
                        o -> new Orgao()
                                .withId(o.texto("id"))
                                .withNome(o.texto("nome"))));
    }

}