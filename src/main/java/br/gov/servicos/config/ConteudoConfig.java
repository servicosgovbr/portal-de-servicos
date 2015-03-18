package br.gov.servicos.config;

import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.Orgao;
import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.Map;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties("gds.conteudo")
@EnableConfigurationProperties
@FieldDefaults(level = PRIVATE)
public class ConteudoConfig {

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, String> linhasDaVida;

    @Getter
    @Setter(/* usado pelo Spring */)
    Map<String, String> orgaos;

    @Autowired
    Slugify slugify;

    public LinhaDaVida linhaDaVida(String termo) {
        String chave = slugify.slugify(termo);
        String titulo = linhasDaVida.get(chave);

        Assert.notNull(titulo,
                format("Chave para a linha da vida '%s' (%s) não encontrada", termo, chave));

        return new LinhaDaVida()
                .withId(slugify.slugify(titulo))
                .withTitulo(titulo);
    }

    public Orgao orgao(String termo) {
        String chave = slugify.slugify(termo);
        String nome = orgaos.get(chave);

        Assert.notNull(nome,
                format("Chave para o órgao '%s' (%s) não encontrada", termo, chave));

        return new Orgao()
                .withId(slugify.slugify(nome))
                .withNome(nome);
    }
}