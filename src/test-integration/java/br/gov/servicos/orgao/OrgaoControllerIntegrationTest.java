package br.gov.servicos.orgao;

import br.gov.servicos.Main;
import br.gov.servicos.servico.Orgao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class OrgaoControllerIntegrationTest {

    @Autowired
    OrgaoRepository orgaoRepository;

    @Autowired
    OrgaoController orgaoController;

    @Test
    public void deveEncontrarConteudoParaTodosOrgaosNoRepositorio() throws Exception {
        orgaoRepository.findAll()
                .stream()
                .map(Orgao::getId)
                .map(orgaoController::orgao);
    }

}