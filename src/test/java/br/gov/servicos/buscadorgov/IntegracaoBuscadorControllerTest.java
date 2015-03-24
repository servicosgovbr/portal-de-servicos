package br.gov.servicos.buscadorgov;

import br.gov.servicos.servico.ServicoRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class IntegracaoBuscadorControllerTest {

    @Mock
    ServicoRepository servicos;

    IntegracaoBuscadorController controller;

    @Before
    public void setUp() throws Exception {
        controller = new IntegracaoBuscadorController(servicos);
    }

    @Test
    public void retornaListaDeLinksParaServicos() throws Exception {
        assertViewName(controller.get(), "resultado-listar-servicos");
        verify(servicos).findAll();
    }

    @Test
    public void retornaDetalheDeServico() throws Exception {
        assertViewName(controller.get("svc"), "resultado-detalhar-servico");
        verify(servicos).findOne("svc");
    }

}
