package br.gov.servicos.busca;

import br.gov.servicos.Main;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.config.GuiaDeServicosIndex;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.util.Optional.of;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class BuscadorConteudoIntegrationTest {

    @Autowired
    GuiaDeServicosIndex guiaDeServicosIndex;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    ConteudoRepository conteudoRepository;

    @Autowired
    BuscadorConteudo   buscadorConteudo;

    @Ignore
    @Test
    public void deveInserirUmServicoEUmConteudoERetornarDoisConteudos() throws Exception {
        guiaDeServicosIndex.recriar();

        servicoRepository.save(TestData.SERVICO);
        conteudoRepository.save(TestData.CONTEUDO);

        buscadorConteudo.busca(of("Descrição"), 0);
    }
}