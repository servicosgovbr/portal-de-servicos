package br.gov.servicos.buscadorgov;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;

@Value
@XStreamAlias("resultadoDetalharServico")
class ResultadoDetalharServico {
    br.gov.servicos.servico.Servico servico;
}
