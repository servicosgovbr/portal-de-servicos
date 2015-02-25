package br.gov.servicos.frontend;

import br.gov.servicos.servico.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private ServicoRepository servicos;
    private IndexController controller;

    @Before
    public void setUp() {
        controller = new IndexController(servicos);
    }

    @Test
    public void redirecionaParaAPaginaInicial() {
        assertViewName(controller.index(), "index");
    }

    @Test
    public void retornaMetricasDeAcessoNoModel() {
        Page page = mock(Page.class);

        doReturn(page)
                .when(servicos)
                .findAll(new PageRequest(0, 9, new Sort(DESC, "acessos")));

        assertModelAttributeValue(controller.index(), "acessos", page);
    }

}