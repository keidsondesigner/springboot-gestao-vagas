package br.com.keidsonroby.gestao_vagas.domain.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.domain.company.dto.AuthCompanyDTO;
import br.com.keidsonroby.gestao_vagas.domain.company.services.AuthCompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyService authCompanyService;

  @PostMapping("/auth")
  public ResponseEntity<Object> createToken(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var token = this.authCompanyService.execute(authCompanyDTO);
      return ResponseEntity.ok().body(token);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
