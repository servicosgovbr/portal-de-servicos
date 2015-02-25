package br.gov.servicos.frontend;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Documento não encontrado")
public class NotFoundException extends Exception {
    
    private static final long serialVersionUID = -7299858067001144911L;
    
}
