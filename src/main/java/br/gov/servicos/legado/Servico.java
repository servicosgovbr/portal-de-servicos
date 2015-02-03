package br.gov.servicos.legado;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Value
@Document(indexName = "guia-legado", type = "servico")
public class Servico {
    @Id
    String id;

    @Version
    Long version;

    String titulo;
    String descricao;

    private Servico() {
        this(null, null, null, null);
    }

    public Servico(String id, Long version, String titulo, String descricao) {
        this.id = id;
        this.version = version;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Servico(Dados.Servicos.Servico legado) {
        this(null, null, legado.getTitulo(), legado.getDescricao());
    }
}
