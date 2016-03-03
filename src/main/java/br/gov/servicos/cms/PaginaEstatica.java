package br.gov.servicos.cms;

import br.gov.servicos.v3.schema.ServicoXML;
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

import static br.gov.servicos.cms.TipoPagina.SERVICO;
import static br.gov.servicos.config.PortalDeServicosIndex.PORTAL_DE_SERVICOS_INDEX;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = PORTAL_DE_SERVICOS_INDEX, type = "pagina-estatica")
public class PaginaEstatica {

    @Id
    @Field(store = true, type = String, index = not_analyzed)
    String id;

    @Field(store = true, type = String)
    String nome;

    @Field(store = true, type = String)
    String conteudo;

    @Field(type = String, store = true, index = not_analyzed)
    String html;

    @Field(store = true, type = String, index = not_analyzed)
    String tipoConteudo;

    public PaginaEstatica() {
        this(null, null, null, null, null);
    }

    @SneakyThrows
    public static PaginaEstatica fromServico(ServicoXML servico) {
        return new PaginaEstatica()
                .withId(new Slugify().slugify(servico.getNome()))
                .withTipoConteudo(SERVICO.getNome())
                .withNome(servico.getNome())
                .withConteudo(servico.getDescricao());
    }

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class PaginaEstaticaFormatter implements org.springframework.format.Formatter<PaginaEstatica> {

        PaginaEstaticaRepository paginas;
        Slugify slugify;

        @Autowired
        public PaginaEstaticaFormatter(PaginaEstaticaRepository paginas, Slugify slugify) {
            this.paginas = paginas;
            this.slugify = slugify;
        }

        @Override
        public PaginaEstatica parse(String id, Locale locale) throws ParseException {
            return paginas.findOne(slugify.slugify(id));
        }

        @Override
        public String print(PaginaEstatica conteudo, Locale locale) {
            return conteudo.getId();
        }
    }

}
