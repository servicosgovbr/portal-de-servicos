package br.gov.servicos.cms;

import br.gov.servicos.servico.Servico;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static br.gov.servicos.config.GuiaDeServicosIndex.GDS_IMPORTADOR;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = GDS_IMPORTADOR, type = "conteudo")
public class Conteudo {

    @Id
    String id;

    @Field(store = true, type = String)
    String titulo;

    @Field(store = true, type = String)
    String conteudo;

    @Field(store = true, type = String)
    String tipoConteudo;

    public Conteudo() {
        this(null, null, null, null);
    }

    public static Conteudo fromServico(Servico servico) {
        return new Conteudo()
                .withId(servico.getId())
                .withTipoConteudo("servico")
                .withTitulo(servico.getTitulo())
                .withConteudo(servico.getDescricao());
    }
}
