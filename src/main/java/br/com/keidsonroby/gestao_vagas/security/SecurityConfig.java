package br.com.keidsonroby.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrfCustomizer -> csrfCustomizer.disable()) // Desabilitar Spring Security para desabilitar o CSRF
        .authorizeHttpRequests((auth) -> {
          // Rotas públicas;
          auth.requestMatchers("/candidate/").permitAll()
              .requestMatchers("/company/").permitAll()
              .requestMatchers("/auth/company").permitAll();
          // E qualque outra, serão Rotas privadas e precisam de autenticação;
          auth.anyRequest().authenticated();
        });
    return http.build();
  }

  // CRIPTOGRAFIA DA SENHA
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
