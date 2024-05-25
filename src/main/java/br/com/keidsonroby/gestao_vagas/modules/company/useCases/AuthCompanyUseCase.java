package br.com.keidsonroby.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.keidsonroby.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.keidsonroby.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}") // Acessando a variável no application.properties
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    // Se a Company não existir, vai lançar uma exceção;
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("Company not found");
      });

    // Se a Company existir;
    // Verificar se as senhas são iguais
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
    // Se a senha é diferente "estiver errada" Deve gerar um Erro
    if (!passwordMatches) {
      throw new AuthenticationException();
    }
    // Se a senha é igual "estiver correta"  -> Deve gerar o token
    Algorithm algorithm = Algorithm.HMAC256(secretKey); // tipo do algoritimo de criptografia
    var token = JWT.create().withIssuer("gestao_vagas")
      .withSubject(company.getId().toString())
      .sign(algorithm);
    
    return token;
  }
}
