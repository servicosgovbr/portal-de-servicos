package br.gov.servicos.servicos;

import br.gov.servicos.legado.Dados;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

import static org.elasticsearch.common.Strings.isEmpty;

@Value
@Document(indexName = "guia-de-servicos", type = "servico")
public class Servico {

    @Id
    String id;

    String titulo;
    String descricao;
    String url;

    Orgao prestador;
    Orgao responsavel;

    Long acessos;
    Long ativacoes;

    public Servico() {
        this(null, null, null, null, null, null, null, null);
    }

    public Servico(String id, String titulo, String descricao, String url, Orgao prestador, Orgao responsavel, Long acessos, Long ativacoes) {
        this.id = isEmpty(id) ? null : id;
        this.titulo = isEmpty(titulo) ? null : titulo;
        this.descricao = isEmpty(descricao) ? null : descricao;
        this.url = isEmpty(url) ? null : url;
        this.acessos = acessos;
        this.ativacoes = ativacoes;
        this.prestador = prestador;
        this.responsavel = responsavel;
    }

    public static Servico servicoLegadoToServico(Dados.Servicos.Servico legado) {
        return new Servico(
                UUID.randomUUID().toString(),
                legado.getTitulo(),
                legado.getDescricao(),
                legado.getUrl(),
                legado.getOrgaoPrestador2(),
                legado.getOrgaoResponsavel2(),
                0L,
                0L
        );
    }

    public Servico withNovoAcesso() {
        return new Servico(id, titulo, descricao, url, prestador, responsavel, acessos == null ? 1 : acessos + 1, ativacoes);
    }

    public Servico withNovaAtivacao() {
        return new Servico(id, titulo, descricao, url, prestador, responsavel, acessos, ativacoes == null ? 1 : ativacoes + 1);
    }

}
