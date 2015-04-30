package br.gov.servicos.legado;

import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import com.github.slugify.Slugify;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MapaDeLinhasDaVidaTest {

    @Test
    public void deveRetornarListaVaziaSeMapeamentoDeServicoNaoExistir() throws IOException {
        MapaDeLinhasDaVida config = new MapaDeLinhasDaVida(new Slugify());
        assertThat(config.linhasDaVida("titulo"), equalTo(Collections.EMPTY_LIST));
    }

    @Test
    public void deveRetornarDuasLinhasDaVidaParaServico() throws Exception {
        MapaDeLinhasDaVida config = new MapaDeLinhasDaVida(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Consulta Situação do Requerimento de Benefício Previdenciário");

        assertThat(linhas, equalTo(asList(
                new LinhaDaVida()
                        .withId("trabalhar")
                        .withTitulo("Trabalhar"),

                new LinhaDaVida()
                        .withId("aposentar-se")
                        .withTitulo("Aposentar-se"))));
    }

    @Test
    public void deveRetornarUmaLinhaDaVidaParaServico() throws Exception {
        MapaDeLinhasDaVida config = new MapaDeLinhasDaVida(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Carteira Nacional De Vigilante (CNV)");

        assertThat(linhas, equalTo(singletonList(
                new LinhaDaVida()
                        .withId("emitir-seus-documentos")
                        .withTitulo("Emitir seus documentos"))));
    }

    @Test
    public void deveRetornarUmaLinhaDaVidaNovaParaServicoExistente() throws Exception {
        MapaDeLinhasDaVida config = new MapaDeLinhasDaVida(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Atualização cadastral de imóvel rural (Cafir)");

        assertThat(linhas, equalTo(singletonList(
                new LinhaDaVida()
                        .withId("ter-um-imovel")
                        .withTitulo("Ter um imóvel"))));
    }

    @Test
    public void deveRetornarListaVaziaParaServico() throws Exception {
        MapaDeLinhasDaVida config = new MapaDeLinhasDaVida(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Instituto Nacional de Meteorologia (INMET)");

        assertThat(linhas, equalTo(Collections.EMPTY_LIST));
    }

}