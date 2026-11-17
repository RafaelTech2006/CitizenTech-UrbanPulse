package br.com.urbanpulse.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OcorrenciaDTO(
        @NotBlank(message = "Este campo deve ser preenchido.")
        String titulo,
        @NotBlank(message = "Este campo deve ser preenchido.")
        String categoria,
        @NotBlank(message = "Este campo deve ser preenchido.")
        String descricao,
        @NotBlank(message = "Este campo deve ser preenchido.")
        String endereco,
        String fotoAntesUrl,
        @NotNull(message = "Este campo deve ser preenchido.")
        Long usuarioCodigo
) {
}
