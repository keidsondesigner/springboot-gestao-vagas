package br.com.keidsonroby.gestao_vagas.domain.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

  private String title;
  private String description;
  private String benefits;
  private String level;
}
