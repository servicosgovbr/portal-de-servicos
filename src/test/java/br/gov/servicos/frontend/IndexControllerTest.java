package br.gov.servicos.frontend;

import br.gov.servicos.servicos.Servico;
import br.gov.servicos.servicos.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

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
        given(sr.findAll(new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "acessos")))).willReturn(page);
        Page<Servico> actual = (Page<Servico>) controller.index().getModel().get("acessos");
        assertThat(actual, is(page));
    }

    @Test
    public void retornaMetricasDeAtivacaoNoModel() throws Exception {
        Page page = mock(Page.class);
        given(sr.findAll(new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "ativacoes")))).willReturn(page);
        Page<Servico> actual = (Page<Servico>) controller.index().getModel().get("ativacoes");
        assertThat(actual, is(page));
    }
}