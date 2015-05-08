package br.gov.servicos.indexador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.fixtures.TestData;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVidaRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class IndexadorConteudoTest {

    @Mock
    OrgaoRepository orgaoRepository;

    @Mock
    LinhaDaVidaRepository linhaDaVidaRepository;

    @Mock
    ConteudoRepository conteudoRepository;

    IndexadorConteudo indexadorConteudo;

    @Before
    public void setUp() throws Exception {
        indexadorConteudo = new IndexadorConteudo(linhaDaVidaRepository, orgaoRepository, conteudoRepository);
        doReturn(TestData.ORGAOS)
                .when(orgaoRepository)
                .findAll();
    }

    @Test
    public void deveIndexarInformacoesDeOrgaos() {
        indexadorConteudo.indexar();

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(conteudoRepository).save(captor.capture());

        List<Conteudo> conteudos = captor.getValue();
        Conteudo arquivoNacional = conteudos.get(0);

        Assert.assertThat(arquivoNacional.getTitulo(), equalTo("Arquivo Nacional"));
        Assert.assertThat(arquivoNacional.getConteudo(), containsString("Órgão central do Sistema de Gestão de Documentos de Arquivos - SIGA da Administração Pública Federal, tem por finalidade implementar e acompanhar a Política Nacional de Arquivos, por meio da gestão, do recolhimento, do tratamento técnico, da preservação e da divulgação do patrimônio documental do País, garantindo pleno acesso à informação, visando apoiar as decisões governamentais de caráter político-administrativo, o cidadão na defesa de seus direitos e de incentivar a produção de conhecimento científico e cultural"));

        Conteudo bancoCentral = conteudos.get(1);
        Assert.assertThat(bancoCentral.getTitulo(), equalTo("Banco Central do Brasil"));
        Assert.assertThat(bancoCentral.getConteudo(), containsString("O Banco Central do Brasil foi criado pela [Lei 4.595], de 31 de dezembro de 1964. É o principal executor das orientações do Conselho Monetário Nacional e responsável por garantir o poder de compra da moeda nacional, tendo por objetivos:"));
    }

}