package br.com.keidsonroby.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.keidsonroby.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateInterceptor extends OncePerRequestFilter {

  @Autowired
  private JWTCandidateProvider jwtCandidateProvider;


  @Override
  protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
      )
        throws ServletException, IOException {
          // Zerando as autenticações anteriores
          SecurityContextHolder.getContext().setAuthentication(null);
          String header = request.getHeader("Authorization");

          if (request.getRequestURI().startsWith("/candidate")) {
            if (header != null) {
              var token = this.jwtCandidateProvider.validateToken(header);
  
              if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
              }
  
              request.setAttribute("candidate_id", token.getSubject());
              
              var roles = token.getClaim("roles").asList(Object.class);

              System.out.println("Token: " + token);
            }
          }

          filterChain.doFilter(request, response);
        }
}