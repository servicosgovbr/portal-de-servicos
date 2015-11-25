package br.gov.servicos.orgao;

import br.gov.servicos.Main;
import br.gov.servicos.setup.SetupTestesIntegracao;
import br.gov.servicos.v3.schema.OrgaoXML;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class OrgaoControllerIntegrationTest {

    @Autowired
    OrgaoRepositoryUtil orgaoRepository;

    @Autowired
    OrgaoController orgaoController;

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    @Before
    public void setup() throws IOException {
        setupTestesIntegracao.setupComDados();
    }

    @Test
    public void deveEncontrarConteudoParaTodosOrgaosNoRepositorio() throws Exception {
        orgaoRepository.findAll()
                .forEach(o -> orgaoController.orgao(new OrgaoXML().withId(o.getId())));
    }

}