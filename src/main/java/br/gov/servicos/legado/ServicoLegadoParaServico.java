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
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.base.Strings.isNullOrEmpty;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoLegadoParaServico implements Function<ServicoType, Servico> {

    Slugify slugify;
    BeanFactory beanFactory;
    ConteudoConfig config;

    ExpressionParser parser = new SpelExpressionParser();

    @Autowired
    public ServicoLegadoParaServico(Slugify slugify, BeanFactory beanFactory, ConteudoConfig config) {
        this.slugify = slugify;
        this.beanFactory = beanFactory;
        this.config = config;
    }

    @Override
    public Servico apply(ServicoType legado) {
        return new Servico()
                .withId(slugify.slugify(legado.getTitulo()))
                .withTitulo(legado.getTitulo())
                .withDescricao(legado.getDescricao())
                .withUrl(url(legado))
                .withUrlAgendamento(urlAgendamento(legado))
                .withTaxa(legado.getTaxa())
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
        return parser.parseExpression("informacoesUteis?.content?.![" +
                "new br.gov.servicos.servico.InformacaoUtil(value?.descricao, value?.tipoInformacaoUtil?.titulo, value?.url)" +
                "]")
                .getValue(context(legado), List.class);
    }

    @SuppressWarnings("unchecked")
    private List<CanalDePrestacao> canaisDePrestacao(ServicoType legado) {
        return parser.parseExpression("canaisPrestacaoServico?.canalPrestacaoServico?.![" +
                "new br.gov.servicos.servico.CanalDePrestacao(descricao, tipoCanalPrestacaoServico?.titulo, url)" +
                "]")
                .getValue(context(legado), List.class);
    }

    private String url(ServicoType servicoType) {
        String[] urls = parser.parseExpression(
                "canaisPrestacaoServico?.canalPrestacaoServico?.![url]?:{}")
                .getValue(context(servicoType), String[].class);

        return stream(urls)
                .filter(url -> url != null && !url.isEmpty())
                .findFirst()
                .orElse(null);
    }

    private String urlAgendamento(ServicoType servicoType) {
        String[] urls = parser.parseExpression(
                "informacoesUteis?.content?." +
                        "?[value?.tipoInformacaoUtil?.titulo == 'Agendamento' && !value?.url?.isEmpty()]." +
                        "![value?.url]")
                .getValue(context(servicoType), String[].class);

        return stream(urls).findFirst().orElse(null);
    }

    private Orgao orgaoPrestador(ServicoType servicoType) {
        String titulo = parser.parseExpression("orgaoPrestador?.titulo").getValue(context(servicoType), String.class);
        if (isNullOrEmpty(titulo)) {
            return null;
        }

        String telefone = parser.parseExpression("orgaoPrestador?.telefone").getValue(context(servicoType), String.class);

        return config.orgao(titulo)
                .withTelefone(telefone);
    }

    private Orgao orgaoResponsavel(ServicoType servicoType) {
        String titulo = parser.parseExpression("orgaoResponsavel?.titulo").getValue(context(servicoType), String.class);
        if (isNullOrEmpty(titulo)) {
            return null;
        }
        return config.orgao(titulo);
    }

    private List<AreaDeInteresse> areasDeInteresse(ServicoType servicoType) {
        String[] areasDeInteresse = parser.parseExpression("areasInteresse?.area?.![titulo]?:{}")
                .getValue(context(servicoType), String[].class);

        return new ArrayList<>(
                stream(areasDeInteresse)
                        .map(titulo -> new AreaDeInteresse().withId(slugify.slugify(titulo)).withTitulo(titulo))
                        .collect(toSet())
        );
    }

    private List<PublicoAlvo> publicoAlvo(ServicoType servicoType) {
        String[] publicosAlvo = parser.parseExpression("publicosAlvo?.content?.![value?.titulo]?:{}")
                .getValue(context(servicoType), String[].class);

        return new ArrayList<>(
                stream(publicosAlvo)
                        .map(p -> new PublicoAlvo().withId(slugify.slugify(p)).withTitulo(p))
                        .collect(toSet()));
    }

    private List<LinhaDaVida> linhasDaVida(ServicoType servicoType) {
        String[][] linhasDaVida = parser.parseExpression(
                "publicosAlvo?.content?.![value?.linhasDaViva?.linhaDaVida.![titulo]]?:{}")
                .getValue(context(servicoType), String[][].class);

        return new ArrayList<>(
                stream(linhasDaVida)
                        .flatMap(Arrays::stream)
                        .map(config::linhaDaVida)
                        .collect(toSet())
        );
    }

    private List<String> eventosDasLinhasDaVida(ServicoType servicoType) {
        String[][][] eventosLinhasDaVida = parser.parseExpression(
                "publicosAlvo?.content?.![value?.linhasDaViva?.linhaDaVida.![eventoslinhaDaVida?.eventolinhaDaVida?.![titulo]]]?:{}")
                .getValue(context(servicoType), String[][][].class);

        return new ArrayList<>(
                stream(eventosLinhasDaVida)
                        .flatMap(Arrays::stream)
                        .flatMap(Arrays::stream)
                        .collect(toSet())
        );
    }

    private StandardEvaluationContext context(ServicoType servicoType) {
        StandardEvaluationContext context = new StandardEvaluationContext(servicoType);
        context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));

        return context;
    }
}
