package br.gov.servicos.servico;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.Object;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = "guia-de-servicos", type = "servico")
@XStreamAlias("servico")
@Wither
public class Servico {

    @Id
    String id;

    @Field(store = true, type = String)
    String titulo;

    @Field(store = true, type = String)
    String descricao;

    @Field(index = not_analyzed, type = String)
    String url;

    @Field(index = not_analyzed, type = String)
    String urlAgendamento;

    @Field(index = not_analyzed, type = String)
    String taxa;

    @Field(type = Object)
    Orgao prestador;

    @Field(type = Object)
    Orgao responsavel;

    @Field(index = not_analyzed, type = String)
    List<String> publicosAlvo;

    @Field(index = not_analyzed, type = Object)
    List<AreaDeInteresse> areasDeInteresse;

    @Field(index = not_analyzed, type = Object)
    List<LinhaDaVida> linhasDaVida;

    @Field(index = not_analyzed, type = String)
    List<String> eventosDasLinhasDaVida;

    @Field(index = not_analyzed, type = Long)
    Long acessos;

    @Field(index = not_analyzed, type = Long)
    Long ativacoes;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Servico withNovoAcesso() {
        return withAcessos(acessos == null ? 1 : acessos + 1);
    }

    public Servico withNovaAtivacao() {
        return withAtivacoes(ativacoes == null ? 1 : ativacoes + 1);
    }

}
