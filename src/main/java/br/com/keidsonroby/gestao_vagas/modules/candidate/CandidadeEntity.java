package br.com.keidsonroby.gestao_vagas.modules.candidate;

import java.util.UUID;

import lombok.Data;

@Data
public class CandidadeEntity {

  private UUID id;
  private String name;
  private String username;
  private String email;
  private String password;
  private String description;
  private String curriculum;
}
