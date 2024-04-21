package br.com.keidsonroby.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrfCustomizer -> csrfCustomizer.disable())
        .authorizeHttpRequests((auth) -> {
          // Rotas públicas;
          auth.requestMatchers("/candidate/").permitAll()
              .requestMatchers("/company/").permitAll();
          // E qualque outra, serão Rotas privadas e precisam de autenticação;
          auth.anyRequest().authenticated();
        });
    return http.build();
  }

}
