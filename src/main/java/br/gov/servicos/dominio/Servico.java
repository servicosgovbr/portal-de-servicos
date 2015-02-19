package br.gov.servicos.dominio;

import br.gov.servicos.legado.ServicoType;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.UUID;

import static org.elasticsearch.common.Strings.isEmpty;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Document(indexName = "guia-de-servicos", type = "servico")
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
    String taxa;

    @Field(type = FieldType.Object)
    Orgao prestador;

    @Field(type = FieldType.Object)
    Orgao responsavel;

    @Field(index = not_analyzed, type = Long)
    Long acessos;

    @Field(index = not_analyzed, type = Long)
    Long ativacoes;

    @Field(index = not_analyzed, type = String)
    List<String> areasDeInteresse;

    @Field(index = not_analyzed, type = String)
    List<String> linhasDaVida;

    @Field(index = not_analyzed, type = String)
    List<String> eventosDasLinhasDaVida;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Servico(
            String id,
            String titulo,
            String descricao,
            String url,
            String taxa,
            Orgao prestador,
            Orgao responsavel,
            List<String> areasDeInteresse,
            List<String> linhasDaVida,
            List<String> eventosDasLinhasDaVida,
            Long acessos,
            Long ativacoes
    ) {
        this.eventosDasLinhasDaVida = eventosDasLinhasDaVida;
        this.id = isEmpty(id) ? null : id;
        this.titulo = isEmpty(titulo) ? null : titulo;
        this.descricao = isEmpty(descricao) ? null : descricao;
        this.url = isEmpty(url) ? null : url;
        this.taxa = isEmpty(taxa) ? null : taxa;
        this.prestador = prestador;
        this.responsavel = responsavel;
        this.areasDeInteresse = areasDeInteresse;
        this.linhasDaVida = linhasDaVida;
        this.acessos = acessos;
        this.ativacoes = ativacoes;
    }

    public static Servico servicoLegadoToServico(ServicoType legado) {
        return new Servico(
                UUID.randomUUID().toString(),
                legado.getTitulo(),
                legado.getDescricao(),
                legado.getUrl(),
                legado.getTaxa(),
                legado.getOrgaoPrestador2(),
                legado.getOrgaoResponsavel2(),
                legado.getAreasDeInteresse(),
                legado.getLinhasDaVida(),
                legado.getEventosDasLinhasDaVida(),
                0L,
                0L
        );
    }

    public Servico withNovoAcesso() {
        return new Servico(id, titulo, descricao, url, taxa, prestador, responsavel, areasDeInteresse, linhasDaVida, eventosDasLinhasDaVida, acessos == null ? 1 : acessos + 1, ativacoes);
    }

    public Servico withNovaAtivacao() {
        return new Servico(id, titulo, descricao, url, taxa, prestador, responsavel, areasDeInteresse, linhasDaVida, eventosDasLinhasDaVida, acessos, ativacoes == null ? 1 : ativacoes + 1);
    }

}
