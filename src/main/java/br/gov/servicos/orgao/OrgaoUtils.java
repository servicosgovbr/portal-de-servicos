package br.gov.servicos.orgao;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrgaoUtils {
    public static final Pattern URL_PATTERN = Pattern.compile("http://estruturaorganizacional\\.dados\\.gov\\.br/(doc|id)/unidade-organizacional/(\\d+)");
    public static final Pattern URL_ID_PATTERN = Pattern.compile("http-estruturaorganizacional-dados-gov-br-(doc|id)-unidade-organizacional-(\\d+)");

    public long obterId(String url) {
        if (URL_PATTERN.asPredicate().negate().test(url) &&
                URL_ID_PATTERN.asPredicate().negate().test(url)) {
            throw new IllegalArgumentException("Formato url inválida para SIORG: " + url);
        }

        Matcher m = URL_PATTERN.matcher(url);
        if (!m.find()) {
            m = URL_ID_PATTERN.matcher(url);
            m.find();
        }
        return Long.parseLong(m.group(2));
    }
}
