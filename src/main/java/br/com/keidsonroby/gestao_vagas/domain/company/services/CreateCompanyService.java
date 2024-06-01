package br.com.keidsonroby.gestao_vagas.domain.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.keidsonroby.gestao_vagas.exceptions.UserFoundException;
import br.com.keidsonroby.gestao_vagas.domain.company.entities.CompanyEntity;
import br.com.keidsonroby.gestao_vagas.domain.company.repositories.CompanyRepository;

@Service
public class CreateCompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public CompanyEntity execute(CompanyEntity companyEntity) {

    this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent((user) -> {
        throw new UserFoundException();
      });

      var password = passwordEncoder.encode(companyEntity.getPassword());
      companyEntity.setPassword(password);

    return this.companyRepository.save(companyEntity);
  }
}