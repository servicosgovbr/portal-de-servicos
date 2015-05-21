package br.gov.servicos.legado;

import br.gov.servicos.config.ConteudoConfig;
import br.gov.servicos.servico.*;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.base.Strings.isNullOrEmpty;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoLegadoParaServico implements Function<ServicoType, Servico> {

    Slugify slugify;
    BeanFactory beanFactory;
    ConteudoConfig config;
    MapaDeLinhasDaVida linhasDaVida;
    MapaDePublicosAlvo publicosAlvo;
    ExpressionParser parser = new SpelExpressionParser();

    @Autowired
    public ServicoLegadoParaServico(Slugify slugify, BeanFactory beanFactory, ConteudoConfig config, MapaDeLinhasDaVida linhasDaVida, MapaDePublicosAlvo publicosAlvo) {
        this.slugify = slugify;
        this.beanFactory = beanFactory;
        this.config = config;
        this.linhasDaVida = linhasDaVida;
        this.publicosAlvo = publicosAlvo;
    }

    @Override
    public Servico apply(ServicoType legado) {
        return new Servico()
                .withId(slugify.slugify(legado.getTitulo()))
                .withTitulo(legado.getTitulo().trim())
                .withDescricao(legado.getDescricao().trim())
                .withUrl(url(legado))
                .withUrlAgendamento(urlAgendamento(legado))
                .withTaxa(legado.getTaxa().trim())
                .withPrestador(orgaoPrestador(legado))
                .withResponsavel(orgaoResponsavel(legado))
                .withAreasDeInteresse(areasDeInteresse(legado))
                .withPublicosAlvo(publicoAlvo(legado))
                .withLinhasDaVida(linhasDaVida(legado))
                .withCanaisDePrestacao(canaisDePrestacao(legado))
                .withInformacoesUteis(informacoesUteis(legado))
                .withEventosDasLinhasDaVida(eventosDasLinhasDaVida(legado));
    }

    @SuppressWarnings("unchecked")
    private List<InformacaoUtil> informacoesUteis(ServicoType legado) {
        return (List<InformacaoUtil>) parser.parseExpression("informacoesUteis?.content?" +
                ".?[#this instanceof T(javax.xml.bind.JAXBElement)]" +
                ".![new br.gov.servicos.servico.InformacaoUtil(value?.descricao.trim(), value?.tipoInformacaoUtil?.titulo.trim(), value?.url.trim())]")
                .getValue(context(legado), List.class)
                .stream()
                .filter(i -> !((InformacaoUtil) i).getUrl().equals(urlAgendamento(legado)))
                .collect(toList());
    }

    @SuppressWarnings("unchecked")
    private List<CanalDePrestacao> canaisDePrestacao(ServicoType legado) {
        return (List<CanalDePrestacao>) parser.parseExpression("canaisPrestacaoServico?.canalPrestacaoServico?.![" +
                "new br.gov.servicos.servico.CanalDePrestacao(descricao.trim(), tipoCanalPrestacaoServico?.titulo.trim(), url.trim())" +
                "]")
                .getValue(context(legado), List.class)
                .stream()
                .filter(i -> !((CanalDePrestacao) i).getUrl().equals(url(legado)))
                .collect(toList());
    }

    private String url(ServicoType servicoType) {
        String[] urls = parser.parseExpression(
                "canaisPrestacaoServico?.canalPrestacaoServico?.![url]?:{}")
                .getValue(context(servicoType), String[].class);

        return stream(urls)
                .filter(url -> url != null && !url.isEmpty())
                .findFirst()
                .map(String::trim)
                .orElse(null);
    }

    private String urlAgendamento(ServicoType servicoType) {
        String[] urls = parser.parseExpression(
                "informacoesUteis?.content?" +
                        ".?[#this instanceof T(javax.xml.bind.JAXBElement)]" +
                        ".?[value?.tipoInformacaoUtil?.titulo == 'Agendamento' && !value?.url?.isEmpty()]" +
                        ".![value?.url]")
                .getValue(context(servicoType), String[].class);

        return stream(urls)
                .findFirst()
                .map(String::trim)
                .orElse(null);
    }

    private Orgao orgaoPrestador(ServicoType servicoType) {
        String titulo = parser.parseExpression("orgaoPrestador?.titulo").getValue(context(servicoType), String.class);
        if (isNullOrEmpty(titulo)) {
            return null;
        }

        String telefone = parser.parseExpression("orgaoPrestador?.telefone").getValue(context(servicoType), String.class);

        return config.orgao(titulo.trim())
                .withTelefone(telefone.trim());
    }

    private Orgao orgaoResponsavel(ServicoType servicoType) {
        String titulo = parser.parseExpression("orgaoResponsavel?.titulo").getValue(context(servicoType), String.class);
        if (isNullOrEmpty(titulo)) {
            return null;
        }
        return config.orgao(titulo.trim());
    }

    private List<AreaDeInteresse> areasDeInteresse(ServicoType servicoType) {
        String[] areasDeInteresse = parser.parseExpression("areasInteresse?.area?.![titulo]?:{}")
                .getValue(context(servicoType), String[].class);

        return new ArrayList<>(
                stream(areasDeInteresse)
                        .map(titulo -> new AreaDeInteresse().withId(slugify.slugify(titulo)).withTitulo(titulo.trim()))
                        .collect(toSet())
        );
    }

    private List<PublicoAlvo> publicoAlvo(ServicoType servicoType) {
        String[] publicos = parser.parseExpression("publicosAlvo?.content?" +
                ".?[#this instanceof T(javax.xml.bind.JAXBElement)]" +
                ".![value?.titulo]?:{}")
                .getValue(context(servicoType), String[].class);

        return new ArrayList<>(
                stream(publicos)
                        .map(this.publicosAlvo::mapear)
                        .filter(Objects::nonNull)
                        .collect(toSet()));
    }

    private List<LinhaDaVida> linhasDaVida(ServicoType servicoType) {
        return linhasDaVida.linhasDaVida(servicoType.getTitulo().trim());
    }

    private List<String> eventosDasLinhasDaVida(ServicoType servicoType) {
        String[][][] eventosLinhasDaVida = parser.parseExpression(
                "publicosAlvo?.content?" +
                        ".?[#this instanceof T(javax.xml.bind.JAXBElement)]" +
                        ".![value?.linhasDaViva?.linhaDaVida.![eventoslinhaDaVida?.eventolinhaDaVida?.![titulo]]]?:{}")
                .getValue(context(servicoType), String[][][].class);

        return new ArrayList<>(
                stream(eventosLinhasDaVida)
                        .flatMap(Arrays::stream)
                        .flatMap(Arrays::stream)
                        .map(String::trim)
                        .collect(toSet())
        );
    }

    private StandardEvaluationContext context(ServicoType servicoType) {
        StandardEvaluationContext context = new StandardEvaluationContext(servicoType);
        context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));

        return context;
    }
}
