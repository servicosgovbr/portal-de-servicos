package br.gov.servicos.legado;

import br.gov.servicos.config.ConteudoConfig;
import br.gov.servicos.servico.AreaDeInteresse;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.LinkedList;

import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.elasticsearch.common.collect.Iterables.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class ImportadorLegadoTest {

    @Mock
    ResourcePatternResolver resolver;

    @Mock
    ServicoRepository servicos;

    @Mock
    ConteudoConfig config;

    @Mock
    BeanFactory beanFactory;

    @Mock
    MapaDeLinhasDaVida mapaDeLinhasDaVida;

    @Mock
    MapaDePublicosAlvo mapaDePublicosAlvo;

    ImportadorLegado importadorLegado;
    Slugify slugify;

    @Before
    public void setUp() throws Exception {
        slugify = new Slugify();

        doReturn(slugify)
                .when(beanFactory)
                .getBean("slugify");

        doAnswer(returnsFirstArg())
                .when(servicos)
                .save(anyCollectionOf(Servico.class));

        doReturn(singletonList(new LinhaDaVida().withId("veio-do-csv").withTitulo("Veio do CSV")))
                .when(mapaDeLinhasDaVida)
                .linhasDaVida(anyString());

        doReturn(new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos"))
                .when(mapaDePublicosAlvo)
                .mapear("Serviços aos cidadãos");

        doReturn(new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às Empresas"))
                .when(mapaDePublicosAlvo)
                .mapear("Serviços às empresas");

        doReturn(new Orgao().withId("ministerio-da-previdencia-social-mps").withNome("Ministério da Previdência Social (MPS)"))
                .when(config)
                .orgao(anyString());

        doReturn(new Resource[]{new ClassPathResource("legado/servico-legado-teste.xml")})
                .when(resolver).getResources("classpath:legado/*.xml");

        importadorLegado = new ImportadorLegado(resolver, servicos, new ServicoLegadoParaServico(slugify, beanFactory, config, mapaDeLinhasDaVida, mapaDePublicosAlvo));
    }

    @Test
    public void deveImportarListaDeServicosLegados() throws Exception {
        importadorLegado.importar();
        verify(servicos).save(anyCollectionOf(Servico.class));
    }

    @Test
    public void deveRetornarTodosOsServicosImportados() throws Exception {
        Iterable<Servico> servicosImportados = new LinkedList<>();

        doReturn(servicosImportados)
                .when(servicos)
                .save(anyCollectionOf(Servico.class));

        assertThat(importadorLegado.importar(), is(servicosImportados));
    }

    @Test
    public void deveCriarUmIdParaOServicoBaseaDoEmSeuTitulo() throws Exception {
        assertThat(importaServico().getId(),
                equalTo("elaboracao-de-demonstrativos-e-acordo-de-parcelamento"));
    }

    @Test
    public void deveImportarOTituloBaseadoNoServicoLegado() throws Exception {
        assertThat(importaServico().getTitulo(),
                equalTo("Elaboração de Demonstrativos e Acordo de Parcelamento."));
    }

    @Test
    public void deveImportarADescricaoDoServicoLegado() throws Exception {
        assertThat(importaServico().getDescricao(),
                equalTo("Descrição do serviço legado"));
    }

    @Test
    public void deveImportarUrlDoPrestadorDoServicoLegado() throws Exception {
        assertThat(importaServico().getUrl(),
                equalTo("http://www.previdencia.gov.br/conteudoDinamico.php?id=1072"));
    }

    @Test
    public void deveImportarUrlDeAgendamentoDoServico() throws Exception {
        assertThat(importaServico().getUrlAgendamento(),
                equalTo("http://www2.dataprev.gov.br/prevagenda/OpcaoInicialTela.view"));
    }

    @Test
    public void deveImportarTaxaDeServicoDoServicoLegado() throws Exception {
        assertThat(importaServico().getTaxa(), equalTo("R$ 156,07"));
    }

    @Test
    public void deveImportarOrgaoPrestadorDoServicoLegado() throws Exception {
        assertThat(importaServico().getPrestador(),
                equalTo(new Orgao()
                        .withId("ministerio-da-previdencia-social-mps")
                        .withNome("Ministério da Previdência Social (MPS)")
                        .withTelefone("Ligue 135.")));
    }

    @Test
    public void deveImportarOrgaoResponsavelDoServicoLegado() throws Exception {
        assertThat(importaServico().getResponsavel(),
                equalTo(new Orgao()
                        .withId("ministerio-da-previdencia-social-mps")
                        .withNome("Ministério da Previdência Social (MPS)")
                        .withTelefone(null)));
    }

    @Test
    public void deveImportarAreasDeInteresseDoServicoLegado() throws Exception {
        assertThat(importaServico().getAreasDeInteresse(),
                equalTo(singletonList(new AreaDeInteresse()
                        .withId("previdencia-social")
                        .withTitulo("Previdência Social"))));
    }

    @Test
    public void deveObterLinhasDaVidaParaServicoDoCsv() throws Exception {
        assertThat(importaServico().getLinhasDaVida(),
                equalTo(singletonList(new LinhaDaVida()
                        .withId("veio-do-csv")
                        .withTitulo("Veio do CSV"))));
        verify(mapaDeLinhasDaVida).linhasDaVida(anyString());
    }

    @Test
    public void deveImportarEventosDaLinhaDaVidaDoServicoLegado() throws Exception {
        assertThat(importaServico().getEventosDasLinhasDaVida(),
                equalTo(singletonList("Outros")));
    }

    @Test
    public void deveImportarPublicoAlvo() throws Exception {
        assertThat(importaServico().getPublicosAlvo(),
                equalTo(singletonList(new PublicoAlvo()
                        .withId("servicos-as-empresas")
                        .withTitulo("Serviços às Empresas"))));
    }

    private Servico importaServico() throws Exception {
        return get(importadorLegado.importar(), 0);
    }

}
