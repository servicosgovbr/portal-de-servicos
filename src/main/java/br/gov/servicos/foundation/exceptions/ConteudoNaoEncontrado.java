package br.gov.servicos.foundation.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Pagina não encontrada")
public class ConteudoNaoEncontrado extends Exception {
    
    private static final long serialVersionUID = -7299858067001144911L;
    
}
