package br.unipar.programacaoweb.livraria.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableSpringDataWebSupport
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${security.cors.origin}")
    private String corsOrigin;

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("auth/login").permitAll()
                                        .requestMatchers("sensor/listar")
                                            .hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("sensor/excluir/")
                                        .hasAnyAuthority("ADMIN")
                                        .requestMatchers("sensor/buscar/")
                                        .hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("sensor/salvar")
                                        .hasAnyAuthority("ADMIN")

                                        .requestMatchers("leitura/listar")
                                        .hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("leitura/salvar")
                                        .hasAnyAuthority("ADMIN")
                                        .requestMatchers("leitura/excluir/")
                                        .hasAnyAuthority("ADMIN")
                                        .requestMatchers("leitura/buscar/")
                                        .hasAnyAuthority("ADMIN", "USER")

                                        .requestMatchers("estacao/listar")
                                        .hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("estacao/media-leituras/").permitAll()
                                        //.hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("estacao/salvar")
                                            .hasAnyAuthority("ADMIN")
                                        .requestMatchers("estacao/editar/")
                                        .hasAnyAuthority("ADMIN")
                                        .requestMatchers("estacao/buscar/")
                                        .hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers("estacao/excluir/")
                                        .hasAnyAuthority("ADMIN")
                                        .anyRequest().denyAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        String[] origins = corsOrigin.split(",");

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Bearer"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;

    }
}
