package br.com.keidsonroby.gestao_vagas.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.keidsonroby.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/company")
  public String createToken(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
      return this.authCompanyUseCase.execute(authCompanyDTO);
  }
}