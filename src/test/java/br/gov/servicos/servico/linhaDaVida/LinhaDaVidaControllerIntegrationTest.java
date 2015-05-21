package br.gov.servicos.servico.linhaDaVida;

import br.gov.servicos.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class LinhaDaVidaControllerIntegrationTest {

    @Autowired
    LinhaDaVidaRepository linhaDaVidaRepository;

    @Autowired
    LinhaDaVidaController linhaDaVidaController;

    @Test
    public void deveEncontrarConteudoParaTodasLinhasDaVidaNoRepositorio() throws Exception {
        linhaDaVidaRepository.findAll()
                .stream()
                .map(LinhaDaVida::getId)
                .map(linhaDaVidaController::linhaDaVida);
    }

}