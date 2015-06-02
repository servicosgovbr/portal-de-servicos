package br.gov.servicos.config;

import com.google.common.cache.Cache;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CacheHealthIndicator implements HealthIndicator {


    private CacheManager cacheManager;

    @Autowired
    public CacheHealthIndicator(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Health health() {
        Health.Builder health = Health.unknown();
        try {
            Collection<String> caches = cacheManager.getCacheNames();
            for (String cache : caches) {
                Cache nativeCache = (Cache) cacheManager.getCache(cache).getNativeCache();
                health = health.up().withDetail(cache, nativeCache.size());
            }
            return health.build();

        } catch (Exception e) {
            return health.down().withException(e).build();
        }
    }
}
