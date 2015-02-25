package br.gov.servicos.legado;

import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ServicoTypeMapper implements Function<ServicoType, Servico> {
    Slugify slugify;

    Expression getLinhasDaVida;

    @Autowired
    public ServicoTypeMapper(Slugify slugify) {
        this.slugify = slugify;

        SpelExpressionParser parser = new SpelExpressionParser();

        getLinhasDaVida = parser.parseExpression("(" +
                "publicosAlvo?.content.size() == 0 ? " +
                "  null : " +
                "  publicosAlvo?.content?.get(0).value?.linhasDaViva?.linhaDaVida" +
                ")?:{}");
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

    private List<String> linhasDaVida(ServicoType servicoType) {
        @SuppressWarnings("unchecked")
        List<LinhaDaVidaType> linhaDaVida = (List<LinhaDaVidaType>) getLinhasDaVida.getValue(servicoType);

        return linhaDaVida.stream()
                .map(LinhaDaVidaType::getTitulo)
                .collect(toList());
    }

    private static Orgao orgaoPrestador(ServicoType servicoType) {
        OrgaoPrestadorType prestador = servicoType.getOrgaoPrestador();
        String nome = prestador == null ? null : prestador.getTitulo();
        String telefone = prestador == null ? null : prestador.getTelefone();

        return new Orgao(nome, telefone);
    }

    private static Orgao orgaoResponsavel(ServicoType servicoType) {
        OrgaoResponsavelType responsavel = servicoType.getOrgaoResponsavel();
        return responsavel == null ? null : new Orgao(responsavel.getTitulo(), null);
    }

    private List<String> eventosDasLinhasDaVida(ServicoType servicoType) {
        @SuppressWarnings("unchecked")
        List<LinhaDaVidaType> linhaDaVida = (List<LinhaDaVidaType>) getLinhasDaVida.getValue(servicoType);

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

    private static List<String> areasDeInteresse(ServicoType servicoType) {
        AreasInteresseType areasInteresseType = servicoType.getAreasInteresse();
        if (areasInteresseType == null) {
            return Arrays.asList();
        }

        return areasInteresseType.getArea().stream().map(AreaType::getTitulo).collect(toList());
    }

    private static String url(ServicoType servicoType) {
        if (servicoType.getCanaisPrestacaoServico() == null || servicoType.getCanaisPrestacaoServico().getCanalPrestacaoServico() == null) {
            return null;
        }

        for (CanalPrestacaoServicoType canal : servicoType.getCanaisPrestacaoServico().getCanalPrestacaoServico()) {
            if (canal.getUrl() != null) {
                return canal.getUrl();
            }
        }

        return null;
    }
}
