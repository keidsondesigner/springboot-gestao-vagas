package br.com.keidsonroby.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Cria um Constructor com todos os atributos;
public class AuthCompanyDTO {

  private String username;
  private String password;
}
