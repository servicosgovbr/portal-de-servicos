package br.gov.servicos.frontend;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Documento não encontrado")
public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }
}
