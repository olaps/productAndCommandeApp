package fr.milleis.test.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration CORS pour permettre les requêtes cross-origin
 */
@Configuration
public class CorsConfig {

    /**
     * Configure le filtre CORS pour autoriser les requêtes du frontend
     * @return Filtre CORS configuré
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser les origines
        config.addAllowedOrigin("http://localhost:4200");

        // Autoriser les en-têtes et méthodes
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Autoriser les cookies (si nécessaire)
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}