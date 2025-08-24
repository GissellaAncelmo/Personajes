package com.personajes.personajes.Config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching  // Activa el sistema de cache en toda la aplicación
class CacheConfig {


    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();

        
        cacheManager.setCacheNames(Arrays.asList(
                "personajes",           // Cache individual de personajes por ID
                "personajes-list",      // Cache de listas de personajes
                "personajes-count"      // Cache del contador de personajes
        ));


        cacheManager.setAllowNullValues(false);  // No cachea valores null

        return cacheManager;
    }
}
