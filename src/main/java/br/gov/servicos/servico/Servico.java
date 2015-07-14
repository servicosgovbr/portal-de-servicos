package br.gov.servicos.servico;

import br.gov.servicos.servico.areaDeInteresse.AreaDeInteresse;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Object;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(indexName = IMPORTADOR, type = "servico")
public class Servico {

    @Id
    String id;

    @Field(store = true, type = String)
    String titulo;

    @Field(store = true, type = String)
    String nomesPopulares;

    @Field(store = true, type = String)
    String descricao;

    @Field(store = true, type = String)
    String palavrasChave;

    @Field(index = not_analyzed, type = String)
    List<String> solicitantes;

    @Field(type = Object)
    TempoEstimado tempoEstimado;

    Boolean gratuito;

    @Field(store = true, type = String)
    String situacao;

    @Field(index = not_analyzed, type = Object)
    List<Etapa> etapas;

    @Field(type = Object)
    Orgao orgao;

    @Field(index = not_analyzed, type = Object)
    List<AreaDeInteresse> areasDeInteresse;

    @Field(index = not_analyzed, type = Object)
    List<LinhaDaVida> eventosDaLinhaDaVida;

    @Field(index = not_analyzed, type = Object)
    List<PublicoAlvo> segmentosDaSociedade;

    @Field(index = not_analyzed, type = String)
    List<String> legislacoes;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @JsonIgnore
    public List<CanalDePrestacao> getCanaisPreferenciais() {
        return ofNullable(etapas).orElse(emptyList()).stream()
                .flatMap(es -> ofNullable(es.getCanaisDePrestacao()).orElse(emptyList()).stream())
                .filter(c -> c != null)
                .filter(c -> c.getPreferencial() != null)
                .filter(CanalDePrestacao::getPreferencial)
                .collect(toList());
    }
}
