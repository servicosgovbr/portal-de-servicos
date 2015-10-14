package br.gov.servicos.setup;

import br.gov.servicos.importador.Importador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetupTestesIntegracao {

    private SetupPortalElasticSearch indicesPortal;
    private Importador importador;

    @Autowired
    public SetupTestesIntegracao(SetupPortalElasticSearch indicesPortal, Importador importador) {
        this.indicesPortal = indicesPortal;
        this.importador = importador;
    }

    public void setupComDados() {
        indicesPortal.setup();
        importador.importar();
    }

    public void setupBaseLimpa() {
        indicesPortal.setup();
    }

}
