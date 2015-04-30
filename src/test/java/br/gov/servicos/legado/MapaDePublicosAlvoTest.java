package br.gov.servicos.legado;

import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.github.slugify.Slugify;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapaDePublicosAlvoTest {

    MapaDePublicosAlvo mapa;

    @Before
    public void setUp() throws Exception {
        mapa = new MapaDePublicosAlvo(new Slugify());
    }

    @Test
    public void mapeiaServicosAosCidadaos() throws Exception {
        assertThat(mapa.mapear("Serviços aos cidadãos"), is(new PublicoAlvo()
                .withId("servicos-aos-cidadaos")
                .withTitulo("Serviços aos Cidadãos")));
    }

    @Test
    public void mapeiaServicosAEmpresas() throws Exception {
        assertThat(mapa.mapear("Serviços as empresas"), is(new PublicoAlvo()
                .withId("servicos-as-empresas")
                .withTitulo("Serviços às Empresas")));
    }

    @Test
    public void mapeiaDesconhecidosParaNull() throws Exception {
        assertThat(mapa.mapear("hein?"), is(nullValue()));
    }
}