package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.cms.Markdown;
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
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ImportadorConteudoTest {

    @Mock
    OrgaoRepository orgaoRepository;

    @Mock
    LinhaDaVidaRepository linhaDaVidaRepository;

    @Mock
    ConteudoRepository conteudoRepository;

    @Mock(answer = RETURNS_SMART_NULLS)
    Markdown markdown;

    ImportadorConteudo importadorConteudo;

    @Before
    public void setUp() throws Exception {
        importadorConteudo = new ImportadorConteudo(markdown, linhaDaVidaRepository, orgaoRepository, conteudoRepository);

        doReturn(TestData.CONTEUDO_HTML).when(markdown).toHtml(anyObject());
    }

    @Test
    public void deveIndexarConteudoDePaginas() {
        doReturn(new ConteudoHtml()
                .withId("acessibilidade")
                .withTitulo("Acessibilidade")
                .withHtml("(não usado)"))
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/acessibilidade.md"));

        doReturn(new ConteudoHtml()
                .withId("perguntas-frequentes")
                .withTitulo("Perguntas Frequentes")
                .withHtml("(não usado)"))
                .when(markdown)
                .toHtml(new ClassPathResource("conteudo/perguntas-frequentes.md"));

        importadorConteudo.importar();

        ArgumentCaptor<List<Conteudo>> captor = ArgumentCaptor.forClass((Class<List<Conteudo>>) null);
        verify(conteudoRepository).save(captor.capture());

        List<Conteudo> conteudos = captor.getValue();
        Conteudo arquivoNacional = conteudos.get(0);

        Assert.assertThat(arquivoNacional.getTitulo(), equalTo("Acessibilidade"));
        Assert.assertThat(arquivoNacional.getConteudo(), containsString("Acessibilidade"));

        Conteudo bancoCentral = conteudos.get(1);
        Assert.assertThat(bancoCentral.getTitulo(), equalTo("Perguntas Frequentes"));
        Assert.assertThat(bancoCentral.getConteudo(), containsString("Perguntas Frequentes"));
    }

    @Test
    public void deveIndexarConteudoDeOrgaos() {
        doReturn(TestData.ORGAOS)
                .when(orgaoRepository)
                .findAll();

        importadorConteudo.importar();

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(conteudoRepository).save(captor.capture());

        List<Conteudo> conteudos = captor.getValue();
        Conteudo arquivoNacional = conteudos.get(0);

        Assert.assertThat(arquivoNacional.getTitulo(), equalTo("Arquivo Nacional"));
        Assert.assertThat(arquivoNacional.getConteudo(), containsString("Conteúdo do Arquivo Nacional"));

        Conteudo bancoCentral = conteudos.get(1);
        Assert.assertThat(bancoCentral.getTitulo(), equalTo("Banco Central do Brasil"));
        Assert.assertThat(bancoCentral.getConteudo(), containsString("Conteúdo do Banco Central do Brasil"));
    }

    @Test
    public void deveIndexarConteudoDeLinhasDaVida() throws Exception {
        doReturn(TestData.LINHAS_DA_VIDA)
                .when(linhaDaVidaRepository)
                .findAll();

        importadorConteudo.importar();

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(conteudoRepository).save(captor.capture());

        List<Conteudo> conteudos = captor.getValue();
        Conteudo arquivoNacional = conteudos.get(0);

        Assert.assertThat(arquivoNacional.getTitulo(), equalTo("Ter um Imóvel"));
        Assert.assertThat(arquivoNacional.getConteudo(), containsString("Ter um Imóvel"));

        Conteudo bancoCentral = conteudos.get(1);
        Assert.assertThat(bancoCentral.getTitulo(), equalTo("Obter Crédito e Apoio Financeiro"));
        Assert.assertThat(bancoCentral.getConteudo(), containsString("Obter Crédito e Apoio Financeiro"));
    }

}