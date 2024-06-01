package br.com.keidsonroby.gestao_vagas.domain.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.keidsonroby.gestao_vagas.domain.candidate.dto.ProfileCandidateResponseDTO;
import br.com.keidsonroby.gestao_vagas.domain.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
      .orElseThrow(
        () -> {
          throw new UsernameNotFoundException("Candidate not found");
        });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
      .id(candidate.getId())
      .name(candidate.getName())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .description(candidate.getDescription())
      .build();
    
    return candidateDTO;
  }
}
