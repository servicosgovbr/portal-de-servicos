package br.gov.servicos.orgao;

import br.gov.servicos.config.SlugifyConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UrlsSiorg {
    private static Map<String, String> urls = new ConcurrentHashMap<>();

    public static String obterUrl(String id) {
        return urls.get(id);
    }

    public static String salvarUrl(String url) {
        return urls.put(SlugifyConfig.slugify(url), url);
    }
}
