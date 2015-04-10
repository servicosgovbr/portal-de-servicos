package br.gov.servicos.cartaServicos;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static br.gov.servicos.cartaServicos.MotivoFalha.ERRO_DE_VALIDACAO;
import static br.gov.servicos.cartaServicos.MotivoFalha.REQUER_ATRIBUTOS;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.junit.Assert.fail;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class Tipo {

    private static final String XML_TESTE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><%s %s>%s</%s>";

    Map<String, String> atributos = new HashMap<>();
    Validator validador;
    String nome;

    Tipo(Validator validator, String nome) {
        this.validador = validator;
        this.nome = nome;
    }

    Tipo attr(String atributo, String valor) {
        atributos.put(atributo, valor);
        return this;
    }

    @SneakyThrows
    Tipo aceitaValor(String valor) {
        String atributosElemento = atributos.entrySet().stream()
                .reduce("",
                        (acc, entry) -> format("%s %s=\"%s\"", acc, entry.getKey(), entry.getValue()),
                        String::concat);

        String paraValidar = format(XML_TESTE, nome, atributosElemento, valor, nome);
        validador.validate(new StreamSource(new StringReader(paraValidar)));

        return this;
    }

    Tipo naoAceitaValor(String valor) {
        return naoAceitaValor(valor, ERRO_DE_VALIDACAO);
    }

    Tipo naoAceitaValor(String valor, MotivoFalha motivo) {
        try {
            aceitaValor(valor);
            fail(format("Campo %s aceitou entrada supostamente inválida %s", nome, valor));
        } catch (Exception e) {
            motivo.valida(e);
        }

        return this;
    }

    Tipo aceitaVazio() {
        return aceitaValor("");
    }

    Tipo naoAceitaVazio() {
        return naoAceitaValor("");
    }

    Tipo possuiTamanhoMinimo(int n) {
        return aceitaValor(repeat("X", n));
    }

    Tipo possuiTamanhoMaximo(int n) {
        return naoAceitaValor(repeat("X", n + 1));
    }

    Tipo eInvalido() {
        return naoAceitaValor("", REQUER_ATRIBUTOS);
    }

    Tipo eValido() {
        return aceitaVazio();
    }
}
