package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BuscaControllerTest {

    private BuscaController controller;

    @Before
    public void setUp() throws Exception {
        controller = new BuscaController();
    }

    @Test
    public void buscaRetornaView() throws Exception {
        assertThat(controller.busca(null).getViewName(), is("resultados-busca"));
    }

    @Test
    public void buscaRetornaResultadosNoModel() throws Exception {
        assertThat(controller.busca(null).getModel().get("resultados"), is(new ArrayList<String>()));
    }
}