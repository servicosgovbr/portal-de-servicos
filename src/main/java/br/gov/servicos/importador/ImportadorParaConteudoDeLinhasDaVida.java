package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVidaRepository;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ImportadorParaConteudoDeLinhasDaVida {

    LinhaDaVidaRepository linhaDaVidaRepository;
    ConteudoParser parser;

    @Autowired
    public ImportadorParaConteudoDeLinhasDaVida(LinhaDaVidaRepository linhaDaVidaRepository, ConteudoParser parser) {
        this.linhaDaVidaRepository = linhaDaVidaRepository;
        this.parser = parser;
    }

    Stream<Conteudo> importar() {
        return linhaDaVidaRepository.findAll().stream().map((linhaDaVida) -> new Conteudo()
                .withId(linhaDaVida.getId())
                .withTipoConteudo("linha-da-vida")
                .withTitulo(linhaDaVida.getTitulo())
                .withConteudo(parser.conteudo(format("/conteudo/linhas-da-vida/%s.md", linhaDaVida.getId()))));
    }
}
