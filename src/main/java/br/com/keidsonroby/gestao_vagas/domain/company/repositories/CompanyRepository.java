package br.com.keidsonroby.gestao_vagas.domain.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keidsonroby.gestao_vagas.domain.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
  
  Optional<CompanyEntity> findByUsername(String username);
}
