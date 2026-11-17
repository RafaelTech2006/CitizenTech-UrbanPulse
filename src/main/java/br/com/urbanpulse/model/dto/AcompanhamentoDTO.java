package br.com.urbanpulse.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AcompanhamentoDTO(
        @NotNull(message = "Este campo deve ser preenchido.")
        Long ocorrenciaCodigo,
        @NotBlank(message = "Este campo deve ser preenchido")
        String etapa,
        String responsavel
) {
}
