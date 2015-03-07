package br.gov.servicos.buscadorgov;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Value
@XStreamAlias("resultadoListarServicos")
class ResultadoListarServicos {
    List<Servico> listaServicos;

    ResultadoListarServicos(List<br.gov.servicos.servico.Servico> servicos, HttpServletRequest request) {
        listaServicos = new ArrayList<>(servicos.stream()
                .map(br.gov.servicos.servico.Servico::getId)
                .map(id -> new Servico(id, request))
                .collect(Collectors.toList()));
    }
}
