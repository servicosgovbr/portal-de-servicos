package br.gov.servicos.piwik;

import org.junit.Test;

import static org.junit.Assert.*;

public class PiwikPageTest {

    @Test
    public void deveRetornarUmaURIDaURLBase() throws Exception {
        PiwikPage page = new PiwikPage()
                .withUrl("/blah/teste");

        assertTrue(page.getPath().isPresent());
        assertEquals(page.getPath().get(), "/blah/teste");
    }

    @Test
    public void deveRetornarOptionalVazioSeUrlInvalida() throws Exception {
        PiwikPage page = new PiwikPage()
                .withUrl("http://servicos.gov.br/servico/teste None None None");

        assertFalse(page.getPath().isPresent());
    }

    @Test
    public void naoDeveRetornarIdDeServico() throws Exception {
        PiwikPage page = new PiwikPage()
                .withUrl("http://servicos.gov.br/linha-da-vida/linha-teste");

        assertFalse(page.getIdServico().isPresent());
    }

    @Test
    public void deveRetornarIdDeServicoPegoDaUrl() throws Exception {
        PiwikPage page = new PiwikPage()
                .withUrl("http://servicos.gov.br/servico/servico-teste");

        assertTrue(page.getIdServico().isPresent());
        assertEquals(page.getIdServico().get(), "servico-teste");
    }

    @Test
    public void deveRetornarIdDeServicoPegoDaUrlLegado() throws Exception {
        PiwikPage page = new PiwikPage()
                .withUrl("http://servicos.gov.br/repositorioServico/servico-teste");

        assertTrue(page.getIdServico().isPresent());
        assertEquals(page.getIdServico().get(), "servico-teste");
    }
}