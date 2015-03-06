package br.gov.servicos.servico;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@XStreamAlias("orgao")
public class Orgao {

    @Id
    @Field(type = String, index = not_analyzed)
    String id;

    @Field(type = String)
    String nome;

    @Field(type = String)
    String telefone;

    public Orgao() {
        this(null, null, null);
    }

    public Orgao(String id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
}
