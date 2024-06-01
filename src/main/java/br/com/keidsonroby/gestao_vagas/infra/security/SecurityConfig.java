package br.com.keidsonroby.gestao_vagas.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private SecurityCompanyInterceptor securityCompanyInterceptor;

  @Autowired
  private SecurityCandidateInterceptor securityCandidateInterceptor;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrfCustomizer -> csrfCustomizer.disable()) // Desabilitar Spring Security para desabilitar o CSRF
        .authorizeHttpRequests((auth) -> {
          // Rotas públicas;
          auth.requestMatchers("/candidate/").permitAll()
              .requestMatchers("/company/").permitAll()
              .requestMatchers("/company/auth").permitAll()
              .requestMatchers("/candidate/auth").permitAll();
          // qualque outra rota, serão Rotas privada, que precisam de autenticação Token;
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCandidateInterceptor, BasicAuthenticationFilter.class)
        .addFilterBefore(securityCompanyInterceptor, BasicAuthenticationFilter.class);
    return http.build();
  }

  // CRIPTOGRAFIA DA SENHA
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
