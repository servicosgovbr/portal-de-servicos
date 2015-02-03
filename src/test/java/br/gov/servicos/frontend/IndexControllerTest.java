package br.gov.servicos.frontend;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IndexControllerTest {

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        controller = new IndexController();
    }

    @Test
    public void retornaViewAoAcessarIndex() throws Exception {
        assertThat(controller.index(), is("index"));
    }
}