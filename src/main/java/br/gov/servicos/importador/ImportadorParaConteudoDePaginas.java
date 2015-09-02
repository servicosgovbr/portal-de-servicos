package br.gov.servicos.importador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.Markdown;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportadorParaConteudoDePaginas {

    Markdown markdown;
    ConteudoParser parser;

    @Autowired
    public ImportadorParaConteudoDePaginas(
            Markdown markdown,
            ConteudoParser parser) {
        this.markdown = markdown;
        this.parser = parser;
    }

    public Stream<Conteudo> importar(RepositorioCartasServico repositorioCartasServico) {
        return asList(
                "acessibilidade",
                "cadastro-de-pessoas-fisicas-cpf",
                "documento-de-arrecadacao-de-receitas-federais-darf",
                "perguntas-frequentes"
        ).stream()
                .map(id -> {
                    Resource documento = acessarDocumento(repositorioCartasServico, id);
                    return new Conteudo()
                            .withId(id)
                            .withNome(markdown.toHtml(documento).getNome())
                            .withTipoConteudo("conteudo")
                            .withConteudo(parser.conteudo(documento));
                });
    }

    private Resource acessarDocumento(RepositorioCartasServico repositorioCartasServico, String id) {
        String caminhoDocumento = String.format("conteudo/%s.md", id);
        return repositorioCartasServico.acessarDocumento(caminhoDocumento);
    }
}
