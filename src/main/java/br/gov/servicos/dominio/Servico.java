package br.gov.servicos.dominio;

import br.gov.servicos.legado.ServicoType;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.util.List;
import java.util.UUID;

import static org.elasticsearch.common.Strings.isEmpty;

@Value
@Document(indexName = "guia-de-servicos", type = "servico")
public class Servico {

    @Id
    String id;

    String titulo;
    String descricao;

    @Field(index = FieldIndex.not_analyzed)
    String url;

    @Field(index = FieldIndex.not_analyzed)
    String taxa;

    Orgao prestador;
    Orgao responsavel;

    @Field(index = FieldIndex.not_analyzed)
    Long acessos;

    @Field(index = FieldIndex.not_analyzed)
    Long ativacoes;

    @Field(index = FieldIndex.not_analyzed)
    List<String> areasDeInteresse;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    public Servico(String id, String titulo, String descricao, String url, String taxa, Orgao prestador, Orgao responsavel, List<String> areasDeInteresse, Long acessos, Long ativacoes) {
        this.areasDeInteresse = areasDeInteresse;
        this.id = isEmpty(id) ? null : id;
        this.titulo = isEmpty(titulo) ? null : titulo;
        this.descricao = isEmpty(descricao) ? null : descricao;
        this.url = isEmpty(url) ? null : url;
        this.taxa = isEmpty(taxa) ? null : taxa;
        this.acessos = acessos;
        this.ativacoes = ativacoes;
        this.prestador = prestador;
        this.responsavel = responsavel;
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
                0L,
                0L
        );
    }

    public Servico withNovoAcesso() {
        return new Servico(id, titulo, descricao, url, taxa, prestador, responsavel, areasDeInteresse, acessos == null ? 1 : acessos + 1, ativacoes);
    }

    public Servico withNovaAtivacao() {
        return new Servico(id, titulo, descricao, url, taxa, prestador, responsavel, areasDeInteresse, acessos, ativacoes == null ? 1 : ativacoes + 1);
    }

}
