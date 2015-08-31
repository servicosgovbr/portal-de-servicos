package br.gov.servicos.cms;

import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = IMPORTADOR, type = "conteudo")
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

    @SneakyThrows
    public static Conteudo fromServico(br.gov.servicos.v3.schema.Servico servico) {
        return new Conteudo()
                .withId(new Slugify().slugify(servico.getNome() + " - " + servico.getSigla()))
                .withTipoConteudo("servico")
                .withTitulo(servico.getNome())
                .withConteudo(servico.getDescricao());
    }
}
