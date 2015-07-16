package br.gov.servicos.foundation;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IO {

    @SneakyThrows
    public static String read(Resource resource) {
        return read(resource.getInputStream());
    }

    @SneakyThrows
    public static String read(InputStream stream) {
        try (BufferedReader reader = bufferedReader(stream)) {
            return reader.lines().collect(joining("\n"));
        }
    }

    @SneakyThrows
    public static BufferedReader bufferedReader(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream, defaultCharset()));
    }

}
