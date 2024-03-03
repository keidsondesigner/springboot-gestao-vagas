package br.com.keidsonroby.gestao_vagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import br.com.keidsonroby.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  // faça a busca por username ou email;
  // Optional = se ele não encontrar, ele retorna a entidade ou nulo, e algumas outras operações;
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
