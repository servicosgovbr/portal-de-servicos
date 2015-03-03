package br.gov.servicos.legado;

import br.gov.servicos.busca.Busca;
import br.gov.servicos.servico.*;
import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.LinkedList;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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
                .findAll();

        assertThat(importador.importar(), is(servicosImportados));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void deveMapearServicoLegadoParaServico() throws Exception {
        importador.importar();

        ArgumentCaptor<Iterable> captor = ArgumentCaptor.forClass(Iterable.class);
        verify(servicos).save(captor.capture());

        Servico servicoImportado = new Servico(
                null,
                "Elaboração de Demonstrativos e Acordo de Parcelamento.",
                "Elaboração de Demonstrativos Previdenciário de Política de Investimentos, das Aplicações e " +
                        "Investimentos dos Recursos e o de Resultados da Avaliação Atuarial - DRAA e o Comprovante de " +
                        "Repasse, são preenchidos pelo ente federativo, via internet, e enviados para o CADPREV e " +
                        "constituem critério para a emissão de CRP.",
                "http://www.previdencia.gov.br/conteudoDinamico.php?id=1072",
                null, null,
                new Orgao(null, "Ministério da Previdência Social - MPS", "Ligue 135."),
                new Orgao(null, "Ministerio da Previdencia Social - MPS", null),
                asList(new AreaDeInteresse(null, "Previdência Social")),
                asList(new LinhaDaVida(null, "Abrir um negócio")),
                asList("Outros"),
                0L, 0L
        );
        assertThat((Iterable<Servico>) captor.getValue(), hasItem(servicoImportado));
    }
}