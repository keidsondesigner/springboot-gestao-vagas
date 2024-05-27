package br.com.keidsonroby.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.keidsonroby.gestao_vagas.exceptions.UserFoundException;
import br.com.keidsonroby.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.keidsonroby.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CandidateRepository candidateRepository;
  
  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
    .ifPresent((user) -> {
      throw new UserFoundException();
    });

    var password = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }
}
