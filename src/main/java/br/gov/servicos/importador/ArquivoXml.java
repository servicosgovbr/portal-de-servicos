package br.gov.servicos.importador;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.FieldDefaults;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Boolean.parseBoolean;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ArquivoXml {

    Optional<Element> xml;

    public ArquivoXml(Element xml) {
        this.xml = ofNullable(xml);
        this.xml.ifPresent(x -> x.ownerDocument().outputSettings().prettyPrint(false));
    }

    public String atributo(String atributo) {
        return xml.map(x -> x.attr(atributo).trim()).orElse("");
    }

    public String atributo(String seletor, String atributo) {
        return navega(seletor).atributo(atributo);
    }

    public Boolean atrituboAtivo(String atributo) {
        return paraBooleano(atributo(atributo));
    }

    public Boolean textoAtivo(String seletor) {
        return paraBooleano(navega(seletor).texto());
    }

    public String texto() {
        return xml.map(x -> x.text().trim()).orElse("");
    }

    public String texto(String seletor) {
        return navega(seletor).texto();
    }

    public List<String> coleta(String seletor) {
        return coleta(seletor, ArquivoXml::texto);
    }

    @SafeVarargs
    public final <T> List<T> coleta(String seletor, Function<ArquivoXml, T> conversor, Predicate<T>... filtros) {
        return xml.map(
                x -> x.select(seletor)
                        .stream()
                        .map(e -> new ArquivoXml(e).converte(conversor))
                        .filter(s -> asList(filtros).stream().reduce(Predicate::and).orElse(any -> true).test(s))
                        .collect(toList())
        ).orElse(new ArrayList<T>());
    }

    public <T> T converte(Function<ArquivoXml, T> conversor) {
        return conversor.apply(this);
    }

    public <T> T converte(String seletor, Function<ArquivoXml, T> conversor) {
        return navega(seletor).converte(conversor);
    }

    private ArquivoXml navega(String seletor) {
        return new ArquivoXml(
                xml.map(x -> x.select(seletor).first()).orElse(null)
        );
    }

    @SuppressFBWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "Campos não preenchidos não devem ser false")
    private Boolean paraBooleano(String valor) {
        if ("true".equals(valor) || "false".equals(valor)) {
            return parseBoolean(valor);
        }

        return null;
    }

    public String html(String seletor) {
        return navega(seletor).html();
    }

    public String html() {
        return xml.map(x -> x.html().trim()).orElse("");
    }


}
