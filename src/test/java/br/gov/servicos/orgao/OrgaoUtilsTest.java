package br.gov.servicos.orgao;

import org.junit.Test;

import static br.gov.servicos.config.SlugifyConfig.slugify;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OrgaoUtilsTest {

    @Test
    public void deveObterIdDeUrl() throws Exception {
        testaIdUrl("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/2981", 2981);
        testaIdUrl("http://estruturaorganizacional.dados.gov.br/id/unidade-organizacional/2981", 2981);
        testaIdUrl(slugify("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/2981"), 2981);
        testaIdUrl(slugify("http://estruturaorganizacional.dados.gov.br/id/unidade-organizacional/2981"), 2981);
    }

    @Test(expected = Exception.class)
    public void deveFalhar1() {
        new OrgaoUtils().obterId("asdfasdf l. adsfasdf. adsf");
    }

    @Test(expected = Exception.class)
    public void deveFalhar2() {
        new OrgaoUtils().obterId("http://estruturaorganizacional.dados.gov.br/outro/unidade-organizacional/2981");
    }

    @Test(expected = Exception.class)
    public void deveFalhar3() {
        new OrgaoUtils().obterId("http://estruturaorganizacional.dados.gov.br/doc/unidade-organizacional/");
    }

    private void testaIdUrl(String urlComId, long test) {
        assertThat(new OrgaoUtils().obterId(urlComId), is(test));
    }

}