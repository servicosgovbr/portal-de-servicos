package br.gov.servicos.buscadorgov;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;

@Value
@XStreamAlias("servico")
class Servico {
    String link;
    Object dataAtualizacao = null;

    Servico(String id, HttpServletRequest request) {
        link = String.format("http://%s:%s/xml/servico/%s",
                request.getServerName(),
                request.getServerPort(),
                id);
    }
}
