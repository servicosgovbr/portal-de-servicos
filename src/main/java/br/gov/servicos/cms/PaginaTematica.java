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

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.util.Locale;

import static br.gov.servicos.TipoPagina.SERVICO;
import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static javax.xml.bind.annotation.XmlAccessType.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@Wither
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = IMPORTADOR, type = "pagina-tematica")
@XmlAccessorType(NONE)
@XmlType(name = "PaginaTematica")
public class PaginaTematica {

    @Id
    @XmlTransient
    @Field(store = true, type = String, index = not_analyzed)
    String id;

    @XmlElement(required = true)
    @Field(store = true, type = String)
    String nome;

    @XmlElement(required = true)
    @Field(store = true, type = String)
    String conteudo;

    @Field(type = String, store = true, index = not_analyzed)
    String html;

    @Field(store = true, type = String, index = not_analyzed)
    String tipoConteudo;

    public PaginaTematica() {
        this(null, null, null, null, null);
    }

    @SneakyThrows
    public static PaginaTematica fromServico(ServicoXML servico) {
        return new PaginaTematica()
                .withId(new Slugify().slugify(servico.getNome()))
                .withTipoConteudo(SERVICO.getNome())
                .withNome(servico.getNome())
                .withConteudo(servico.getDescricao());
    }

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class PaginaTematicaFormatter implements org.springframework.format.Formatter<PaginaTematica> {

        PaginaTematicaRepository paginas;
        Slugify slugify;

        @Autowired
        public PaginaTematicaFormatter(PaginaTematicaRepository paginas, Slugify slugify) {
            this.paginas = paginas;
            this.slugify = slugify;
        }

        @Override
        public PaginaTematica parse(String id, Locale locale) throws ParseException {
            return paginas.findOne(slugify.slugify(id));
        }

        @Override
        public String print(PaginaTematica conteudo, Locale locale) {
            return conteudo.getId();
        }
    }

}
