package br.com.keidsonroby.gestao_vagas.domain.company.repositories;

import br.com.keidsonroby.gestao_vagas.domain.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
