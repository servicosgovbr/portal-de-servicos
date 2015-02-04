package br.gov.servicos.legado;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Value
@Document(indexName = "guia-legado", type = "servico")
class Servico {

    @Id
    private final String id = null;

    @Version
    private final Long version = null;

    private final String titulo;
    private final String descricao;

    Servico() {
        this(null, null);
    }

    public Servico(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    static Servico servicoLegadoToServico(Dados.Servicos.Servico legado) {
        return new Servico(legado.titulo, legado.descricao);
    }

}
