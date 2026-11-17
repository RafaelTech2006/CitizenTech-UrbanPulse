package br.com.urbanpulse.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "Este campo deve ser preenchido.")
        String nome,
        @Email(message = "Email inválido.")
        @NotBlank(message = "Este campo deve ser preenchido.")
        String email,
        @NotBlank(message = "Este campo deve ser preenchido.")
        String telefone
) {
}
