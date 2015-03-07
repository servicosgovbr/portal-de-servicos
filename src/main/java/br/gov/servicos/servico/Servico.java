package br.gov.servicos.servico;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

import static java.util.Arrays.asList;
import static org.elasticsearch.common.Strings.isEmpty;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.Object;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Document(indexName = "guia-de-servicos", type = "servico")
@XStreamAlias("servico")
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

    @Field(index = not_analyzed, type = Long)
    Long acessos;

    @Field(index = not_analyzed, type = Long)
    Long ativacoes;

    @Field(index = not_analyzed, type = Object)
    List<AreaDeInteresse> areasDeInteresse;

    @Field(index = not_analyzed, type = Object)
    List<LinhaDaVida> linhasDaVida;

    @Field(index = not_analyzed, type = String)
    List<String> eventosDasLinhasDaVida;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Servico(
            String id,
            String titulo,
            String descricao,
            String url,
            String urlAgendamento,
            String taxa,
            Orgao prestador,
            Orgao responsavel,
            List<AreaDeInteresse> areasDeInteresse,
            List<LinhaDaVida> linhasDaVida,
            List<String> eventosDasLinhasDaVida,
            Long acessos,
            Long ativacoes
    ) {
        this.id = isEmpty(id) ? null : id;
        this.titulo = isEmpty(titulo) ? null : titulo;
        this.descricao = isEmpty(descricao) ? null : descricao;
        this.url = isEmpty(url) ? null : url;
        this.urlAgendamento = urlAgendamento;
        this.taxa = isEmpty(taxa) ? null : taxa;
        this.prestador = prestador;
        this.responsavel = responsavel;
        this.areasDeInteresse = areasDeInteresse;
        this.linhasDaVida = linhasDaVida;
        this.eventosDasLinhasDaVida = eventosDasLinhasDaVida;
        this.acessos = acessos;
        this.ativacoes = ativacoes;
    }

    public Servico withNovoAcesso() {
        return new Servico(id, titulo, descricao, url, urlAgendamento, taxa, prestador, responsavel, areasDeInteresse, linhasDaVida, eventosDasLinhasDaVida, acessos == null ? 1 : acessos + 1, ativacoes);
    }

    public Servico withNovaAtivacao() {
        return new Servico(id, titulo, descricao, url, urlAgendamento, taxa, prestador, responsavel, areasDeInteresse, linhasDaVida, eventosDasLinhasDaVida, acessos, ativacoes == null ? 1 : ativacoes + 1);
    }

    public Servico withId(String newId) {
        return new Servico(newId, titulo, descricao, url, urlAgendamento, taxa, prestador, responsavel, areasDeInteresse, linhasDaVida, eventosDasLinhasDaVida, acessos, ativacoes);
    }

    public Servico withLinhasDaVida(LinhaDaVida ... linhasDaVida) {
        return new Servico(id, titulo, descricao, url, urlAgendamento, taxa, prestador, responsavel, areasDeInteresse, asList(linhasDaVida), eventosDasLinhasDaVida, acessos, ativacoes);
    }
}
