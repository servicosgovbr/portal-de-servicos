package br.gov.servicos.cartaServicos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

enum MotivoFalha {

    REQUER_ATRIBUTOS {
        @Override
        void valida(Exception erro) {
            assertThat(erro.getMessage(), containsString("must appear on element"));
        }
    },

    ERRO_DE_VALIDACAO {
        @Override
        void valida(Exception erro) {
            assertThat(erro.getMessage(), containsString("is not facet-valid with respect to"));
        }
    };

    abstract void valida(Exception erro);

}
