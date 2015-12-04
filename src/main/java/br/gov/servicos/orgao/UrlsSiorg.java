package br.gov.servicos.orgao;

import br.gov.servicos.config.SlugifyConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlsSiorg {
    private static Pattern URL_PREDICATE = Pattern.compile("(http://estruturaorganizacional\\.dados\\.gov\\.br/)(doc|id)(/unidade-organizacional/\\d+)");
    private static Map<String, String> urls = new ConcurrentHashMap<>();

    public static String obterUrl(String id) {
        return urls.get(SlugifyConfig.slugify(id));
    }

    public static void salvarUrl(String url) {
        if (!URL_PREDICATE.asPredicate().test(url)) {
            throw new IllegalArgumentException("Formato url inválida para SIORG: " + url);
        }
        Matcher m = URL_PREDICATE.matcher(url);
        String url1 = m.replaceFirst("$1id$3");
        String url2 = m.replaceFirst("$1doc$3");

        urls.put(SlugifyConfig.slugify(url1), url);
        urls.put(SlugifyConfig.slugify(url2), url);
    }

}
