package br.gov.servicos.frontend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;

import static java.nio.charset.Charset.defaultCharset;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DiffControllerTest {

    @Mock
    Resource resource;

    @Test
    public void deveRedirecionarParaOGithubComCommitCorreto() throws Exception {
        given(resource.getInputStream()).willReturn(new ByteArrayInputStream("git.commit.id=abcdef".getBytes(defaultCharset())));

        assertThat(new DiffController(resource).diff().getUrl(), is("https://github.com/servicosgovbr/guia-de-servicos/compare/abcdef...master"));
    }

    @Test
    public void deveRedirecionarParaOGithubMesmoSemCommit() throws Exception {
        given(resource.getInputStream()).willReturn(new ByteArrayInputStream(new byte[0]));

        assertThat(new DiffController(resource).diff().getUrl(), is("https://github.com/servicosgovbr/guia-de-servicos/compare/master...master"));
    }

    @Test
    public void deveRedirecionarParaOGithubMesmoArquivoDeProperties() throws Exception {
        given(resource.getInputStream()).willThrow(new RuntimeException("boom"));

        assertThat(new DiffController(resource).diff().getUrl(), is("https://github.com/servicosgovbr/guia-de-servicos/compare/master...master"));
    }
}