package br.com.keidsonroby.gestao_vagas.domain.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.domain.candidate.entities.CandidateEntity;
import br.com.keidsonroby.gestao_vagas.domain.candidate.services.CreateCandidateService;
import br.com.keidsonroby.gestao_vagas.domain.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CadidateController {

  @Autowired
  private CreateCandidateService createCandidateService;

  @Autowired
  private ProfileCandidateService profileCandidateService;
  
  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateService.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest request) { // usar o HttpServletRequest para pegar o id do candidato
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var profile = this.profileCandidateService.execute(UUID.fromString(idCandidate.toString())); // converter o id para string
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
