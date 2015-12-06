package br.gov.servicos.orgao;

import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SiorgTest {

    Siorg siorg;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        siorg = new Siorg(restTemplate, new Slugify(), new OrgaoUtils());
    }

    @Test
    public void retornaUnidadeDoOrgao() throws Exception {
        String urlOrgao = "http://estruturaorganizacional.dados.gov.br/id/unidade-organizacional/1934";

        given(restTemplate.getForEntity(urlOrgao, Siorg.Orgao.class))
                .willReturn(new ResponseEntity<>(new Siorg.Orgao().withServico(new Siorg.Servico().withCodigoErro(0)).withUnidade(new Siorg.Unidade().withNome("Secretaria do Secretariado Secretarial").withSigla("SSS")), HttpStatus.OK));

        assertThat(siorg.findUnidade(urlOrgao).getNome(), is("Secretaria do Secretariado Secretarial"));
        assertThat(siorg.findUnidade(urlOrgao).getSigla(), is("SSS"));
    }

}