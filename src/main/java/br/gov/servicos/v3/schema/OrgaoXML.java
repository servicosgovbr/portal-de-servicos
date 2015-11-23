package br.gov.servicos.v3.schema;

import br.gov.servicos.orgao.OrgaoRepository;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.Locale;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;


@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Orgao")
@Document(indexName = IMPORTADOR, type = "pagina-orgao")
public class OrgaoXML {

    @XmlAttribute(name = "id", required = true)
    @Field(type = String, index = not_analyzed, store = true)
    @Id
    String id;

    @XmlElement
    @Field(type = String, index = not_analyzed, store = true)
    String url;

    @XmlElement
    @Field(type = String, index = analyzed, store = true)
    String nome;

    @XmlElement
    @Field(type = String, index = analyzed, store = true)
    String conteudo;

    @Field(store = true, type = String, index = not_analyzed)
    String tipoConteudo;

    @Field(store = true, type = String, index = not_analyzed)
    String html;

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class PaginaOrgaoFormatter implements org.springframework.format.Formatter<OrgaoXML> {

        OrgaoRepository orgaoRepository;
        Slugify slugify;

        @Autowired
        public PaginaOrgaoFormatter(OrgaoRepository orgaoRepository, Slugify slugify) {
            this.orgaoRepository = orgaoRepository;
            this.slugify = slugify;
        }

        @Override
        public OrgaoXML parse(String id, Locale locale) {
            return orgaoRepository.findOne(slugify.slugify(id));
        }

        @Override
        public String print(OrgaoXML conteudo, Locale locale) {
            return conteudo.getId();
        }
    }
}
