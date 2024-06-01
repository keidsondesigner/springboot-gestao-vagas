package br.com.keidsonroby.gestao_vagas.domain.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.keidsonroby.gestao_vagas.domain.candidate.dto.AuthCandidateRequestDTO;
import br.com.keidsonroby.gestao_vagas.domain.candidate.dto.AuthCandidateResponseDTO;
import br.com.keidsonroby.gestao_vagas.domain.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateService {

  @Value("${security.token.secret.candidate}") // Acessando a variável no application.properties
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("Username or Password incorrect");
      });

    var passwordMatches = this.passwordEncoder
    .matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey); // tipo do algoritimo de criptografia
    var expiresIn = Instant.now().plus(Duration.ofHours(2));

    var token = JWT.create().withIssuer("gestao_vagas")
      .withSubject(candidate.getId().toString())
      .withClaim("roles", Arrays.asList("CANDIDATE"))
      .withExpiresAt(expiresIn)
      .sign(algorithm);

      var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli()) // Convertendo o Instant para milissegundos
        .build();

      return authCandidateResponse;
  }
}
