package br.gov.servicos.cms;

import br.gov.servicos.config.SlugifyConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.nio.file.Paths;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TipoPagina {

    ORGAO("conteudo/orgaos", "xml"),
    PAGINA_TEMATICA("conteudo/paginas-tematicas", "xml"),
    PAGINA_ESTATICA("conteudo/paginas-estaticas", "md"),
    SERVICO("cartas-servico/v3/servicos", "xml");

    String nome;

    String caminhoPasta;

    String extensao;

    TipoPagina(String caminhoPasta, String extensao) {
        this.nome = SlugifyConfig.slugify(name());
        this.caminhoPasta = Paths.get(caminhoPasta).toString();
        this.extensao = "." + extensao;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
