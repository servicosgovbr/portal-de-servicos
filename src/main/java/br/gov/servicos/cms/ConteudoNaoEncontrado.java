package br.gov.servicos.cms;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Documento não encontrado")
class ConteudoNaoEncontrado extends Exception {
    
    private static final long serialVersionUID = -7299858067001144911L;
    
}
