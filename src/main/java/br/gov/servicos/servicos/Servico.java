package br.gov.servicos.servicos;

import br.gov.servicos.legado.Dados;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Value
@Document(indexName = "guia-legado", type = "servico")
public class Servico {

    @Id
    String id = null;

    @Version
    Long version = null;

    String titulo;
    String descricao;

    public Servico() {
        this(null, null);
    }

    public Servico(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public static Servico servicoLegadoToServico(Dados.Servicos.Servico legado) {
        return new Servico(legado.getTitulo(), legado.getDescricao());
    }

}
