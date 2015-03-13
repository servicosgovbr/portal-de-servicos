package br.gov.servicos.foundation.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST)
public class ValidacaoFormularioException extends RuntimeException {
    public ValidacaoFormularioException(String s) { super (s); }
}
