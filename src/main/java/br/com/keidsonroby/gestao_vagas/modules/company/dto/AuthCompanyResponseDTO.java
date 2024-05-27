package br.com.keidsonroby.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // Para criar a instancia
@NoArgsConstructor // Para criar um construtor vazio
@AllArgsConstructor // Para criar um construtor com todos os atributos
public class AuthCompanyResponseDTO {
  private String acess_token;
  private Long expires_in;
}
