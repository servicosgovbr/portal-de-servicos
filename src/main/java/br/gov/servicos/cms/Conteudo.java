package br.gov.servicos.cms;

import br.gov.servicos.v3.schema.Servico;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = IMPORTADOR, type = "conteudo")
public class Conteudo {

    @Id
    String id;

    @Field(store = true, type = String)
    String nome;

    @Field(store = true, type = String)
    String conteudo;

    @Field(type = String, store = true, index = not_analyzed)
    String conteudoHtml;

    @Field(store = true, type = String, index = not_analyzed)
    String tipoConteudo;

    public Conteudo() {
        this(null, null, null, null, null);
    }

    @SneakyThrows
    public static Conteudo fromServico(Servico servico) {
        return new Conteudo()
                .withId(new Slugify().slugify(servico.getNome()))
                .withTipoConteudo("servico")
                .withNome(servico.getNome())
                .withConteudo(servico.getDescricao());
    }

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class ConteudoFormatter implements org.springframework.format.Formatter<Conteudo> {

        ConteudoRepository conteudos;
        Slugify slugify;

        @Autowired
        public ConteudoFormatter(ConteudoRepository conteudos, Slugify slugify) {
            this.conteudos = conteudos;
            this.slugify = slugify;
        }

        @Override
        public Conteudo parse(String id, Locale locale) throws ParseException {
            return conteudos.findOne(slugify.slugify(id));
        }

        @Override
        public String print(Conteudo conteudo, Locale locale) {
            return conteudo.getId();
        }
    }

}
