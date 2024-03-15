package com.sfahafi.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){

        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/applications/apihotel/**").hasRole("HOTELS")
                .pathMatchers("/applications/apirooms/**").authenticated()
                .pathMatchers("/applications/apireservations/**").permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt().jwtAuthenticationConverter(grantedAuthorities()))
                .csrf(csrf -> csrf.disable());

        return http.build();

    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthorities() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new RoleKeycloakConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
