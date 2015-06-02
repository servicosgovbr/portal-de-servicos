package br.gov.servicos.foundation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;

public class IO {

    public static String read(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, defaultCharset()))) {
            return reader.lines().collect(joining("\n"));
        }
    }

}
