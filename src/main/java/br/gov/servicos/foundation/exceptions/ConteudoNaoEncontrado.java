package br.gov.servicos.foundation.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Página não encontrada")
public class ConteudoNaoEncontrado extends RuntimeException {

    private static final long serialVersionUID = -7299858067001144911L;

    public ConteudoNaoEncontrado() {
    }

    public ConteudoNaoEncontrado(IOException cause) {
        super(cause);
    }
}
