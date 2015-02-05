package br.gov.servicos.servicos;

import br.gov.servicos.legado.Dados;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Value
@Document(indexName = "guia-de-servicos", type = "servico")
public class Servico {

    @Id
    String id;

    String titulo;
    String descricao;
    String url;

    private Servico() {
        this(null, null, null, null);
    }

    public Servico(String id, String titulo, String descricao, String url) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public static Servico servicoLegadoToServico(Dados.Servicos.Servico legado) {

        return new Servico(
                UUID.randomUUID().toString(),
                legado.getTitulo(),
                legado.getDescricao(),
                legado.getUrl()
        );
    }

}
