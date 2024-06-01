package br.com.keidsonroby.gestao_vagas.domain.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company") // nome da tabela
@Data
public class CompanyEntity {

  @Id // id vai ser a chave primária;
  @GeneratedValue(strategy = GenerationType.UUID) // cria o id único automaticamente;
  private UUID id;

  // @Column(name = "nome") // para renomear o nome da minha coluna;
  private String name;

  @Pattern(regexp = "\\S+", message =  "O campo [username], não deve conter espaços, ou use underline * _ * em espaços.")
  private String username;

  @Email(message = "O campo [email], deve conter um email válido.")
  private String email;

  @Length(min = 10, max = 100, message = "O campo [senha], deve conter entre 10 a 100 caracteres.")
  private String password;

  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
