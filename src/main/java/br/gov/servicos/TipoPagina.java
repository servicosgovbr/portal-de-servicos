package br.gov.servicos;

import br.gov.servicos.config.SlugifyConfig;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public enum TipoPagina {
    PAGINA_ORGAO("conteudo/orgaos", "xml"),
    PAGINA_TEMATICA("conteudo/paginas-tematicas", "xml"),
    PAGINA_ESTATICA("conteudo/paginas-estaticas", "md"),
    SERVICO("cartas-servico/v3/servicos", "xml");

    @Getter
    private String nome;

    @Getter
    private Path caminhoPasta;

    @Getter
    private String extensao;

    TipoPagina(String caminhoPasta, String extensao) {
        this.nome = SlugifyConfig.slugify(name());
        this.caminhoPasta = Paths.get(caminhoPasta);
        this.extensao = "." + extensao;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static TipoPagina fromNome(String nome) {
        return Arrays.asList(values())
                .stream()
                .filter(t -> t.getNome().equals(nome))
                .findFirst()
                .get();
    }

    public String prefixo() {
        return getNome() + "-";
    }
}
