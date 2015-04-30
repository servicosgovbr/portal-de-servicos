package br.gov.servicos.legado;

import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.github.slugify.Slugify;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class MapaDePublicosAlvo {

    private static final PublicoAlvo CIDADAOS = new PublicoAlvo().withId("servicos-aos-cidadaos").withTitulo("Serviços aos Cidadãos");
    private static final PublicoAlvo EMPRESAS = new PublicoAlvo().withId("servicos-as-empresas").withTitulo("Serviços às Empresas");

    Slugify slugify;

    @Autowired
    public MapaDePublicosAlvo(Slugify slugify) {
        this.slugify = slugify;
    }

    public PublicoAlvo mapear(String original) {
        return slugify.slugify(original).equals("servicos-aos-cidadaos") ? CIDADAOS :
                slugify.slugify(original).equals("servicos-as-empresas") ? EMPRESAS : null;
    }
}
