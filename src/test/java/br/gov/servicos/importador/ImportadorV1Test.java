package br.gov.servicos.importador;

import br.gov.servicos.config.Vcge20Config;
import br.gov.servicos.servico.Servico;
import br.gov.servicos.servico.ServicoRepository;
import com.github.slugify.Slugify;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;

import static br.gov.servicos.fixtures.TestData.SERVICO;
import static java.util.Collections.singleton;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.when;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class ImportadorV1Test {

    @Mock
    ServicoRepository servicos;

    File diretorioCartasDeServico;
    ImportadorV1 importadorV1;

    @Before
    public void setUp() throws IOException {
        diretorioCartasDeServico = new ClassPathResource("repositorio-cartas-servico").getFile();

        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
        importadorV1 = new ImportadorV1(resourceResolver, servicos, new Vcge20Config().getMapaVcge20(), new Slugify());

        when(servicos.save(anySetOf(Servico.class))).thenAnswer(returnsFirstArg());
    }

    @Test
    public void deveImportarUmServicoXML() throws IOException {
        assertThat(importadorV1.importar(diretorioCartasDeServico),
                is(singleton(SERVICO)));
    }
}