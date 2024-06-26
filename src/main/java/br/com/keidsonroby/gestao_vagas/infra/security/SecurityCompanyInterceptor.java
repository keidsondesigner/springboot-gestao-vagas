package br.com.keidsonroby.gestao_vagas.infra.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.keidsonroby.gestao_vagas.providers.JWTCompanyProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyInterceptor extends OncePerRequestFilter {

  @Autowired
  private JWTCompanyProvider jwtCompanyProvider;

  @Override
  protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
      )
        throws ServletException, IOException {
          // Zerando as autenticações anteriores
          // SecurityContextHolder.getContext().setAuthentication(null);
          String header = request.getHeader("Authorization");
          // System.out.println("Header: " + header);

          if (request.getRequestURI().startsWith("/company")) {
            if (header != null) {
              var token = this.jwtCompanyProvider.validateToken(header);
              if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
              }

              var roles = token.getClaim("roles").asList(Object.class);
              
              var grants = roles.stream().map(// trasnformo a lista em stream()
              role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()) // transformo o objeto em string
              ).toList(); //  depois pego o stream() etransformo em uma lista novamente;
              
              request.setAttribute("company_id", token.getSubject());

              UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
              SecurityContextHolder.getContext().setAuthentication(auth);
            }
          }

          filterChain.doFilter(request, response);
        }
}
