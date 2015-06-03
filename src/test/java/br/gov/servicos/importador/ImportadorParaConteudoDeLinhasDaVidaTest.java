package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVidaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorParaConteudoDeLinhasDaVidaTest {

    @Mock
    LinhaDaVidaRepository repository;

    @Mock
    ConteudoParser parser;

    @Test
    public void deveConverterLinhasDaVidaEmConteudos() throws Exception {
        given(repository.findAll()).willReturn(singletonList(new LinhaDaVida().withId("amamentando").withTitulo("Amamentando")));
        given(parser.conteudo("/conteudo/linhas-da-vida/amamentando.md")).willReturn("Parágrafo um. Parágrafo dois.");

        Conteudo c = new ImportadorParaConteudoDeLinhasDaVida(repository, parser).importar().findFirst().get();

        assertThat(c.getId(), is("amamentando"));
        assertThat(c.getTipoConteudo(), is("linha-da-vida"));
        assertThat(c.getTitulo(), is("Amamentando"));
        assertThat(c.getConteudo(), is("Parágrafo um. Parágrafo dois."));
    }

}