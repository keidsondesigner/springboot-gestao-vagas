package br.com.keidsonroby.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id // id vai ser a chave primária;
  @GeneratedValue(strategy = GenerationType.UUID) // cria o id único automaticamente ou  GenerationType.AUTO;
  private UUID id;

  // @Column(name = "nome") // para renomear o nome da minha coluna;
  @NotNull(message = "O campo [name], não foi informado.")
  @NotBlank(message = "O campo [name], não poder conter caractérs em branco.")
  @Length(min = 5, max = 10, message = "O campo [name], deve possuir no minimo ${min} caracteres e no máximo ${max}")
  private String name;

  @Pattern(regexp = "\\S+", message =  "O campo [username], não deve conter espaços, ou use underline * _ * em espaços.")
  private String username;

  @Email(message = "O campo [email], deve conter um email válido.")
  private String email;

  @Length(min = 10, max = 100, message = "O campo [senha], deve conter entre 10 a 100 caracteres.")
  private String password;
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
