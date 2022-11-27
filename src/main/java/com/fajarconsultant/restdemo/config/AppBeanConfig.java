package com.fajarconsultant.restdemo.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Declare common managed beans configuration.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 26/11/2022, modified: 27/11/2022 16:37
 */
@Configuration
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class AppBeanConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("*"));  // e.g. http://domain1.com
        config.setAllowedHeaders(List.of(
                "Accept",
                "Accept-Language",
                "Authorization",
                "Content-Type",
                "X-Api-Key",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Last-Modified",
                "Origin"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public ProjectionFactory projectionFactory(BeanFactory beanFactory) {
        SpelAwareProxyProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        projectionFactory.setBeanFactory(beanFactory);

        return projectionFactory;
    }
}
