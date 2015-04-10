package br.gov.servicos.cartaServicos;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.net.URL;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class TiposTest {

    Validator validador;

    @Before
    public void setUp() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setResourceResolver(new ClassPathResolver());

        URL xsd = getClass().getResource("/tipos-teste.xsd");
        validador = schemaFactory.newSchema(xsd).newValidator();
    }

    @Test
    public void idEObrigatorio() {
        tipo("id").naoAceitaVazio();
    }

    @Test
    public void idDeveAceitarApenasSlugs() {
        tipo("id")
                .naoAceitaValor("id do serviço")
                .aceitaValor("id-do-servico");
    }

    @Test
    public void linkDeveTerRelEHref() {
        tipo("link").attr("rel", "").eInvalido();
        tipo("link").attr("href", "").eInvalido();

        tipo("link")
                .attr("rel", "self")
                .attr("href", "http://localhost:8080/servicos/id")
                .eValido();
    }

    @Test
    public void textoCurtoNaoPodeSerVazio() {
        tipo("textoCurto")
                .naoAceitaVazio()
                .possuiTamanhoMinimo(5);
    }

    @Test
    public void textoCurtoOpcionalNaoDevePassarDe255Caracteres() {
        tipo("textoCurtoOpcional").possuiTamanhoMaximo(255);
    }

    @Test
    public void textoLongoNaoPodeSerVazio() {
        tipo("textoLongo")
                .naoAceitaVazio()
                .possuiTamanhoMinimo(50);
    }

    @Test
    public void textoLongoNaoDevePassarDe500Caracteres() {
        tipo("textoLongoOpcional").possuiTamanhoMaximo(500);
    }

    private Tipo tipo(String nome) {
        return new Tipo(validador, nome);
    }

}
