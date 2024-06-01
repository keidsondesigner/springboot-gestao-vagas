package br.com.keidsonroby.gestao_vagas.domain.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.domain.company.entities.CompanyEntity;
import br.com.keidsonroby.gestao_vagas.domain.company.services.CreateCompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyService createCompanyService;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
    try {
      var company = this.createCompanyService.execute(companyEntity);
      return ResponseEntity.ok().body(company);
    } catch (Exception e) {
      // e.printStackTrace();
      // se existir um user, pode retornar um erro
      return ResponseEntity.badRequest().body(e.getMessage());

    }
  }
}
