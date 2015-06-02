package br.gov.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.joining;

public class IOUtils {
    public static String toString(InputStream is) throws IOException {
        InputStreamReader reader = new InputStreamReader(is, defaultCharset());
        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(joining("\n"));
        }
    }
}
