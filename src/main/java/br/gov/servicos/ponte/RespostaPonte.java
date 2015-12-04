package br.gov.servicos.ponte;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Value
@Wither
@AllArgsConstructor
public class RespostaPonte {
    String titulo;
    String appId;
    List<Map<String, String>> componentes;

    public RespostaPonte() {
        titulo = "";
        appId = "";
        componentes = new ArrayList<>();
    }
}