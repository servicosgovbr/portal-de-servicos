package br.gov.servicos.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.HOURS;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(1, HOURS));
        return manager;
    }

}
