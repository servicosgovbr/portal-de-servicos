package br.gov.servicos.legado;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.servico.*;
import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.LinkedList;

import static java.util.Arrays.asList;
import static org.elasticsearch.common.collect.Iterables.get;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorTest {

    private static final String GUIA_DE_SERVICOS = "guia-de-servicos";

    @Mock
    private ElasticsearchTemplate elasticsearch;

    @Mock
    private ServicoRepository servicos;

    @Mock
    private Slugify slugify;

    @Mock
    private BeanFactory beanFactory;

    private Importador importador;

    @Before
    public void setUp() {
        doReturn(slugify)
                .when(beanFactory)
                .getBean("slugify");

        doAnswer(returnsFirstArg())
                .when(servicos)
                .save(anyList());

        importador = new Importador(elasticsearch, servicos, new ServicoTypeMapper(slugify, beanFactory));
    }

    @Test
    public void deveApagarIndiceExistente() throws Exception {
        doReturn(true)
                .when(elasticsearch)
                .indexExists(GUIA_DE_SERVICOS);

        importador.importar();
        verify(elasticsearch).deleteIndex(GUIA_DE_SERVICOS);
    }

    @Test
    public void deveCriarIndiceParaGuiaDeServicos() throws Exception {
        importador.importar();
        verify(elasticsearch).createIndex(eq(GUIA_DE_SERVICOS), anyString());
        verify(elasticsearch).putMapping(Busca.class);
        verify(elasticsearch).putMapping(Servico.class);
    }

    @Test
    public void deveImportarListaDeServicosLegados() throws Exception {
        importador.importar();
        verify(servicos).save(anyList());
    }

    @Test
    public void deveRetornarTodosOsServicosImportados() throws Exception {
        Iterable<Servico> servicosImportados = new LinkedList<>();

        doReturn(servicosImportados)
                .when(servicos)
                .save(anyList());

        assertThat(importador.importar(), is(servicosImportados));
    }

    @Test
    public void deveCriarUmIdParaOServicoBaseaDoEmSeuTitulo() throws Exception {
        doReturn("elaboracao-de-demostrativos-e-acordo-de-parcelamento")
                .when(slugify)
                .slugify("Elaboração de Demonstrativos e Acordo de Parcelamento.");

        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getId(), equalTo("elaboracao-de-demostrativos-e-acordo-de-parcelamento"));
    }

    @Test
    public void deveImportarOTituloBaseadoNoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getTitulo(), equalTo("Elaboração de Demonstrativos e Acordo de Parcelamento."));
    }

    @Test
    public void deveImportarADescricaoDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getDescricao(), equalTo("Descrição do serviço legado"));
    }

    @Test
    public void deveImportarUrlDoPrestadorDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getUrl(), equalTo("http://www.previdencia.gov.br/conteudoDinamico.php?id=1072"));
    }

    @Test
    public void deveImportarUrlDeAgendamentoDoServico() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getUrlAgendamento(), equalTo("http://www2.dataprev.gov.br/prevagenda/OpcaoInicialTela.view"));
    }

    @Test
    public void deveImportarTaxaDeServicoDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getTaxa(), equalTo("R$ 156,07"));
    }

    @Test
    public void deveImportarOrgaoPrestadorDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getPrestador(), equalTo(new Orgao(null, "Ministério da Previdência Social - MPS", "Ligue 135.")));
    }

    @Test
    public void deveImportarOrgaoResponsavelDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getResponsavel(), equalTo(new Orgao(null, "Ministerio da Previdencia Social - MPS", null)));
    }
    
    @Test
    public void deveImportarAreasDeInteresseDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getAreasDeInteresse(), equalTo(asList(new AreaDeInteresse(null, "Previdência Social"))));
    }

    @Test
    public void deveImportarLinhasDaVidaDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getLinhasDaVida(), equalTo(asList(new LinhaDaVida(null, "Abrir um negócio"))));
    }
    
    @Test
    public void deveImportarEventosDaLinhaDaVidaDoServicoLegado() throws Exception {
        Servico servico = get(importador.importar(), 0);
        assertThat(servico.getEventosDasLinhasDaVida(), equalTo(asList("Outros")));
    }
    
}