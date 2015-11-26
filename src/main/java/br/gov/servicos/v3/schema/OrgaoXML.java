package br.gov.servicos.v3.schema;

import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.orgao.Siorg;
import br.gov.servicos.orgao.UrlsSiorg;
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
import java.util.Optional;

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
@Document(indexName = IMPORTADOR, type = "orgao")
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

    @XmlElement
    @Field(type = String, index = analyzed, store = true)
    String contato;

    @Field(store = true, type = String, index = not_analyzed)
    String tipoConteudo;

    @Field(store = true, type = String, index = not_analyzed)
    String html;

    @Field(store = true, type = String, index = not_analyzed)
    String contatoHtml;

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class PaginaOrgaoFormatter implements org.springframework.format.Formatter<OrgaoXML> {
        OrgaoRepository orgaoRepository;
        Siorg siorg;
        Slugify slugify;

        @Autowired
        public PaginaOrgaoFormatter(OrgaoRepository orgaoRepository, Siorg siorg, Slugify slugify) {
            this.orgaoRepository = orgaoRepository;
            this.siorg = siorg;
            this.slugify = slugify;
        }

        @Override
        public OrgaoXML parse(String id, Locale locale) {
            String url = UrlsSiorg.obterUrl(id);
            return Optional.ofNullable(orgaoRepository.findOne(slugify.slugify(id)))
                    .orElse(siorg.obterOrgao(url)
                            .orElse(null));
        }

        @Override
        public String print(OrgaoXML conteudo, Locale locale) {
            return conteudo.getId();
        }
    }
}
