package br.com.keidsonroby.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.modules.candidate.CandidadeEntity;

@RestController
@RequestMapping("/candidate")
public class CaditateController {

  
  @PostMapping("/")
  public void create(@RequestBody CandidadeEntity candidateEntity) {
    System.out.println("Candidato");
    System.out.println(candidateEntity.getEmail());

  }
}
