package br.gov.servicos.v3.schema;

import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import br.gov.servicos.orgao.OrgaoRepositoryUtil;
import br.gov.servicos.servico.ServicoRepository;
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
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static br.gov.servicos.config.PortalDeServicosIndex.IMPORTADOR;
import static java.util.Optional.ofNullable;
import static javax.xml.bind.annotation.XmlAccessType.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Object;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Document(indexName = IMPORTADOR, type = "servico")
@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@XmlAccessorType(NONE)
@XmlType(name = "Servico")
public class ServicoXML {

    @Id
    @XmlTransient
    @Field(type = String, index = not_analyzed, store = true)
    String id;

    @XmlTransient
    @Field(store = true, type = String, index = not_analyzed)
    String xml;

    @XmlElement(required = true)
    @Field(type = String, store = true)
    String nome;

    @XmlElement
    @Field(type = String, store = true, index = not_analyzed)
    String sigla;

    @XmlElementWrapper(name = "nomes-populares")
    @XmlElement(name = "item")
    List<String> nomesPopulares;

    @XmlElement
    @Field(type = String, store = true)
    String descricao;

    @XmlElement
    String gratuito;

    @XmlElement
    @Field(type = String, store = true)
    String contato;

    @XmlElement(name = "id-aplicacao")
    @Field(type = String, store = true)
    String idAplicacao;

    @XmlElementWrapper(name = "solicitantes")
    @XmlElement(name = "solicitante")
    List<Solicitante> solicitantes;

    @XmlElement(name = "tempo-total-estimado")
    TempoTotalEstimado tempoTotalEstimado;

    @XmlElementWrapper(name = "etapas")
    @XmlElement(name = "etapa")
    List<Etapa> etapas;

    @XmlElement
    @Field(type = Object)
    OrgaoXML orgao;

    @XmlElementWrapper(name = "segmentos-da-sociedade")
    @XmlElement(name = "item")
    @Field(type = String, index = not_analyzed, store = true)
    List<SegmentoDaSociedade> segmentosDaSociedade;

    @XmlElementWrapper(name = "areas-de-interesse")
    @XmlElement(name = "item")
    @Field(type = String, index = not_analyzed)
    List<AreaDeInteresse> areasDeInteresse;

    @XmlElementWrapper(name = "palavras-chave")
    @XmlElement(name = "item")
    List<String> palavrasChave;

    @XmlElementWrapper(name = "legislacoes")
    @XmlElement(name = "item")
    @Field(type = String, index = not_analyzed)
    List<String> legislacoes;

    @Component
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    public static class Formatter implements org.springframework.format.Formatter<ServicoXML> {

        Slugify slugify;
        ServicoRepository servicos;
        private OrgaoRepositoryUtil orgaoRepositoryUtil;

        @Autowired
        public Formatter(Slugify slugify, ServicoRepository servicos, OrgaoRepositoryUtil orgaoRepositoryUtil) {
            this.slugify = slugify;
            this.servicos = servicos;
            this.orgaoRepositoryUtil = orgaoRepositoryUtil;
        }

        @Override
        public ServicoXML parse(String id, Locale locale) throws ParseException {
            return ofNullable(servicos.findOne(slugify.slugify(id)))
                    .map(s -> s.withOrgao(orgaoRepositoryUtil.obterOrgao(s.getOrgao())))
                    .orElseThrow(() -> new ConteudoNaoEncontrado(id));
        }

        @Override
        public String print(ServicoXML servico, Locale locale) {
            return servico.getId();
        }
    }
}
