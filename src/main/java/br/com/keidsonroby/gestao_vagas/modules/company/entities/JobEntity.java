package br.com.keidsonroby.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "job")
@Data
public class JobEntity {

  @Id // id vai ser a chave primária;
  @GeneratedValue(strategy = GenerationType.UUID) // cria o id único automaticamente;
  private UUID id;

  // Foreign Key (FK); 
  // Muitos Jobs para uma company;
  @ManyToOne
  @JoinColumn(name = "company_id", insertable = false, updatable = false) // anotation de relacionamento 
  private CompanyEntity companyEntity;

  @Column(name = "company_id", nullable = false)
  private UUID companyId;

  private String description;
  private String level;

  @NotBlank(message = "Esse campo é obrigatório.")
  private String benefits;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
