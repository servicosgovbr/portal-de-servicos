package br.gov.servicos.legado;

import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoTypeMapper implements Function<ServicoType, Servico> {
    Slugify slugify;

    SpelExpressionParser parser = new SpelExpressionParser();

    @Autowired
    public ServicoTypeMapper(Slugify slugify) {
        this.slugify = slugify;
    }

    @Override
    public Servico apply(ServicoType legado) {
        return new Servico(
                slugify.slugify(legado.getTitulo()),
                legado.getTitulo(),
                legado.getDescricao(),
                url(legado),
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

    @SuppressWarnings("unchecked")
    private List<String> linhasDaVida(ServicoType servicoType) {
        return ((List<List<String>>)
                parser.parseExpression(
                        "publicosAlvo?.content.![value?.linhasDaViva?.linhaDaVida.![titulo]]?:{}")
                        .getValue(servicoType))
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Orgao orgaoPrestador(ServicoType servicoType) {
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao(orgaoPrestador?.titulo, orgaoPrestador?.telefone)")
                .getValue(servicoType, Orgao.class);
    }

    private Orgao orgaoResponsavel(ServicoType servicoType) {
        return parser.parseExpression(
                "new br.gov.servicos.servico.Orgao(orgaoResponsavel?.titulo, null)")
                .getValue(servicoType, Orgao.class);
    }

    @SuppressWarnings("unchecked")
    private List<String> eventosDasLinhasDaVida(ServicoType servicoType) {
        return ((List<List<List<String>>>)
                parser.parseExpression(
                        "publicosAlvo?.content.![value?.linhasDaViva?.linhaDaVida.![eventoslinhaDaVida?.eventolinhaDaVida?.![titulo]]]?:{}")
                        .getValue(servicoType))
                .stream()
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<String> areasDeInteresse(ServicoType servicoType) {
        return parser.parseExpression(
                "areasInteresse?.area?.![titulo]?:{}")
                .getValue(servicoType, List.class);
    }

    @SuppressWarnings("unchecked")
    private String url(ServicoType servicoType) {
        List<String> urls = parser.parseExpression(
                "canaisPrestacaoServico?.canalPrestacaoServico?.![url]?:{}")
                .getValue(servicoType, List.class);

        return urls.isEmpty() ? null : urls.get(0);
    }
}
