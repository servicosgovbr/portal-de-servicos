package br.gov.servicos.frontend;

import br.gov.servicos.dominio.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;

@SuppressWarnings("unchecked")
public class IndexControllerTest {

    @Mock
    ServicoRepository sr;

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new IndexController(sr);
    }

    @Test
    public void retornaView() throws Exception {
        assertThat(controller.index().getViewName(), is("index"));
    }

    @Test
    public void retornaMetricasDeAcessoNoModel() throws Exception {
        Page page = mock(Page.class);

        doReturn(page)
                .when(sr)
                .findAll(new PageRequest(0, 9, new Sort(Sort.Direction.DESC, "acessos")));

        assertModelAttributeValue(controller.index(), "acessos", page);
    }

}