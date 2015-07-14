package br.gov.servicos.servico;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TempoEstimadoTest {

    @Test
    public void deveImprimirFraseParaTempoMaximo() throws Exception {
        TempoEstimado tempo = new TempoEstimado()
                .withTipo("até")
                .withAteMaximo("30")
                .withAteTipoMaximo("dias úteis");

        assertThat(tempo.toString(), is("Até 30 dias úteis"));
    }

    @Test
    public void deveImprimirFraseParaFaixaDeTempoComTiposDiferentes() throws Exception {
        TempoEstimado tempo = new TempoEstimado()
                .withTipo("entre")
                .withEntreMinimo("5")
                .withEntreTipoMinimo("horas")
                .withEntreMaximo("2")
                .withEntreTipoMaximo("dias");

        assertThat(tempo.toString(), is("Entre 5 horas e 2 dias"));
    }

    @Test
    public void deveImprimirFraseParaFaixaDeTempoComTiposIguais() throws Exception {
        TempoEstimado tempo = new TempoEstimado()
                .withTipo("entre")
                .withEntreMinimo("5")
                .withEntreTipoMinimo("dias")
                .withEntreMaximo("10")
                .withEntreTipoMaximo("dias");

        assertThat(tempo.toString(), is("Entre 5 e 10 dias"));
    }
}