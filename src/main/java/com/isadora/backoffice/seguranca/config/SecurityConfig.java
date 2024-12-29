package com.isadora.backoffice.seguranca.config;

import com.isadora.backoffice.seguranca.service.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Value("${security.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${security.cors.allowed-methods}")
    private String[] allowedMethods;

    @Value("${security.cors.allowed-headers}")
    private String[] allowedHeaders;

    @Value("${security.cors.exposed-headers}")
    private String[] exposedHeaders;

    @Value("${security.cors.allow-credentials}")
    private boolean allowCredentials;

    @Value("${security.cors.max-age}")
    private long maxAge;

    @Value("${security.endpoints.public}")
    private String[] publicEndpoints;

    @Value("${security.roles.admin-endpoints}")
    private String[] adminEndpoints;

    @Value("${security.roles.user-endpoints}")
    private String[] userEndpoints;

    @Bean(name = "globalSecurityFilterChain")
    public SecurityFilterChain globalSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
                    configuration.setAllowedMethods(Arrays.asList(allowedMethods));
                    configuration.setAllowedHeaders(Arrays.asList(allowedHeaders));
                    configuration.setExposedHeaders(Arrays.asList(exposedHeaders));
                    configuration.setAllowCredentials(allowCredentials);
                    configuration.setMaxAge(maxAge);
                    return configuration;
                }))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(publicEndpoints).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}