package br.gov.servicos.config;

import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import com.github.slugify.Slugify;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LinhasDaVidaDeServicosConfigTest {

    @Test
    public void deveRetornarListaVaziaSeMapeamentoDeServicoNaoExistir() throws IOException {
        LinhasDaVidaDeServicosConfig config = new LinhasDaVidaDeServicosConfig(new Slugify());
        assertThat(config.linhasDaVida("titulo"), equalTo(Collections.EMPTY_LIST));
    }

    @Test
    public void deveRetornarDuasLinhasDaVidaParaServico() throws Exception {
        LinhasDaVidaDeServicosConfig config = new LinhasDaVidaDeServicosConfig(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Consulta Situação do Requerimento de Benefício Previdenciário");

        assertThat(linhas, equalTo(Arrays.asList(
                new LinhaDaVida()
                        .withId("trabalhar")
                        .withTitulo("Trabalhar"),
                new LinhaDaVida()
                        .withId("aposentar-se")
                        .withTitulo("Aposentar-se"))));
    }

    @Test
    public void deveRetornarUmaLinhaDaVidaParaServico() throws Exception {
        LinhasDaVidaDeServicosConfig config = new LinhasDaVidaDeServicosConfig(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Carteira Nacional De Vigilante (CNV) ");

        assertThat(linhas, equalTo(Arrays.asList(
                new LinhaDaVida()
                .withId("emitir-seus-documentos")
                .withTitulo("Emitir seus documentos"))));
    }

    @Test
    public void deveRetornarListaVaziaParaServico() throws Exception {
        LinhasDaVidaDeServicosConfig config = new LinhasDaVidaDeServicosConfig(new Slugify());
        List<LinhaDaVida> linhas = config.linhasDaVida("Instituto Nacional de Meteorologia ( INMET)");

        assertThat(linhas, equalTo(Collections.EMPTY_LIST));
    }

}