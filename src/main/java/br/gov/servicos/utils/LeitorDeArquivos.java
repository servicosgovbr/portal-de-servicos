package br.gov.servicos.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Optional;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@Component
public class LeitorDeArquivos {

    @SneakyThrows
    public Optional<String> ler(File arquivo) {
        if (!arquivo.exists()) {
            log.info("Arquivo {} não encontrado", arquivo.getAbsolutePath());
            return empty();

        } else if (!arquivo.isFile() || !arquivo.canRead()) {
            log.info("Arquivo {} não pode ser lido", arquivo.getAbsolutePath());
            return empty();
        }
        log.info("Arquivo {} encontrado", arquivo.getAbsolutePath());

        try (InputStream in = new FileInputStream(arquivo)) {
            return of(StreamUtils.copyToString(in, defaultCharset()));
        }
    }

    public Optional<String> ler(URI uri) throws IOException {
        try (InputStream in = uri.toURL().openStream()) {
            return of(StreamUtils.copyToString(in, defaultCharset()));
        }
    }

}
