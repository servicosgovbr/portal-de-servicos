package br.gov.servicos.legado;

import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.LinhaDaVida;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
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
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoLegadoParaServico implements Function<ServicoType, Servico> {

    Slugify slugify;
    BeanFactory beanFactory;

    ExpressionParser parser = new SpelExpressionParser();

    @Autowired
    public ServicoLegadoParaServico(Slugify slugify, BeanFactory beanFactory) {
        this.slugify = slugify;
        this.beanFactory = beanFactory;
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
                .withLinhasDaVida(linhasDaVida(legado))
                .withEventosDasLinhasDaVida(eventosDasLinhasDaVida(legado))
                .withAcessos(0L)
                .withAtivacoes(0L);
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
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao()" +
                        ".withId(@slugify.slugify(orgaoPrestador?.titulo))" +
                        ".withNome(orgaoPrestador?.titulo)" +
                        ".withTelefone(orgaoPrestador?.telefone)")
                .getValue(context(servicoType), Orgao.class);
    }

    private Orgao orgaoResponsavel(ServicoType servicoType) {
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao()" +
                        ".withId(@slugify.slugify(orgaoResponsavel?.titulo))" +
                        ".withNome(orgaoResponsavel?.titulo)")
                .getValue(context(servicoType), Orgao.class);
    }

    private List<AreaDeInteresse> areasDeInteresse(ServicoType servicoType) {
        String[] areasDeInteresse = parser.parseExpression("areasInteresse?.area?.![titulo]?:{}")
                .getValue(context(servicoType), String[].class);

        return new ArrayList<>(
                stream(areasDeInteresse)
                        .map(titulo -> new AreaDeInteresse().withId(slugify.slugify(titulo)).withTitulo(titulo))
                        .collect(Collectors.toSet())
        );
    }

    private List<LinhaDaVida> linhasDaVida(ServicoType servicoType) {
        String[][] linhasDaVida = parser.parseExpression(
                "publicosAlvo?.content?.![value?.linhasDaViva?.linhaDaVida.![titulo]]?:{}")
                .getValue(context(servicoType), String[][].class);

        return new ArrayList<>(
                stream(linhasDaVida)
                        .flatMap(Arrays::stream)
                        .map(titulo -> new LinhaDaVida().withId(slugify.slugify(titulo)).withTitulo(titulo))
                        .collect(Collectors.toSet())
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
                        .collect(Collectors.toSet())
        );
    }

    private StandardEvaluationContext context(ServicoType servicoType) {
        StandardEvaluationContext context = new StandardEvaluationContext(servicoType);
        context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));

        return context;
    }
}
