package br.com.keidsonroby.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
          SecurityContextHolder.getContext().setAuthentication(null);
          String header = request.getHeader("Authorization");
          // System.out.println("Header: " + header);

          if (request.getRequestURI().startsWith("/company")) {
            if (header != null) {
              var token = this.jwtCompanyProvider.validateToken(header);
              if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
              }

              request.setAttribute("company_id", token.getSubject());
              
              var roles = token.getClaim("roles").asList(Object.class);

              UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, Collections.emptyList());
              
              SecurityContextHolder.getContext().setAuthentication(auth);
            }
          }

          filterChain.doFilter(request, response);
        }
}