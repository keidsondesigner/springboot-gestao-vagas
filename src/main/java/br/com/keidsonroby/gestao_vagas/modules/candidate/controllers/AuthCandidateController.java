package br.com.keidsonroby.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keidsonroby.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.keidsonroby.gestao_vagas.modules.candidate.services.AuthCandidateService;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateService authCandidateService;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var token = this.authCandidateService.execute(authCandidateRequestDTO);
      return ResponseEntity.ok().body(token);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
