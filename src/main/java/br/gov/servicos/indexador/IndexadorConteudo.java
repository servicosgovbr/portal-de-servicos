package br.gov.servicos.indexador;

import br.gov.servicos.cms.Conteudo;
import br.gov.servicos.cms.ConteudoRepository;
import br.gov.servicos.foundation.exceptions.ConteudoNaoEncontrado;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.servico.Orgao;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.linhaDaVida.LinhaDaVidaRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class IndexadorConteudo {

    LinhaDaVidaRepository linhaDaVidaRepository;
    OrgaoRepository orgaoRepository;
    ConteudoRepository conteudoRepository;

    @Autowired
    public IndexadorConteudo(LinhaDaVidaRepository linhaDaVidaRepository, OrgaoRepository orgaoRepository, ConteudoRepository conteudoRepository) {
        this.linhaDaVidaRepository = linhaDaVidaRepository;
        this.orgaoRepository = orgaoRepository;
        this.conteudoRepository = conteudoRepository;
    }

    public void indexar() {
        Stream<Conteudo> conteudosOrgaos = orgaoRepository.findAll().stream()
                .map(this::paraConteudo);

        Stream<Conteudo> conteudoLinhasDaVida = linhaDaVidaRepository.findAll().stream()
                .map(this::paraConteudo);

        List<Conteudo> conteudos = Stream.concat(conteudosOrgaos, conteudoLinhasDaVida).collect(toList());

        this.conteudoRepository.save(conteudos);
    }

    private Conteudo paraConteudo(LinhaDaVida linhaDaVida) {
        return new Conteudo()
                .withTitulo(linhaDaVida.getTitulo())
                .withConteudo(obterConteudoLinhaDaVida(linhaDaVida.getId()));
    }

    private Conteudo paraConteudo(Orgao orgao) {
        return new Conteudo()
                .withTitulo(orgao.getNome())
                .withConteudo(obterConteudoOrgao(orgao.getId()));
    }

    private String obterConteudoLinhaDaVida(String id) {
        String linhaDaVidaId = format("/conteudo/linhas-da-vida/%s.md", id);
        return obterConteudo(linhaDaVidaId);
    }

    private String obterConteudoOrgao(String id) {
        String orgaoId = format("/conteudo/orgaos/%s.md", id);
        return obterConteudo(orgaoId);
    }

    private String obterConteudo(String conteudoId) {
        ClassPathResource resource = new ClassPathResource(conteudoId);
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(resource.getInputStream(), "UTF-8");
            try (BufferedReader br = new BufferedReader(input)) {
                return br.lines().collect(joining("\n"));
            }
        } catch (IOException e) {
            throw new ConteudoNaoEncontrado(e);
        }
    }

}
