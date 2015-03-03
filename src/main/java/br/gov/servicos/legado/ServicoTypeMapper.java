package br.gov.servicos.legado;

import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.LinhaDaVida;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.common.collect.Tuple;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoTypeMapper implements Function<ServicoType, Servico> {

    Slugify slugify;
    BeanFactory beanFactory;
    
    ExpressionParser parser = new SpelExpressionParser();

    @Autowired
    public ServicoTypeMapper(Slugify slugify, BeanFactory beanFactory) {
        this.slugify = slugify;
        this.beanFactory = beanFactory;
    }

    @Override
    public Servico apply(ServicoType legado) {
        return new Servico(
                slugify.slugify(legado.getTitulo()),
                legado.getTitulo(),
                legado.getDescricao(),
                url(legado),
                urlAgendamento(legado),
                legado.getTaxa(),
                orgaoPrestador(legado),
                orgaoResponsavel(legado),
                areasDeInteresse(legado),
                linhasDaVida(legado),
                eventosDasLinhasDaVida(legado),
                0L,
                0L
        );
    }

    private String url(ServicoType servicoType) {
        String[] urls = parser.parseExpression(
                "canaisPrestacaoServico?.canalPrestacaoServico?.![url]?:{}")
                .getValue(context(servicoType), String[].class);

        return Stream.of(urls)
                .filter(url -> url != null && !url.isEmpty())
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    private String urlAgendamento(ServicoType servicoType) {
        Tuple<String, String>[] tituloEUrl = parser.parseExpression(
                "informacoesUteis?.content?.![new org.elasticsearch.common.collect.Tuple(value?.tipoInformacaoUtil.titulo, value?.url)]")
                .getValue(context(servicoType), Tuple[].class);

        return Stream.of(tituloEUrl)
                .filter(t -> t.v1().equals("Agendamento"))
                .filter(t -> t.v2() != null && !t.v2().isEmpty())
                .findFirst()
                .map(Tuple::v2)
                .orElse(null);
    }

    private Orgao orgaoPrestador(ServicoType servicoType) {
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao(@slugify.slugify(orgaoPrestador?.titulo), orgaoPrestador?.titulo, orgaoPrestador?.telefone)")
                .getValue(context(servicoType), Orgao.class);
    }

    private Orgao orgaoResponsavel(ServicoType servicoType) {
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao(@slugify.slugify(orgaoResponsavel?.titulo), orgaoResponsavel?.titulo, null)")
                .getValue(context(servicoType), Orgao.class);
    }

    private List<AreaDeInteresse> areasDeInteresse(ServicoType servicoType) {
        return new ArrayList<>(
                ((List<String>) parser.parseExpression(
                        "areasInteresse?.area?.![titulo]?:{}")
                        .getValue(context(servicoType)))
                        .stream()
                        .map(s -> new AreaDeInteresse(slugify.slugify(s), s))
                        .collect(Collectors.toSet()));
    }

    private List<LinhaDaVida> linhasDaVida(ServicoType servicoType) {
        return new ArrayList<>(
                ((List<List<String>>)
                        parser.parseExpression(
                                "publicosAlvo?.content?.![value?.linhasDaViva?.linhaDaVida.![titulo]]?:{}")
                                .getValue(context(servicoType)))
                        .stream()
                        .flatMap(Collection::stream)
                        .map(x -> new LinhaDaVida(slugify.slugify(x), x))
                        .collect(Collectors.toSet()));
    }

    private List<String> eventosDasLinhasDaVida(ServicoType servicoType) {
        return ((List<List<List<String>>>)
                parser.parseExpression(
                        "publicosAlvo?.content?.![value?.linhasDaViva?.linhaDaVida.![eventoslinhaDaVida?.eventolinhaDaVida?.![titulo]]]?:{}")
                        .getValue(context(servicoType)))
                .stream()
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private StandardEvaluationContext context(ServicoType servicoType) {
        StandardEvaluationContext context = new StandardEvaluationContext(servicoType);
        context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));
        return context;
    }
}
